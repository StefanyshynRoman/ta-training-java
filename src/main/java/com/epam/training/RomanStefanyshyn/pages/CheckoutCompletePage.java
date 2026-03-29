package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get success message")
    public String getSuccessMessage() {
        return getText(completeHeader);
    }
}