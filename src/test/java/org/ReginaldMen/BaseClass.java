package org.ReginaldMen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public static WebDriver driver;

    public static void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siva\\eclipse-workspace\\SeleniumKGM\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void windowMaximize() {
        driver.manage().window().maximize();
    }

    public static void launchUrl(String url) {
        driver.get(url);
    }

    public static void closeEntireBrowser() {
        driver.quit();
    }
}
