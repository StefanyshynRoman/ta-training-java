package com.epam.training.RomanStefanyshyn.tests;

import com.epam.training.RomanStefanyshyn.base.BaseTest;
import com.epam.training.RomanStefanyshyn.pages.*;
import com.epam.training.RomanStefanyshyn.utils.ConfigReader;
import com.epam.training.RomanStefanyshyn.utils.TestData;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    @Description("UC-1 Checkout Flow (one item)")
    public void testCheckoutOneItem() {
        String product = TestData.PRODUCT_BACKPACK;
        logger.info("Starting UC-1: Checkout Flow with one item: {}", product);

        LoginPage loginPage = new LoginPage(getDriver());
        logger.info("UC-1: Logging in as standard_user");
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        logger.info("UC-1: Adding {} to the cart", product);
        inventoryPage.addProductToCart(product);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(getDriver());
        logger.info("UC-1: Verifying item in cart");
        Assert.assertTrue(cartPage.isItemInCart(product), "Item is not present in the cart!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(getDriver());
        logger.info("UC-1: Filling checkout information UC-1");
        infoPage.fillInformation(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.ZIP);

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(getDriver());
        logger.info("UC-1: Finishing checkout");
        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(getDriver());
        String message = completePage.getSuccessMessage();
        logger.info("UC-1: Verifying success message: {}", message);
        Assert.assertEquals(message, "Thank you for your order!", "Success message mismatch!");
    }


    @Test
    @Description("UC-2 Checkout Flow (several items)")
    public void testCheckoutMultipleItems() {
        String product1 = TestData.PRODUCT_BACKPACK;
        String product2 = TestData.PRODUCT_BIKE_LIGHT;
        logger.info("Starting UC-2: Checkout Flow with multiple items: {}, {}", product1, product2);

        LoginPage loginPage = new LoginPage(getDriver());
        logger.info("UC-2: Logging in as standard_user");
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        logger.info("UC-2: Adding multiple items to cart");
        inventoryPage.addProductToCart(product1);
        inventoryPage.addProductToCart(product2);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(getDriver());
        logger.info("UC-2: Verifying both items are in the cart");
        Assert.assertTrue(cartPage.isItemInCart(product1), "Product 1 is missing!");
        Assert.assertTrue(cartPage.isItemInCart(product2), "Product 2 is missing!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(getDriver());
        logger.info("UC-2: Filling checkout information UC-2");
        infoPage.fillInformation(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.ZIP);

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(getDriver());
        logger.info("UC-2: Calculating total price and comparing with subtotal");
        double expectedSum = overviewPage.calculateSumOfItems();
        double actualSubtotal = overviewPage.getSubtotal();

        logger.info("UC-2: Expected: {}, Actual: {}", expectedSum, actualSubtotal);
        Assert.assertEquals(actualSubtotal, expectedSum, "Final price does not equal the sum of product prices!");

        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(getDriver());
        logger.info("UC-2: Completing order and validating success");
        Assert.assertEquals(completePage.getSuccessMessage(), "Thank you for your order!", "Success message mismatch!");
    }
    /*
      for test screen
      need to be uncommented
     */

// @Test
// public void screenTest(){
//     String product = "Sauce Labs Backpack";
//     logger.info("Starting UC-1: for screen: {}", product);
//
//     LoginPage loginPage = new LoginPage(getDriver());
//
//     logger.info("Logging in as standard_user1");  // invalid credentials
//     loginPage.login("standard_user1", "secret_sauce1");  // invalid credentials
//     InventoryPage inventoryPage = new InventoryPage(getDriver());
//     logger.info("Adding {} to the cart", product);
//     inventoryPage.addProductToCart(product);
//     inventoryPage.goToCart();
//}
}