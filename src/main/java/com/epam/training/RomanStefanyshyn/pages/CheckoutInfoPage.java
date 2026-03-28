package com.epam.training.RomanStefanyshyn.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private By firstNameInput = By.cssSelector("#first-name");
    private By lastNameInput = By.cssSelector("#last-name");
    private By zipInput = By.cssSelector("#postal-code");
    private By continueButton = By.cssSelector("#continue");

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