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

public class TS_005 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc019_verifyProductImageDisplayed() {
        try {
            // Navigate to product page
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

            // Click the first product to go to the details page
            driver.findElements(By.cssSelector(".product")).get(0).click();

            // Check if product image is visible
            WebElement productImage = driver.findElement(By.cssSelector(".product-image"));
            Assert.assertTrue(productImage.isDisplayed(), "Product image is not displayed.");
        } catch (Exception e) {
            Assert.fail("Product image verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc020_verifyProductDescription() {
        try {
            // Navigate to product page
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

            // Click the first product to go to the details page
            driver.findElements(By.cssSelector(".product")).get(0).click();

            // Check if product description is displayed and matches expected
            WebElement productDescription = driver.findElement(By.cssSelector(".product-description"));
            Assert.assertTrue(productDescription.isDisplayed(), "Product description is not visible.");
            String expectedDescription = "This is a sample product description.";
            String actualDescription = productDescription.getText();
            Assert.assertEquals(actualDescription, expectedDescription, "Product description does not match.");
        } catch (Exception e) {
            Assert.fail("Product description verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc021_verifyProductPrice() {
        try {
            // Navigate to product page
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

            // Click the first product to go to the details page
            driver.findElements(By.cssSelector(".product")).get(0).click();

            // Check if product price is displayed and matches expected
            WebElement productPrice = driver.findElement(By.cssSelector(".product-price"));
            Assert.assertTrue(productPrice.isDisplayed(), "Product price is not visible.");
            String expectedPrice = "$99.99";
            String actualPrice = productPrice.getText();
            Assert.assertEquals(actualPrice, expectedPrice, "Product price does not match.");
        } catch (Exception e) {
            Assert.fail("Product price verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc022_verifyAddToCartFunctionality() {
        try {
            // Navigate to product page
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

            // Click the first product to go to the details page
            driver.findElements(By.cssSelector(".product")).get(0).click();

            // Add product to cart
            WebElement addToCartButton = driver.findElement(By.cssSelector(".add-to-cart-button"));
            addToCartButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-notification"))); // Assuming there's a cart notification

            // Verify the cart notification or cart icon to ensure the product was added
            WebElement cartNotification = driver.findElement(By.cssSelector(".cart-notification"));
            Assert.assertTrue(cartNotification.isDisplayed(), "Add to Cart notification not displayed.");
        } catch (Exception e) {
            Assert.fail("Add to Cart functionality failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
