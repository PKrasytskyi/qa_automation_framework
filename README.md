# QA Automation Framework

UI test automation framework built with Java 17, Selenium WebDriver, TestNG, and Allure.

## What is included

- Page Object Model for UI flows
- TestNG-based suite execution
- Allure reporting with screenshots on failure
- Configurable browser, base URL, and timeouts
- GitHub Actions workflow for CI execution

## Project structure

```text
src
|-- main/java
|   |-- config
|   |-- core
|   |-- data
|   |-- driver
|   |-- listeners
|   |-- pages
|   |-- reporting
|   `-- utils
`-- test
    |-- java/tests/ui
    `-- resources
```

## Configuration

Default configuration lives in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties).

Supported properties:

```properties
baseUrl=https://the-internet.herokuapp.com/
browser=chrome
headless=true
explicit.wait=10
page.load.timeout=30
screenshotPath=reports/screenshots/
```

Environment variables override file values when present:

```text
BASE_URL
BROWSER
HEADLESS
EXPLICIT_WAIT
PAGE_LOAD_TIMEOUT
SCREENSHOT_PATH
```

## Running tests

Prerequisites:

- Java 17+
- Maven 3.9+
- Chrome installed

Run the full suite:

```bash
mvn clean test
```

Override settings from the command line:

```bash
BASE_URL=https://the-internet.herokuapp.com/ HEADLESS=true mvn clean test
```

Generate and open Allure report:

```bash
mvn allure:serve
```

## CI

GitHub Actions workflow is stored in [ci.yml](C:\Users\demra\IdeaProjects\UI_API\.github\workflows\ci.yml).

The workflow:

- checks out the repository
- installs Java 17
- caches Maven dependencies
- runs `mvn clean test`
- uploads Surefire and Allure artifacts

## Notes

- The suite is defined in [testng.xml](C:\Users\demra\IdeaProjects\UI_API\testng.xml).
- Allure result files are written to `target/allure-results`.
- Local screenshots are written to `reports/screenshots/`.
