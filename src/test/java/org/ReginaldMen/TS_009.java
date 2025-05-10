package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class TS_009 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    @Test
    public void tc033_addSingleProductToCart() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);

            List<WebElement> products = driver.findElements(By.cssSelector(".product"));
            Assert.assertTrue(products.size() > 0, "No products found to add to cart.");
            products.get(0).click();
            Thread.sleep(2000);

            WebElement addToCartButton = driver.findElement(By.cssSelector(".single_add_to_cart_button"));
            addToCartButton.click();
            Thread.sleep(2000);

            WebElement cartCount = driver.findElement(By.cssSelector(".cart-count"));
            Assert.assertTrue(cartCount.getText().contains("1"), "Product not added to cart.");
        } catch (Exception e) {
            Assert.fail("Failed to add product to cart: " + e.getMessage());
        }
    }

    @Test
    public void tc034_addMultipleQuantitiesToCart() {
        try {
            driver.findElement(By.linkText("Shop")).click();
            Thread.sleep(2000);

            driver.findElements(By.cssSelector(".product")).get(0).click();
            Thread.sleep(2000);

            WebElement quantityBox = driver.findElement(By.name("quantity"));
            quantityBox.clear();
            quantityBox.sendKeys("3");

            WebElement addToCartButton = driver.findElement(By.cssSelector(".single_add_to_cart_button"));
            addToCartButton.click();
            Thread.sleep(2000);

            WebElement cartCount = driver.findElement(By.cssSelector(".cart-count"));
            Assert.assertTrue(cartCount.getText().contains("3"), "Incorrect quantity added to cart.");
        } catch (Exception e) {
            Assert.fail("Failed to add multiple quantities: " + e.getMessage());
        }
    }

    @Test
    public void tc035_verifyCartIconNavigation() {
        try {
            WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
            cartIcon.click();
            Thread.sleep(2000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("cart"), "Did not navigate to cart page.");
        } catch (Exception e) {
            Assert.fail("Cart icon navigation failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
