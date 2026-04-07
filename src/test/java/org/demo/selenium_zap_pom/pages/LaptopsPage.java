package org.demo.selenium_zap_pom.pages;

import io.qameta.allure.Step;
import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LaptopsPage extends BasePage {

    public locators locators;
    public LaptopsPage(WebDriver driver) {
        super(driver);
        this.locators = new locators();
    }

    @Step("Navegar al menú Laptops")
    public void clickLaptopsMenu() {
        tomarPantalla("Antes de hacer click en Laptops");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menu_laptop = wait.until(ExpectedConditions.elementToBeClickable(locators.laptopmenu));
        menu_laptop.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locators.productCards));
        tomarPantalla("Después de hacer click en Laptops - productos cargados");
    }
}

