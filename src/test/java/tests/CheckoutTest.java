package tests;


import com.saucedemo.pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test
    @Description("UC-1 Checkout Flow (one item)")
    public void testCheckoutOneItem() {
        String product = "Sauce Labs Backpack";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(product);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isItemInCart(product), "Item is not present in the cart!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.fillInformation("John", "Doe", "12345");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(completePage.getSuccessMessage(), "Thank you for your order!", "Success message mismatch!");
    }

    @Test
    @Description("UC-2 Checkout Flow (several items)")
    public void testCheckoutMultipleItems() {
        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(product1);
        inventoryPage.addProductToCart(product2);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isItemInCart(product1), "Product 1 is missing!");
        Assert.assertTrue(cartPage.isItemInCart(product2), "Product 2 is missing!");
        cartPage.proceedToCheckout();

        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.fillInformation("Jane", "Smith", "98765");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        double expectedSum = overviewPage.calculateSumOfItems();
        double actualSubtotal = overviewPage.getSubtotal();

        // Валідація ціни
        Assert.assertEquals(actualSubtotal, expectedSum, "Final price does not equal the sum of product prices!");

        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(completePage.getSuccessMessage(), "Thank you for your order!", "Success message mismatch!");
    }
}