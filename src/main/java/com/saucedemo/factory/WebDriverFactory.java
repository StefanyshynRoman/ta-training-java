package com.saucedemo.factory;

/**
 * @author romanpz051@gmail.com on 24.03.2026.
 * @project ta-training-java
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();

                // 1. Запуск в режимі інкогніто — це найчастіше вирішує проблему,
                // бо браузер не підтягує жодних акаунтів Google
                options.addArguments("--incognito");

                // 2. Додаткові прапори для стабільності
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");

                // 3. Вимикаємо менеджер паролів через Preferences
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false); // вимикає службу паролів
                prefs.put("profile.password_manager_enabled", false); // вимикає сам менеджер
                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options);
            }
            case "firefox" -> driver = new FirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}