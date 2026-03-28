# 🧪 Test Automation Framework – SauceDemo (EPAM Task)

## 📌 Project Overview

This project implements automated end-to-end UI tests for the checkout flow on **https://www.saucedemo.com/**.

The framework is built using:

* **Java 22**
* **Selenium WebDriver**
* **TestNG**
* **Allure Reporting**
* **Maven**

The goal is to validate the checkout process for one and multiple items, following best practices in test automation.

---

## ✅ Test Scenarios

### 🔹 UC-1: Checkout Flow (Single Item)

* Login with `standard_user`
* Add one product to cart
* Verify item in cart
* Complete checkout
* Validate success message

---

### 🔹 UC-2: Checkout Flow (Multiple Items)

* Login with `standard_user`
* Add two products to cart
* Verify both items in cart
* Complete checkout
* Validate total price = sum of item prices
* Validate success message

---

## 🏗️ Project Structure

```
src
 ├── main
 │    └── java
 │         ├── factory        # WebDriverFactory
 │         ├── pages          # Page Object Model classes
 │         └── utils          # ConfigReader
 │
 ├──  resources
 │     ├── allure.properties
 │     ├── config.properties
 │     └── log4j2.xml
 ├── test
 │    └── java
 │         ├── base           # BaseTest
 │         ├── listeners      # TestListener (screenshots)
 │         └── tests          # Test classes
 │
 └── resources
      └── testng.xml
      
```

---

## ⚙️ Technologies & Patterns

* **Selenium WebDriver** – UI automation
* **TestNG** – test execution & parallel runs
* **Page Object Model (POM)** – design pattern
* **Factory Pattern** – WebDriver creation
* **Allure** – reporting with steps and screenshots
* **Log4j2** – logging

---

## 🌐 Browser Support

* Google Chrome
* Mozilla Firefox

✔ Tests run in **parallel** using TestNG

---

## 🚀 How to Run Tests

### 1. Run tests via Maven

```bash
mvn clean test
```

---

### 2. Run tests via TestNG (IntelliJ)

* Open `testng.xml`
* Right-click → Run

---

## 📊 Allure Report

### 1. Generate report

```bash
allure serve target/allure-results
```

---

### 2. What is included

* Test steps (@Step)
* Logs
* Screenshots on failure
* Test statuses

---

## 📸 Screenshot on Failure

Framework includes a custom TestNG listener:

* Automatically captures screenshots when test fails
* Attaches them to Allure report

---
🧪 How to Simulate Failure (for testing screenshots)

To verify screenshot capturing:

Open CheckoutTest
Uncomment the method:

@Test

public void screenTest() {

    loginPage.login("standard_user1", "secret_sauce1"); // invalid credentials
}
```bash
mvn clean test
```
Open Allure report and check screenshot attachment



## ⚙️ Configuration

Located in:

```
src/main/resources/config.properties
```

Example:

```
base.url=https://www.saucedemo.com/
```

---

## 🧠 Key Design Decisions

* Used **parallel execution on test level** for browser coverage
* Implemented **explicit waits** for stability
* Avoided overengineering (e.g., ThreadLocal) for simplicity and clarity
* Focused on **readability and maintainability**

---

## 🔍 Logging

Logging is implemented using Log4j2:

* Clear execution steps
* Browser-based logging context

---

## 📈 Possible Improvements

* Add Retry mechanism for flaky tests
* Introduce ThreadLocal for method-level parallelism
* Add CI/CD integration (GitHub Actions / Jenkins)
* Add API layer (hybrid testing)

---

## 👨‍💻 Author

Roman Stefanyshyn

---

## 📬 Notes

This project was created as part of a technical assessment and demonstrates:

* UI automation skills
* Framework design understanding
* Clean code practices
* Test architecture basics
