package org.demo.selenium_zap_pom.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.demo.selenium_zap_pom.helpers.reporter;
import org.demo.selenium_zap_pom.pages.LaptopsPage;
import org.demo.selenium_zap_pom.pages.MonitorsPage;
import org.demo.selenium_zap_pom.pages.PhonesPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Epic("Seguridad OWASP")
@Feature("Análisis de vulnerabilidades con ZAP Proxy")
public class AnalisisFinalTest {
    private WebDriver driver;
    private ClientApi api;
    private reporter reporter;
    private boolean navegacionCompletada = false;

    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8050;
    private static final String ZAP_API_KEY = "dl63ub0jhlc06ctuh2o8et0pdf";
    private static final String SCAN_URL = "https://www.demoblaze.com/";

    @Before
    public void setup() throws ClientApiException {
        // 1. Conectar con ZAP y configurar nueva sesión
        api = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_API_KEY);
        api.ascan.removeAllScans();
        api.core.newSession("", "");

        // 2. Configurar el proxy de Selenium apuntando a ZAP
        Proxy SeleniumProxy = new Proxy();
        SeleniumProxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        SeleniumProxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);

        // 3. Abrir Chrome con el proxy de ZAP
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PROXY, SeleniumProxy);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(chromeOptions);

        // 4. Navegar a la URL → ZAP registra la URL en su árbol de sitios
        driver.get(SCAN_URL);
        driver.manage().window().maximize();

        // 5. Ahora que ZAP conoce la URL, iniciar spider y escaneo activo
        api.spider.scan(SCAN_URL, null, null, null, null);
        api.pscan.enableAllScanners();
        api.ascan.scan(SCAN_URL, "true", "false", null, null, null);
    }

    @Test
    @Story("Navegación y escaneo de demoblaze.com")
    @Description("Navega por las secciones Laptops, Phones y Monitors mientras ZAP intercepta el tráfico y genera el informe de seguridad")
    @Severity(SeverityLevel.CRITICAL)
    public void execTests() {
        // Instanciar las páginas y ejecutar los métodos de interacción
        LaptopsPage laptopsPage = new LaptopsPage(driver);
        PhonesPage phonesPage = new PhonesPage(driver);
        MonitorsPage monitorsPage = new MonitorsPage(driver);

        // Interacción con los menús
        laptopsPage.clickLaptopsMenu();
        phonesPage.clickPhonesMenu();
        monitorsPage.clickMonitorsMenu();

        // Condición 1 cumplida: navegación completada
        navegacionCompletada = true;
        System.out.println("[CONDICIÓN 1 ✅] Navegación completada.");
    }

    @Step("Esperar escaneo ZAP y generar informe HTML")
    public void generarInforme() throws ClientApiException, IOException, InterruptedException {
        // Condición 1: la automatización debe haber terminado
        if (!navegacionCompletada) {
            throw new IllegalStateException("[CONDICIÓN 1 ❌] La navegación no ha completado. No se puede generar el informe.");
        }

        // Condición 2: esperar a que ZAP termine (spider + escaneo activo)
        esperarSpider();
        esperarEscaneoActivo();
        System.out.println("[CONDICIÓN 2 ✅] Escaneo ZAP completado.");
        System.out.println("[✅ AMBAS CONDICIONES CUMPLIDAS] Generando informe...");

        String report = new String(api.core.htmlreport());
        String folder_report = "scan-results";
            File carpeta = new File(folder_report);
        if (!carpeta.exists()) {
            // Intentar crear la carpeta
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada exitosamente");
            } else {
                System.out.println("No se pudo crear la carpeta");
            }
        } else {
            System.out.println("La carpeta ya existe");
        }
        Path filePath = Paths.get(System.getProperty("user.dir") + "/scan-results/SeleniumTest.html");
        Files.deleteIfExists(filePath);
        Files.write(filePath, report.getBytes());
       //this.reporter = new reporter(api, driver);
    }

    @After
    public void teardown() throws ClientApiException, IOException, InterruptedException {
        // Siempre esperar al scanner y generar informe, independientemente del resultado E2E
        if (api != null) {
            generarInforme();
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Esperar a que el escaneo activo de ZAP finalice")
    private void esperarEscaneoActivo() throws ClientApiException, InterruptedException {
        System.out.println("Esperando que el escaneo activo ZAP finalice...");
        int progreso = 0;
        while (progreso < 100) {
            String statusStr = api.ascan.status(null).toString().replaceAll("[^0-9]", "");
            progreso = statusStr.isEmpty() ? 0 : Integer.parseInt(statusStr);
            System.out.println("Progreso escaneo activo: " + progreso + "%");
            if (progreso < 100) {
                Thread.sleep(5000);
            }
        }
        System.out.println("Escaneo activo finalizado.");
    }

    @Step("Esperar a que el spider de ZAP finalice")
    private void esperarSpider() throws ClientApiException, InterruptedException {
        System.out.println("Esperando que el spider ZAP finalice...");
        int progreso = 0;
        while (progreso < 100) {
            String statusStr = api.spider.status(null).toString().replaceAll("[^0-9]", "");
            progreso = statusStr.isEmpty() ? 0 : Integer.parseInt(statusStr);
            System.out.println("Progreso spider: " + progreso + "%");
            if (progreso < 100) {
                Thread.sleep(5000);
            }
        }
        System.out.println("Spider finalizado.");
    }
}

