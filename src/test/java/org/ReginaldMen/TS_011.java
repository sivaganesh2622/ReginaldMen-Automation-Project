package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_011 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    public void addProductAndGoToCheckout() throws InterruptedException {
        driver.findElement(By.linkText("Shop")).click();
        Thread.sleep(2000);
        driver.findElements(By.cssSelector(".product")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".single_add_to_cart_button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".cart-icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Proceed to checkout")).click();
        Thread.sleep(2000);
    }

    @Test
    public void tc039_checkoutPageLoadsSuccessfully() {
        try {
            addProductAndGoToCheckout();
            WebElement checkoutTitle = driver.findElement(By.cssSelector(".checkout-title"));
            Assert.assertTrue(checkoutTitle.isDisplayed(), "Checkout page did not load properly.");
        } catch (Exception e) {
            Assert.fail("Checkout page load failed: " + e.getMessage());
        }
    }

    @Test
    public void tc040_validateRequiredFieldsOnCheckout() {
        try {
            addProductAndGoToCheckout();
            driver.findElement(By.id("place_order")).click(); // Try placing order without filling fields
            Thread.sleep(2000);
            WebElement errorNotice = driver.findElement(By.cssSelector(".woocommerce-error"));
            Assert.assertTrue(errorNotice.isDisplayed(), "Required field validation failed.");
        } catch (Exception e) {
            Assert.fail("Required field validation failed: " + e.getMessage());
        }
    }

    @Test
    public void tc041_completeCheckoutWithValidDetails() {
        try {
            addProductAndGoToCheckout();

            driver.findElement(By.id("billing_first_name")).sendKeys("John");
            driver.findElement(By.id("billing_last_name")).sendKeys("Doe");
            driver.findElement(By.id("billing_address_1")).sendKeys("123 Elm Street");
            driver.findElement(By.id("billing_city")).sendKeys("New York");
            driver.findElement(By.id("billing_postcode")).sendKeys("10001");
            driver.findElement(By.id("billing_phone")).sendKeys("1234567890");
            driver.findElement(By.id("billing_email")).sendKeys("john.doe@example.com");

            driver.findElement(By.id("place_order")).click();
            Thread.sleep(3000);

            WebElement confirmation = driver.findElement(By.cssSelector(".woocommerce-thankyou-order-received"));
            Assert.assertTrue(confirmation.isDisplayed(), "Order confirmation not shown.");
        } catch (Exception e) {
            Assert.fail("Checkout with valid details failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
