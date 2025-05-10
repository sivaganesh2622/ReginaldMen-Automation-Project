package org.ReginaldMen;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TS_002 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc006_verifyHomeMenuVisible() {
        try {
            WebElement homeMenu = driver.findElement(By.linkText("Home"));
            Assert.assertTrue(homeMenu.isDisplayed(), "Home menu is not visible.");
        } catch (Exception e) {
            Assert.fail("Home menu visibility test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc007_verifyShopMenuVisible() {
        try {
            WebElement shopMenu = driver.findElement(By.linkText("Shop"));
            Assert.assertTrue(shopMenu.isDisplayed(), "Shop menu is not visible.");
        } catch (Exception e) {
            Assert.fail("Shop menu visibility test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc008_verifyAboutMenuVisible() {
        try {
            WebElement aboutMenu = driver.findElement(By.linkText("About"));
            Assert.assertTrue(aboutMenu.isDisplayed(), "About menu is not visible.");
        } catch (Exception e) {
            Assert.fail("About menu visibility test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc009_verifyContactMenuVisible() {
        try {
            WebElement contactMenu = driver.findElement(By.linkText("Contact"));
            Assert.assertTrue(contactMenu.isDisplayed(), "Contact menu is not visible.");
        } catch (Exception e) {
            Assert.fail("Contact menu visibility test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc010_verifyShopMenuNavigation() {
        try {
            WebElement shopMenu = driver.findElement(By.linkText("Shop"));
            shopMenu.click();
            Thread.sleep(2000); // Consider using WebDriverWait instead
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("shop"), "Navigation to Shop page failed.");
        } catch (Exception e) {
            Assert.fail("Shop menu navigation failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
