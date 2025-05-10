package org.ReginaldMen;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TS_003 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc011_verifyFooterIsVisible() {
        try {
            WebElement footer = driver.findElement(By.tagName("footer"));
            Assert.assertTrue(footer.isDisplayed(), "Footer is not visible on the page.");
        } catch (Exception e) {
            Assert.fail("Footer visibility check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc012_verifyPrivacyPolicyLink() {
        try {
            WebElement privacyLink = driver.findElement(By.linkText("Privacy Policy"));
            Assert.assertTrue(privacyLink.isDisplayed(), "Privacy Policy link is not visible.");
        } catch (Exception e) {
            Assert.fail("Privacy Policy link check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc013_verifyTermsAndConditionsLink() {
        try {
            WebElement termsLink = driver.findElement(By.linkText("Terms & Conditions"));
            Assert.assertTrue(termsLink.isDisplayed(), "Terms & Conditions link is not visible.");
        } catch (Exception e) {
            Assert.fail("Terms & Conditions link check failed: " + e.getMessage());
        }
    }

    @Test
    public void tc014_verifySocialMediaIcons() {
        try {
            WebElement facebookIcon = driver.findElement(By.cssSelector("a[href*='facebook.com']"));
            WebElement instagramIcon = driver.findElement(By.cssSelector("a[href*='instagram.com']"));
            Assert.assertTrue(facebookIcon.isDisplayed(), "Facebook icon is not visible.");
            Assert.assertTrue(instagramIcon.isDisplayed(), "Instagram icon is not visible.");
        } catch (Exception e) {
            Assert.fail("Social media icon check failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
