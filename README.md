# 🧪 QA Automation Framework (Java + Selenium + TestNG + Allure)

This project is a **UI Test Automation Framework** built with **Java, Selenium WebDriver, TestNG, and Allure Reporting**.  
It demonstrates best practices in test automation, including **Page Object Model, data-driven testing, and CI/CD integration**.

---

## 🚀 Features

- ✅ **Java + Selenium WebDriver**
- ✅ **TestNG** for test execution and configuration
- ✅ **Page Object Model (POM)** for maintainable UI tests
- ✅ **Data-driven testing** using TestNG DataProviders
- ✅ **Allure Reports** with screenshots on failure
- ✅ **Centralized test setup (BaseTest)**
- ✅ **Environment configuration (config.properties + env variables)**
- ✅ **GitHub Actions CI/CD pipeline**

---

## 🧱 Project Structure

```
src
 └── test
     ├── java
     │   ├── data          # DataProviders and test data
     │   ├── pages         # Page Object classes (MainPage, LoginPage, SecurePage)
     │   ├── setup         # BaseTest setup and driver management
     │   ├── tests         # Test classes (login, logout scenarios)
     │   └── utils         # Helper utilities and listeners
     └── resources
         ├── config.properties
         └── testng.xml
```

---

## ⚙️ Configuration

The framework supports both:
- 📄 `config.properties` (default values)
- 🌍 **Environment Variables** (for CI/CD and secure data)

### Example `config.properties`
```
base.url=https://the-internet.herokuapp.com
browser=chrome
headless=false
explicit.wait=10
```

### Example ENV variables
```
BASE_URL=https://the-internet.herokuapp.com
BROWSER=chrome
HEADLESS=true
USERNAME=tomsmith
PASSWORD=SuperSecretPassword!
```

---

## 🧪 Test Scenarios

Implemented test cases include:

- 🔐 Login with valid credentials
- ❌ Login with invalid credentials
- 🚪 Logout functionality

---

## ▶️ How to Run Tests

### Prerequisites
- Java 17+
- Maven 3.9+
- Chrome browser

### Run tests locally
```
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run in headless mode
```
mvn clean test -Dheadless=true
```

---

## 📊 Allure Report

### Generate report
```
mvn allure:serve
```

---

## 🔄 CI/CD (GitHub Actions)

The project includes a CI pipeline located in:
```
.github/workflows/maven.yml
```

---

## 🧩 Technologies Used

- Java 17  
- Selenium WebDriver  
- TestNG  
- Maven  
- Allure Reports  
- WebDriverManager  
- GitHub Actions  

---

## 👨‍💻 Author

Petro Krasytskyi  
QA Automation Engineer  

GitHub: https://github.com/PKrasytskyi  
LinkedIn: https://linkedin.com/in/petro-krasytskyi-54a7b918b  

---

## 🏁 Future Improvements

- Add API tests (RestAssured)  
- Implement Docker + Selenium Grid  
- Add parallel test execution  
- Improve reporting with Allure history  
