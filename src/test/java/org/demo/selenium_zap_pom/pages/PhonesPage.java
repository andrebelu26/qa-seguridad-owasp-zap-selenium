package org.demo.selenium_zap_pom.pages;

import io.qameta.allure.Step;
import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhonesPage extends BasePage {

    public locators locator;
    public PhonesPage(WebDriver driver) {
        super(driver);
        locator = new locators();
    }

    @Step("Navegar al menú Phones")
    public void clickPhonesMenu() {
        tomarPantalla("Antes de hacer click en Phones");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menu_phones = wait.until(ExpectedConditions.elementToBeClickable(locator.phonesmenu));
        menu_phones.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator.productCards));
        tomarPantalla("Después de hacer click en Phones - productos cargados");
    }
}
