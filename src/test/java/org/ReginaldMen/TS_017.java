package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_017 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc057_viewAndEditProfileDetails() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("Test@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Account details")).click();
            Thread.sleep(1000);

            WebElement firstNameField = driver.findElement(By.id("account_first_name"));
            WebElement lastNameField = driver.findElement(By.id("account_last_name"));

            firstNameField.clear();
            firstNameField.sendKeys("JohnUpdated");
            lastNameField.clear();
            lastNameField.sendKeys("DoeUpdated");

            driver.findElement(By.name("save_account_details")).click();
            Thread.sleep(2000);

            WebElement confirmationMessage = driver.findElement(By.cssSelector(".woocommerce-message"));
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Profile update failed.");
        } catch (Exception e) {
            Assert.fail("View and edit profile details test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc058_changeUserPassword() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("Test@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Account details")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("password_current")).sendKeys("Test@123");
            driver.findElement(By.id("password_1")).sendKeys("NewPass@123");
            driver.findElement(By.id("password_2")).sendKeys("NewPass@123");

            driver.findElement(By.name("save_account_details")).click();
            Thread.sleep(2000);

            WebElement passwordChangeConfirmation = driver.findElement(By.cssSelector(".woocommerce-message"));
            Assert.assertTrue(passwordChangeConfirmation.isDisplayed(), "Password change failed.");
        } catch (Exception e) {
            Assert.fail("Change user password test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc059_verifyProfileDetailsAfterChange() {
        try {
            driver.findElement(By.linkText("My account")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("username")).sendKeys("newuser");
            driver.findElement(By.id("password")).sendKeys("NewPass@123");
            driver.findElement(By.name("login")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Account details")).click();
            Thread.sleep(1000);

            WebElement firstNameField = driver.findElement(By.id("account_first_name"));
            WebElement lastNameField = driver.findElement(By.id("account_last_name"));

            Assert.assertEquals(firstNameField.getAttribute("value"), "JohnUpdated", "First name did not update correctly.");
            Assert.assertEquals(lastNameField.getAttribute("value"), "DoeUpdated", "Last name did not update correctly.");
        } catch (Exception e) {
            Assert.fail("Verify profile details after change test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
