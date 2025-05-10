package org.ReginaldMen;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TS_001 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc001_verifyURL() {
        try {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://reginaldmen.com/", "URL does not match expected");
        } catch (Exception e) {
            Assert.fail("Exception in URL check: " + e.getMessage());
        }
    }

    @Test
    public void tc002_verifyTitle() {
        try {
            String title = driver.getTitle();
            String expectedTitle = "Reginald Men"; // Replace with actual title from browser
            Assert.assertEquals(title, expectedTitle, "Title mismatch.");
        } catch (Exception e) {
            Assert.fail("Title verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc003_verifyLogoPresence() {
        try {
            WebElement logo = driver.findElement(By.cssSelector("img[alt='logo']")); // Update if necessary
            Assert.assertTrue(logo.isDisplayed(), "Logo is not visible.");
        } catch (Exception e) {
            Assert.fail("Logo check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc004_verifySearchIcon() {
        try {
            WebElement searchIcon = driver.findElement(By.cssSelector(".search-icon")); // Update if necessary
            Assert.assertTrue(searchIcon.isDisplayed(), "Search icon not displayed.");
        } catch (Exception e) {
            Assert.fail("Search icon check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc005_verifyCartIcon() {
        try {
            WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon")); // Update if necessary
            Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon not visible.");
        } catch (Exception e) {
            Assert.fail("Cart icon verification failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
