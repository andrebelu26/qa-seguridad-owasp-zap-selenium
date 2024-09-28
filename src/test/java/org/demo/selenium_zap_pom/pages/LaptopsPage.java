package org.demo.selenium_zap_pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LaptopsPage extends BasePage {


    public LaptopsPage(WebDriver driver) {
        super(driver);
    }

    public void clickLaptopsMenu() throws InterruptedException {
        WebElement menu_laptop = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]"));
        Thread.sleep(5000);
        menu_laptop.click();
        Thread.sleep(5000);
    }
}

