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

public class TS_006 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc023_verifyCartIconPresent() {
        try {
            // Navigate to the shop page and add an item to the cart
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));
            driver.findElements(By.cssSelector(".product")).get(0).click();
            driver.findElement(By.cssSelector(".add-to-cart-button")).click();
            
            // Wait for the cart icon to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-icon")));
            WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
            Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is not visible after adding product.");
        } catch (Exception e) {
            Assert.fail("Cart icon verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc024_verifyProductInCart() {
        try {
            // Navigate to the shop page and add an item to the cart
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));
            driver.findElements(By.cssSelector(".product")).get(0).click();
            driver.findElement(By.cssSelector(".add-to-cart-button")).click();
            
            // Click on the cart icon to view the cart
            driver.findElement(By.cssSelector(".cart-icon")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-items")));
            
            // Verify that the product is added to the cart
            List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));
            Assert.assertTrue(cartItems.size() > 0, "No products found in the cart.");
        } catch (Exception e) {
            Assert.fail("Product in cart verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc025_verifyCartTotalPrice() {
        try {
            // Navigate to the shop page and add an item to the cart
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));
            driver.findElements(By.cssSelector(".product")).get(0).click();
            driver.findElement(By.cssSelector(".add-to-cart-button")).click();
            
            // Click on the cart icon to view the cart
            driver.findElement(By.cssSelector(".cart-icon")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-total")));
            
            // Verify that the cart total matches the expected price
            WebElement cartTotal = driver.findElement(By.cssSelector(".cart-total"));
            String expectedTotal = "$99.99";  // Replace with actual expected value
            String actualTotal = cartTotal.getText();
            Assert.assertEquals(actualTotal, expectedTotal, "Cart total price does not match.");
        } catch (Exception e) {
            Assert.fail("Cart total price verification failed: " + e.getMessage());
        }
    }

    @Test
    public void tc026_verifyCheckoutButtonFunctionality() {
        try {
            // Navigate to the shop page and add an item to the cart
            driver.findElement(By.linkText("Shop")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));
            driver.findElements(By.cssSelector(".product")).get(0).click();
            driver.findElement(By.cssSelector(".add-to-cart-button")).click();
            
            // Click on the cart icon to view the cart
            driver.findElement(By.cssSelector(".cart-icon")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout-button")));
            
            // Verify that the checkout button is visible and clickable
            WebElement checkoutButton = driver.findElement(By.cssSelector(".checkout-button"));
            Assert.assertTrue(checkoutButton.isDisplayed(), "Checkout button is not visible.");
            checkoutButton.click();
            
            // Verify if the checkout page loads
            wait.until(ExpectedConditions.urlContains("checkout"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("checkout"), "Did not navigate to checkout page.");
        } catch (Exception e) {
            Assert.fail("Checkout button functionality failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
