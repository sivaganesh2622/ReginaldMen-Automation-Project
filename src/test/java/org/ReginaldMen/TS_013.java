package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_013 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc044_searchForProduct() {
        try {
            WebElement searchBox = driver.findElement(By.id("s"));
            searchBox.sendKeys("shirt"); // Example product search term
            searchBox.submit();
            Thread.sleep(2000);

            WebElement productResult = driver.findElement(By.cssSelector(".product"));
            Assert.assertTrue(productResult.isDisplayed(), "No product found for search term 'shirt'.");
        } catch (Exception e) {
            Assert.fail("Product search failed: " + e.getMessage());
        }
    }

    @Test
    public void tc045_verifySearchResultsPage() {
        try {
            WebElement searchBox = driver.findElement(By.id("s"));
            searchBox.sendKeys("shirt");
            searchBox.submit();
            Thread.sleep(2000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("s=shirt"), "Search results page did not load correctly.");
        } catch (Exception e) {
            Assert.fail("Search results page validation failed: " + e.getMessage());
        }
    }

    @Test
    public void tc046_searchForNonExistingProduct() {
        try {
            WebElement searchBox = driver.findElement(By.id("s"));
            searchBox.sendKeys("nonexistentproduct");
            searchBox.submit();
            Thread.sleep(2000);

            WebElement noResultsMessage = driver.findElement(By.cssSelector(".no-products"));
            Assert.assertTrue(noResultsMessage.isDisplayed(), "No 'no results' message displayed for nonexistent product.");
        } catch (Exception e) {
            Assert.fail("Search for nonexistent product failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
