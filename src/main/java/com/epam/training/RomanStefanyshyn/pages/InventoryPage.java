package com.epam.training.RomanStefanyshyn.pages;

import com.epam.training.RomanStefanyshyn.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private By cartIcon = By.cssSelector(".shopping_cart_link");
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add product to cart: {0}")
    public void addProductToCart(String productName) {
        By addButton =By.xpath(String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName));
      click(addButton);
    }

    @Step("Navigate to Cart")
    public void goToCart() {
       click(cartIcon);
    }
}