package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TS_008 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc030_loginWithValidCredentials() {
        try {
            driver.findElement(By.linkText("Login")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            driver.findElement(By.id("username")).sendKeys("validUser"); // Use valid test creds
            driver.findElement(By.id("password")).sendKeys("validPassword");
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.urlContains("dashboard"));
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        } catch (Exception e) {
            Assert.fail("Exception during valid login: " + e.getMessage());
        }
    }

    @Test
    public void tc031_loginWithInvalidCredentials() {
        try {
            driver.findElement(By.linkText("Login")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            driver.findElement(By.id("username")).sendKeys("wrongUser");
            driver.findElement(By.id("password")).sendKeys("wrongPass");
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message")));
            WebElement error = driver.findElement(By.cssSelector(".error-message"));
            Assert.assertTrue(error.isDisplayed(), "Error message not displayed for invalid login.");
        } catch (Exception e) {
            Assert.fail("Exception during invalid login: " + e.getMessage());
        }
    }

    @Test
    public void tc032_registrationFormValidation() {
        try {
            driver.findElement(By.linkText("Register")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            driver.findElement(By.id("username")).sendKeys("newUser123");
            driver.findElement(By.id("email")).sendKeys("newuser123@example.com");
            driver.findElement(By.id("password")).sendKeys("StrongPass!123");
            driver.findElement(By.id("confirm-password")).sendKeys("StrongPass!123");
            driver.findElement(By.id("register-button")).click();

            wait.until(ExpectedConditions.urlContains("welcome"));
            Assert.assertTrue(driver.getCurrentUrl().contains("welcome"), "Registration did not complete successfully.");
        } catch (Exception e) {
            Assert.fail("Exception during registration: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
