package org.demo.selenium_zap_pom.pages;

import io.qameta.allure.Step;
import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MonitorsPage extends BasePage {

    public locators locator;
    public MonitorsPage(WebDriver driver) {
        super(driver);
        this.locator = new locators();
    }

    @Step("Navegar al menú Monitors")
    public void clickMonitorsMenu() {
        tomarPantalla("Antes de hacer click en Monitors");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menu_monitors = wait.until(ExpectedConditions.elementToBeClickable(locator.monitorsmenu));
        menu_monitors.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator.productCards));
        tomarPantalla("Después de hacer click en Monitors - productos cargados");
    }
}

