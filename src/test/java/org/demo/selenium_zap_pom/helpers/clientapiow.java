package org.demo.selenium_zap_pom.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

public class clientapiow {
    private ClientApi api;
    private WebDriver driver;
    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8050;
    private static final String ZAP_API_KEY = "r7pisvfpphtob8lkp15k1m9sj5";
    private static final String SCAN_URL = "https://www.demoblaze.com/";

    public clientapiow() throws ClientApiException{
        api = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_API_KEY);
        Proxy SeleniumProxy = new  Proxy();
        SeleniumProxy.setProxyAutoconfigUrl("http://"+ZAP_PROXYHOST+":"+ZAP_PROXYPORT);
        api.ascan.removeAllScans();
        api.core.newSession("", "");
        api.spider.scan(SCAN_URL,null,null,null,null);
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PROXY, SeleniumProxy);
        //chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        api.pscan.enableAllScanners();
        api.ascan.scan(SCAN_URL,"true","false",null,null,null);

        driver = new ChromeDriver(chromeOptions);
        driver.get(SCAN_URL);
        driver.manage().window().maximize();
    }
}
