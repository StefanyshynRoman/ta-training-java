package com.epam.training.RomanStefanyshyn.tests;

import com.epam.training.RomanStefanyshyn.pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.training.RomanStefanyshyn.base.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test
    @Description("UC-1 Checkout Flow (one item)")
    public void testCheckoutOneItem() {
        String product = "Sauce Labs Backpack";
        logger.info("Starting UC-1: Checkout Flow with one item: {}", product);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Logging in as standard_user");
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Adding {} to the cart", product);
        inventoryPage.addProductToCart(product);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        logger.info("Verifying item in cart");
        Assert.assertTrue(cartPage.isItemInCart(product), "Item is not present in the cart!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        logger.info("Filling checkout information");
        infoPage.fillInformation("Roman", "Stefanyshyn", "12345");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        logger.info("Finishing checkout");
        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String message = completePage.getSuccessMessage();
        logger.info("Verifying success message: {}", message);
        Assert.assertEquals(message, "Thank you for your order!", "Success message mismatch!");
    }


    @Test
    @Description("UC-2 Checkout Flow (several items)")
    public void testCheckoutMultipleItems() {
        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";
        logger.info("Starting UC-2: Checkout Flow with multiple items: {}, {}", product1, product2);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("UC-2 Logging in as standard_user");
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Adding multiple items to cart");
        inventoryPage.addProductToCart(product1);
        inventoryPage.addProductToCart(product2);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        logger.info("Verifying both items are in the cart");
        Assert.assertTrue(cartPage.isItemInCart(product1), "Product 1 is missing!");
        Assert.assertTrue(cartPage.isItemInCart(product2), "Product 2 is missing!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        logger.info("Filling checkout information");
        infoPage.fillInformation("Rom", "Stefan", "00-888");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        logger.info("Calculating total price and comparing with subtotal");
        double expectedSum = overviewPage.calculateSumOfItems();
        double actualSubtotal = overviewPage.getSubtotal();

        logger.info("Expected: {}, Actual: {}", expectedSum, actualSubtotal);
        Assert.assertEquals(actualSubtotal, expectedSum, "Final price does not equal the sum of product prices!");

        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        logger.info("Completing order and validating success");
        Assert.assertEquals(completePage.getSuccessMessage(), "Thank you for your order!", "Success message mismatch!");
    }

 @Test
 public void screenTest(){
     String product = "Sauce Labs Backpack";
     logger.info("Starting UC-1: for screen: {}", product);

     LoginPage loginPage = new LoginPage(driver);
     logger.info("Logging in as standard_user1");
     loginPage.login("standard_user1", "secret_sauce1");
     InventoryPage inventoryPage = new InventoryPage(driver);
     logger.info("Adding {} to the cart", product);
     inventoryPage.addProductToCart(product);
     inventoryPage.goToCart();

 }
}