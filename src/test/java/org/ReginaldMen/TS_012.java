package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_012 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    public void loginAndPlaceOrder() throws InterruptedException {
        driver.findElement(By.linkText("My account")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("testuser"); // Replace with valid test creds
        driver.findElement(By.id("password")).sendKeys("testpass");
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);

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

        WebElement phoneField = driver.findElement(By.id("billing_phone"));
        if (phoneField.getAttribute("value").isEmpty()) {
            phoneField.sendKeys("1234567890");
        }

        WebElement emailField = driver.findElement(By.id("billing_email"));
        if (emailField.getAttribute("value").isEmpty()) {
            emailField.sendKeys("user@example.com");
        }

        driver.findElement(By.id("place_order")).click();
        Thread.sleep(3000);
    }

    @Test
    public void tc042_verifyOrderConfirmationPage() {
        try {
            loginAndPlaceOrder();
            WebElement confirmationMsg = driver.findElement(By.cssSelector(".woocommerce-thankyou-order-received"));
            Assert.assertTrue(confirmationMsg.isDisplayed(), "Order confirmation message not visible.");
        } catch (Exception e) {
            Assert.fail("Order confirmation test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc043_viewOrdersFromMyAccount() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("testuser"); // Replace with valid test creds
            driver.findElement(By.id("password")).sendKeys("testpass");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Orders")).click();
            Thread.sleep(2000);

            WebElement ordersTable = driver.findElement(By.cssSelector(".woocommerce-orders-table"));
            Assert.assertTrue(ordersTable.isDisplayed(), "Orders table not visible under My Account.");
        } catch (Exception e) {
            Assert.fail("Viewing orders failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
