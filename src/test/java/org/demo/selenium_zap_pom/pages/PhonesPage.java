package org.demo.selenium_zap_pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhonesPage extends BasePage {


    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    public void clickPhonesMenu() throws InterruptedException {
        WebElement menu_phones = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[2]"));
        Thread.sleep(5000);
        menu_phones.click();
        Thread.sleep(5000);
    }
}
