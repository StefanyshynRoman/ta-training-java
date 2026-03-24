package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage {
    private WebDriver driver;
    private By firstNameInput = By.cssSelector("#first-name");
    private By lastNameInput = By.cssSelector("#last-name");
    private By zipInput = By.cssSelector("#postal-code");
    private By continueButton = By.cssSelector("#continue");

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill in checkout information: {0} {1}, {2}")
    public void fillInformation(String firstName, String lastName, String zip) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(continueButton).click();
    }
}