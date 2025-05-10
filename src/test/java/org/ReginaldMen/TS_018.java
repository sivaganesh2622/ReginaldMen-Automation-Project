package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_018 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc060_viewOrderHistory() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("NewPass@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Orders")).click();
            Thread.sleep(1000);

            WebElement ordersSection = driver.findElement(By.cssSelector(".order-history"));
            Assert.assertTrue(ordersSection.isDisplayed(), "Order history is not visible.");
        } catch (Exception e) {
            Assert.fail("View order history test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc061_verifyOrderStatusTracking() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("NewPass@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Orders")).click();
            Thread.sleep(1000);

            WebElement orderStatus = driver.findElement(By.cssSelector(".order-status"));
            Assert.assertTrue(orderStatus.isDisplayed(), "Order status is not displayed.");
            Assert.assertTrue(orderStatus.getText().contains("Completed"), "Order status is incorrect.");
        } catch (Exception e) {
            Assert.fail("Order status tracking test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc062_verifyOrderDetailsPage() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("NewPass@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Orders")).click();
            Thread.sleep(1000);

            WebElement firstOrder = driver.findElement(By.cssSelector(".order-details a"));
            firstOrder.click();
            Thread.sleep(1000);

            WebElement orderDetailsSection = driver.findElement(By.cssSelector(".order-details"));
            Assert.assertTrue(orderDetailsSection.isDisplayed(), "Order details page not visible.");
        } catch (Exception e) {
            Assert.fail("Verify order details page test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
