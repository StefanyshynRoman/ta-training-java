package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartItems = By.cssSelector(".inventory_item_name");
    private By checkoutButton = By.cssSelector("#checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check if product is in cart: {0}")
    public boolean isItemInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        return items.stream().anyMatch(item -> item.getText().equals(productName));
    }

    @Step("Proceed to Checkout")
    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}