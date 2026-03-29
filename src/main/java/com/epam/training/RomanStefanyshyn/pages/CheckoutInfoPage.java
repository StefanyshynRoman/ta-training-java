package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private final By firstNameInput = By.cssSelector("#first-name");
    private final By lastNameInput = By.cssSelector("#last-name");
    private final By zipInput = By.cssSelector("#postal-code");
    private final By continueButton = By.cssSelector("#continue");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill in checkout information: {0} {1}, {2}")
    public void fillInformation(String firstName, String lastName, String zip) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(zipInput, zip);
        click(continueButton);
    }
}