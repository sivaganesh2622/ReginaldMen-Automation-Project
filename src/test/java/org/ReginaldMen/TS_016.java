package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_016 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc054_addProductReview() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            driver.findElements(By.cssSelector(".product")).get(0).click();
            Thread.sleep(2000);

            WebElement reviewSection = driver.findElement(By.id("reviews"));
            reviewSection.click();
            Thread.sleep(1000);

            driver.findElement(By.id("comment")).sendKeys("Great product! Highly recommend.");
            driver.findElement(By.id("rating")).click(); // Assume 5-star rating
            driver.findElement(By.id("submit")).click();
            Thread.sleep(2000);

            WebElement reviewConfirmation = driver.findElement(By.cssSelector(".review-message"));
            Assert.assertTrue(reviewConfirmation.isDisplayed(), "Review submission failed.");
        } catch (Exception e) {
            Assert.fail("Add product review test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc055_submitProductRating() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            driver.findElements(By.cssSelector(".product")).get(0).click();
            Thread.sleep(2000);

            WebElement ratingStars = driver.findElement(By.id("rating"));
            ratingStars.click(); // Assuming clicking on 5-star rating

            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();
            Thread.sleep(2000);

            WebElement ratingConfirmation = driver.findElement(By.cssSelector(".woocommerce-review-rating"));
            Assert.assertTrue(ratingConfirmation.isDisplayed(), "Rating submission failed.");
        } catch (Exception e) {
            Assert.fail("Submit product rating test failed: " + e.getMessage());
        }
    }

    @Test
    public void tc056_verifyReviewAndRatingOnProductPage() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);
            driver.findElements(By.cssSelector(".product")).get(0).click();
            Thread.sleep(2000);

            WebElement reviewSection = driver.findElement(By.id("reviews"));
            Assert.assertTrue(reviewSection.isDisplayed(), "Review section not visible.");
            WebElement ratingSection = driver.findElement(By.cssSelector(".woocommerce-review-rating"));
            Assert.assertTrue(ratingSection.isDisplayed(), "Rating section not visible.");
        } catch (Exception e) {
            Assert.fail("Review and rating verification failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
