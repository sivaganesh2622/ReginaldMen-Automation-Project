package org.ReginaldMen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TS_010 extends BaseClass {

    @BeforeMethod
    public void beforeMethod() {
        launchBrowser();
        windowMaximize();
        launchUrl("https://reginaldmen.com/");
    }

    public void addProductToCart() throws InterruptedException {
        driver.findElement(By.linkText("Shop")).click();
        Thread.sleep(2000);
        List<WebElement> products = driver.findElements(By.cssSelector(".product"));
        if (products.isEmpty()) {
            Assert.fail("No products available to test cart.");
        }
        products.get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".single_add_to_cart_button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".cart-icon")).click();
        Thread.sleep(2000);
    }

    @Test
    public void tc036_updateCartQuantity() {
        try {
            addProductToCart();
            WebElement quantityBox = driver.findElement(By.cssSelector("input.qty"));
            quantityBox.clear();
            quantityBox.sendKeys("2");

            driver.findElement(By.name("update_cart")).click();
            Thread.sleep(2000);

            WebElement updatedQty = driver.findElement(By.cssSelector("input.qty"));
            Assert.assertEquals(updatedQty.getAttribute("value"), "2", "Cart quantity not updated.");
        } catch (Exception e) {
            Assert.fail("Updating cart quantity failed: " + e.getMessage());
        }
    }

    @Test
    public void tc037_removeItemFromCart() {
        try {
            addProductToCart();
            driver.findElement(By.cssSelector(".remove")).click(); // Update selector if needed
            Thread.sleep(2000);

            WebElement cartEmptyMsg = driver.findElement(By.cssSelector(".cart-empty"));
            Assert.assertTrue(cartEmptyMsg.isDisplayed(), "Cart not empty after removing item.");
        } catch (Exception e) {
            Assert.fail("Removing item from cart failed: " + e.getMessage());
        }
    }

    @Test
    public void tc038_verifyTotalPriceCalculation() {
        try {
            addProductToCart();

            WebElement quantityBox = driver.findElement(By.cssSelector("input.qty"));
            quantityBox.clear();
            quantityBox.sendKeys("2");

            driver.findElement(By.name("update_cart")).click();
            Thread.sleep(2000);

            WebElement unitPriceElem = driver.findElement(By.cssSelector(".product-price .amount"));
            WebElement totalElem = driver.findElement(By.cssSelector(".product-subtotal .amount"));

            double unitPrice = Double.parseDouble(unitPriceElem.getText().replace("$", "").trim());
            double expectedTotal = unitPrice * 2;

            double actualTotal = Double.parseDouble(totalElem.getText().replace("$", "").trim());
            Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total price calculation incorrect.");
        } catch (Exception e) {
            Assert.fail("Total price verification failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeEntireBrowser();
    }
}
