package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;
    private By cartIcon = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Add product to cart: {0}")
    public void addProductToCart(String productName) {
        // Динамічний XPath для пошуку кнопки саме під вказаним товаром
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    @Step("Navigate to Cart")
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}