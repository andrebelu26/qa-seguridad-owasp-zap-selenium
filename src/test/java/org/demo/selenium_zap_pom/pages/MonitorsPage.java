package org.demo.selenium_zap_pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MonitorsPage extends BasePage {


    public MonitorsPage(WebDriver driver) {
        super(driver);
    }

    public void clickMonitorsMenu() throws InterruptedException {
        WebElement menu_monitors = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[2]"));
        Thread.sleep(5000);
        menu_monitors.click();
        Thread.sleep(5000);
    }
}

