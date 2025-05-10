package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_015 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    public void addProductToCartAndProceedToCheckout() throws InterruptedException {
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
    public void tc051_verifyPaymentGatewayVisible() {
        try {
            addProductToCartAndProceedToCheckout();
            WebElement paymentSection = driver.findElement(By.id("payment"));
            Assert.assertTrue(paymentSection.isDisplayed(), "Payment gateway section is not visible.");
        } catch (Exception e) {
            Assert.fail("Payment gateway visibility test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc052_verifySuccessfulOrderCompletionThroughPayment() {
        try {
            addProductToCartAndProceedToCheckout();

            driver.findElement(By.id("billing_first_name")).sendKeys("John");
            driver.findElement(By.id("billing_last_name")).sendKeys("Doe");
            driver.findElement(By.id("billing_address_1")).sendKeys("123 Elm Street");
            driver.findElement(By.id("billing_city")).sendKeys("New York");
            driver.findElement(By.id("billing_postcode")).sendKeys("10001");
            driver.findElement(By.id("billing_phone")).sendKeys("1234567890");
            driver.findElement(By.id("billing_email")).sendKeys("john.doe@example.com");

            // Simulate payment method selection
            WebElement paymentMethod = driver.findElement(By.id("payment_method_cod"));
            paymentMethod.click();

            driver.findElement(By.id("place_order")).click();
            Thread.sleep(3000);

            WebElement confirmation = driver.findElement(By.cssSelector(".woocommerce-thankyou-order-received"));
            Assert.assertTrue(confirmation.isDisplayed(), "Order completion failed after payment.");
        } catch (Exception e) {
            Assert.fail("Payment and order completion failed: " + e.getMessage());
        }
    }

    @Test
    public void tc053_verifyFailedPaymentScenario() {
        try {
            addProductToCartAndProceedToCheckout();

            driver.findElement(By.id("billing_first_name")).sendKeys("John");
            driver.findElement(By.id("billing_last_name")).sendKeys("Doe");
            driver.findElement(By.id("billing_address_1")).sendKeys("123 Elm Street");
            driver.findElement(By.id("billing_city")).sendKeys("New York");
            driver.findElement(By.id("billing_postcode")).sendKeys("10001");
            driver.findElement(By.id("billing_phone")).sendKeys("1234567890");
            driver.findElement(By.id("billing_email")).sendKeys("john.doe@example.com");

            // Simulate an invalid payment scenario
            WebElement invalidPaymentMethod = driver.findElement(By.id("payment_method_bacs"));
            invalidPaymentMethod.click();

            driver.findElement(By.id("place_order")).click();
            Thread.sleep(3000);

            WebElement errorMessage = driver.findElement(By.cssSelector(".woocommerce-error"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message not shown for failed payment.");
        } catch (Exception e) {
            Assert.fail("Failed payment scenario failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
