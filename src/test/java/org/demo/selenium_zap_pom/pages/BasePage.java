package org.demo.selenium_zap_pom.pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "{descripcion}", type = "image/png")
    public byte[] tomarPantalla(String descripcion) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

