package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_014 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc047_registerNewUser() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Register")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("reg_username")).sendKeys("newuser");
            driver.findElement(By.id("reg_email")).sendKeys("newuser@example.com");
            driver.findElement(By.id("reg_password")).sendKeys("Test@123");
            driver.findElement(By.name("register")).click();
            Thread.sleep(2000);

            WebElement welcomeMessage = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content"));
            Assert.assertTrue(welcomeMessage.getText().contains("Welcome, newuser!"), "User registration failed.");
        } catch (Exception e) {
            Assert.fail("User registration test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc048_loginWithValidCredentials() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser"); // Replace with valid registered user
            driver.findElement(By.id("password")).sendKeys("Test@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            WebElement accountPage = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content"));
            Assert.assertTrue(accountPage.isDisplayed(), "Login with valid credentials failed.");
        } catch (Exception e) {
            Assert.fail("Login with valid credentials failed: " + e.getMessage());
        }
    }

    @Test
    public void tc049_loginWithInvalidCredentials() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("invaliduser");
            driver.findElement(By.id("password")).sendKeys("WrongPassword123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            WebElement errorMessage = driver.findElement(By.cssSelector(".woocommerce-error"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid credentials.");
        } catch (Exception e) {
            Assert.fail("Login with invalid credentials failed: " + e.getMessage());
        }
    }

    @Test
    public void tc050_passwordReset() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Lost your password?")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("user_login")).sendKeys("newuser@example.com");
            driver.findElement(By.id("wp-submit")).click();
            Thread.sleep(2000);

            WebElement resetMessage = driver.findElement(By.cssSelector(".woocommerce-message"));
            Assert.assertTrue(resetMessage.isDisplayed(), "Password reset message not displayed.");
        } catch (Exception e) {
            Assert.fail("Password reset failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
