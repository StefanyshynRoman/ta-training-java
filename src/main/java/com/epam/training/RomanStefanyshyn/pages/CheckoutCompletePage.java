package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;
    private By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get success message")
    public String getSuccessMessage() {
        return driver.findElement(completeHeader).getText();
    }
}