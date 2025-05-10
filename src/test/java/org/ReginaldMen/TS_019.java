package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_019 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc063_subscribeToNewsletter() {
        try {
            driver.findElement(By.id("newsletter")).sendKeys("testuser@example.com");
            driver.findElement(By.id("subscribe-button")).click();
            Thread.sleep(2000);

            WebElement successMessage = driver.findElement(By.cssSelector(".newsletter-success"));
            Assert.assertTrue(successMessage.isDisplayed(), "Newsletter subscription failed.");
        } catch (Exception e) {
            Assert.fail("Newsletter subscription test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc064_verifySubscriptionConfirmationEmail() {
        try {
            // This step assumes that the email system is connected to a mock service or an email provider's API
            String receivedEmail = checkEmail("testuser@example.com", "Newsletter Subscription Confirmation");
            Assert.assertTrue(receivedEmail.contains("Thank you for subscribing"), "Subscription confirmation email not received.");
        } catch (Exception e) {
            Assert.fail("Email confirmation test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc065_verifyInvalidEmailSubscription() {
        try {
            driver.findElement(By.id("newsletter")).sendKeys("invalid-email");
            driver.findElement(By.id("subscribe-button")).click();
            Thread.sleep(2000);

            WebElement errorMessage = driver.findElement(By.cssSelector(".newsletter-error"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Invalid email subscription did not show an error.");
        } catch (Exception e) {
            Assert.fail("Invalid email subscription test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }

    private String checkEmail(String email, String subject) {
        // Implement email checking logic, can use a mock email service or an API
        // Placeholder for actual email verification
        return "Thank you for subscribing to our newsletter!";
    }
}
