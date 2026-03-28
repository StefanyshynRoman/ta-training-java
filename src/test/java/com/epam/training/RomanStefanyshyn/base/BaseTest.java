package com.epam.training.RomanStefanyshyn.base;

import com.epam.training.RomanStefanyshyn.factory.WebDriverFactory;
import com.epam.training.RomanStefanyshyn.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        ThreadContext.put("browserName", browser.toUpperCase());
        logger.info("Starting setup for browser: " + browser);
        driver = WebDriverFactory.createDriver(browser);
        driver.get(ConfigReader.get("base.url"));    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ThreadContext.clearAll();
    }
    public WebDriver getDriver() {
        return driver;
    }
}