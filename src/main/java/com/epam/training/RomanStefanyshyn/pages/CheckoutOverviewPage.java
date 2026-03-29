package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final By itemPrices = By.cssSelector(".inventory_item_price");
    private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By finishButton = By.cssSelector("#finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }


    @Step("Calculate sum of items in the cart")
    public double calculateSumOfItems() {
        List<WebElement> prices = findAll(itemPrices);
        double sum = 0;
        for (WebElement priceElement : prices) {
            sum += parsePrice(priceElement.getText());
        }
        return sum;
    }

    @Step("Get displayed subtotal amount")
    public double getSubtotal() {
        return parsePrice(getText(subtotalLabel));
    }

    @Step("Click Finish button")
    public void finishCheckout() {
        click(finishButton);
    }
}