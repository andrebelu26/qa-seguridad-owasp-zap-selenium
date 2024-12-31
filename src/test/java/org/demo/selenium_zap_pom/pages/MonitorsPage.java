package org.demo.selenium_zap_pom.pages;

import org.demo.selenium_zap_pom.helpers.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MonitorsPage extends BasePage {

    public locators locator;
    public MonitorsPage(WebDriver driver) {
        super(driver);
        this.locator = new locators();
    }

    public void clickMonitorsMenu() throws InterruptedException {

        WebElement menu_monitors = driver.findElement(locator.monitorsmenu);
        Thread.sleep(5000);
        menu_monitors.click();
        Thread.sleep(5000);
    }
}

