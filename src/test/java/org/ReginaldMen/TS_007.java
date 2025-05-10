package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class TS_007 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc027_verifySearchWithValidQuery() {
        try {
            // Find the search bar and enter a valid search query
            WebElement searchBar = driver.findElement(By.cssSelector(".search-bar"));
            searchBar.sendKeys("shirt");  // Example query
            searchBar.submit();
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-results")));
            
            // Verify search results contain relevant products
            List<WebElement> products = driver.findElements(By.cssSelector(".product"));
            Assert.assertTrue(products.size() > 0, "No products found for the valid search query.");
        } catch (Exception e) {
            Assert.fail("Search with valid query failed: " + e.getMessage());
        }
    }

    @Test
    public void tc028_verifySearchWithEmptyQuery() {
        try {
            // Find the search bar and submit an empty query
            WebElement searchBar = driver.findElement(By.cssSelector(".search-bar"));
            searchBar.clear();
            searchBar.submit();
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-results")));
            
            // Verify an appropriate message is shown for empty query
            WebElement noResultsMessage = driver.findElement(By.cssSelector(".no-results-message"));
            Assert.assertTrue(noResultsMessage.isDisplayed(), "No message displayed for empty search query.");
        } catch (Exception e) {
            Assert.fail("Search with empty query failed: " + e.getMessage());
        }
    }

    @Test
    public void tc029_verifySearchWithInvalidQuery() {
        try {
            // Find the search bar and enter an invalid search query
            WebElement searchBar = driver.findElement(By.cssSelector(".search-bar"));
            searchBar.sendKeys("xyzxyzxyz");  // Example of an invalid query
            searchBar.submit();
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-results")));
            
            // Verify an appropriate message is shown for no results
            WebElement noResultsMessage = driver.findElement(By.cssSelector(".no-results-message"));
            Assert.assertTrue(noResultsMessage.isDisplayed(), "No results message not shown for invalid query.");
        } catch (Exception e) {
            Assert.fail("Search with invalid query failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
