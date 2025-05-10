package org.ReginaldMen;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class TS_004 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc015_navigateToShopPage() {
        try {
            WebElement shopLink = driver.findElement(By.linkText("Shop"));
            shopLink.click();
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("shop"), "Failed to navigate to Shop page.");
        } catch (Exception e) {
            Assert.fail("Shop navigation failed: " + e.getMessage());
        }
    }

    @Test
    public void tc016_verifyProductListingsExist() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            List<WebElement> products = driver.findElements(By.cssSelector(".product")); // update selector
            Assert.assertTrue(products.size() > 0, "No products displayed on Shop page.");
        } catch (Exception e) {
            Assert.fail("Product listing check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc017_clickFirstProductCard() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            List<WebElement> products = driver.findElements(By.cssSelector(".product")); // update selector
            if (!products.isEmpty()) {
                products.get(0).click();
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("product"), "Did not navigate to product page.");
            } else {
                Assert.fail("No products available to click.");
            }
        } catch (Exception e) {
            Assert.fail("Clicking product card failed: " + e.getMessage());
        }
    }

    @Test
    public void tc018_verifyFilterOptionsPresent() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            WebElement filter = driver.findElement(By.cssSelector(".filters")); // update if needed
            Assert.assertTrue(filter.isDisplayed(), "Filter section not visible.");
        } catch (Exception e) {
            Assert.fail("Filter check failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
