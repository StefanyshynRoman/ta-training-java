package com.epam.training.RomanStefanyshyn.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public static WebDriver createDriver(String browser) {

        return switch (browser.toLowerCase()) {
            case "chrome" -> createChromeDriver();
            case "firefox" -> createFirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    // ================= CHROME =================
    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        maximizeWindow(driver);

        return driver;
    }

    // ================= FIREFOX =================
    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("-private");

        options.addPreference("signon.rememberSignons", false);
        options.addPreference("browser.shell.checkDefaultBrowser", false);

        WebDriver driver = new FirefoxDriver(options);
        maximizeWindow(driver);

        return driver;
    }

    private static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }
}