package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutOverviewPage {
    private WebDriver driver;
    private By itemPrices = By.cssSelector(".inventory_item_price");
    private By subtotalLabel = By.cssSelector(".summary_subtotal_label");
    private By finishButton = By.cssSelector("#finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Calculate sum of items in the cart")
    public double calculateSumOfItems() {
        List<WebElement> prices = driver.findElements(itemPrices);
        double sum = 0;
        for (WebElement priceElement : prices) {
            // Видаляємо знак "$" і перетворюємо в число
            sum += Double.parseDouble(priceElement.getText().replace("$", ""));
        }
        return sum;
    }

    @Step("Get displayed subtotal amount")
    public double getSubtotal() {
        String subtotalText = driver.findElement(subtotalLabel).getText();
        // Текст виглядає як "Item total: $29.99"
        return Double.parseDouble(subtotalText.split("\\$")[1]);
    }

    @Step("Click Finish button")
    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }
}