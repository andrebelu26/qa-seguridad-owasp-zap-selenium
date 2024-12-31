package org.demo.selenium_zap_pom.pages;

import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LaptopsPage extends BasePage {

    public locators locators;
    public LaptopsPage(WebDriver driver) {
        super(driver);
        this.locators = new locators();
    }


    public void clickLaptopsMenu() throws InterruptedException {
        WebElement menu_laptop = driver.findElement(locators.laptopmenu);
        Thread.sleep(5000);
        menu_laptop.click();
        Thread.sleep(5000);
    }
}

