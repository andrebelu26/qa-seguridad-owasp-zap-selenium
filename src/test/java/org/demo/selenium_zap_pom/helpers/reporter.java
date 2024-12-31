package org.demo.selenium_zap_pom.helpers;


import org.openqa.selenium.WebDriver;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class reporter {
    private clientapiow api = new clientapiow();

    public reporter(ClientApi api, WebDriver driver) throws ClientApiException, IOException, InterruptedException{
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
}
