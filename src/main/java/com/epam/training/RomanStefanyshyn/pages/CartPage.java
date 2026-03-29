package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By cartItems = By.cssSelector(".inventory_item_name");
    private final By checkoutButton = By.cssSelector("#checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if product is in cart: {0}")
    public boolean isItemInCart(String productName) {
        return findAll(cartItems)
                .stream()
                .anyMatch(item -> item.getText().equals(productName));
    }

    @Step("Proceed to Checkout")
    public void proceedToCheckout() {
      click(checkoutButton);
    }
}