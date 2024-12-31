package org.demo.selenium_zap_pom.pages;

import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhonesPage extends BasePage {

    public locators locator;
    public PhonesPage(WebDriver driver) {
        super(driver);
        locator = new locators();
    }

    public void clickPhonesMenu() throws InterruptedException {
        WebElement menu_phones = driver.findElement(locator.phonesmenu);
        Thread.sleep(5000);
        menu_phones.click();
        Thread.sleep(5000);
    }
}
