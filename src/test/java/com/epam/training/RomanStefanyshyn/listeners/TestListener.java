package com.epam.training.RomanStefanyshyn.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        // 🔴 тут важливо: каст до BaseTest
        WebDriver driver = ((com.epam.training.RomanStefanyshyn.base.BaseTest) testClass).getDriver();

        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment("Screenshot on failure",
                    new ByteArrayInputStream(screenshot));
        }
    }
}