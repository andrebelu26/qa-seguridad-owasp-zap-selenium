package org.demo.selenium_zap_pom.test;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class AnalisisFinalTest {
    private WebDriver driver;
    private ClientApi api;

    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8050;
    private static final String ZAP_API_KEY = "r7pisvfpphtob8lkp15k1m9sj5";
    private static final String SCAN_URL = "https://www.demoblaze.com/";

    @Before
    public void setup() throws ClientApiException {
        //Instanciar inicialmente el objeto proxy
        api = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_API_KEY);
        Proxy SeleniumProxy = new  Proxy();
        SeleniumProxy.setProxyAutoconfigUrl("http://"+ZAP_PROXYHOST+":"+ZAP_PROXYPORT);
        api.ascan.removeAllScans();
        api.core.newSession("", "");
        api.spider.scan(SCAN_URL,null,null,null,null);
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PROXY, SeleniumProxy);
        //chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        api.pscan.enableAllScanners();
        api.ascan.scan(SCAN_URL,"true","false",null,null,null);

        driver = new ChromeDriver(chromeOptions);
        driver.get(SCAN_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void execTests() throws InterruptedException, ClientApiException, IOException {
        // Instanciar las páginas y ejecutar los métodos de interacción
        LaptopsPage laptopsPage = new LaptopsPage(driver);
        PhonesPage phonesPage = new PhonesPage(driver);
        MonitorsPage monitorsPage = new MonitorsPage(driver);

        // Interacción con los menús,,,,,,,,,,,, .......................................................................
        laptopsPage.clickLaptopsMenu();
        phonesPage.clickPhonesMenu();
        monitorsPage.clickMonitorsMenu();

        // Generar informe
        generarInforme();
    }

    public void generarInforme() throws ClientApiException, IOException, InterruptedException {
        Thread.sleep(6 * 60 * 1000);
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
    }

    @After
    public void teardown() {

            driver.quit();

    }
}

