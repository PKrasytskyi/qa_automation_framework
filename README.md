# QA Automation Framework

Java-based automation framework that combines UI testing with Selenium WebDriver and API testing with RestAssured. The project uses TestNG for execution, Allure for reporting, and GitHub Actions for CI. It also includes optional OpenAI-powered failure triage for UI test failures.

## Why This Project

This repository is built as a learning-oriented automation framework rather than a collection of isolated tests. The main goal is to keep the structure readable, layered, and easy to extend while still following good engineering practices:

- separate UI and API test layers
- centralized configuration
- reusable base classes and managers
- DTO-based API requests and responses
- suite-based execution with TestNG
- reporting and CI support

## What Is Included

- UI automation with Selenium WebDriver and Page Object Model
- API automation with RestAssured
- TestNG suites for UI, smoke, and API execution
- DTO-based request/response handling for API tests
- DataProvider examples for API parameterization
- Allure reporting
- failure screenshots for UI tests
- experimental AI-assisted failure triage for UI failures
- GitHub Actions workflow with separate UI and API jobs

## Current Project Structure

```text
src
|-- main/java
|   |-- api
|   |   |-- clients
|   |   |-- models
|   |   |   |-- request
|   |   |   `-- response
|   |   `-- triage
|   |-- config
|   |-- core
|   |-- driver
|   |-- listeners
|   |-- pages
|   |   `-- DynamicLoadingPages
|   `-- utils
`-- test/java/tests
    |-- api
    |-- apiData
    |-- data
    `-- ui
```

## Architecture Overview

- `core`
  Contains shared framework setup such as `BaseTest`, `ApiBaseTest`, `DriverFactory`, `PageManager`, and `ApiManager`.
- `pages`
  UI Page Object classes and page-specific actions.
- `api.clients`
  Endpoint-level API operations such as `PostClient`.
- `api.models.request`
  DTO classes for request payloads such as `CreatePostRequest`.
- `api.models.response`
  DTO classes for response payloads such as `PostResponse`.
- `tests.ui`
  UI scenarios grouped by feature.
- `tests.api`
  API scenarios for GET and POST flows.
- `tests.data` and `tests.apiData`
  TestNG DataProviders for UI and API tests.
- `listeners`
  Allure attachments, screenshot capture, and AI triage hooks.
- `.github/workflows`
  CI pipeline definitions.

## Covered UI Flows

- login with valid credentials
- validation for invalid username and invalid password
- logout flow
- direct navigation protection for secured pages
- session persistence after browser refresh
- inputs field behavior
- checkboxes interactions
- dropdown selection
- add/remove elements interactions
- JavaScript alerts
- frames and nested frames
- multiple windows
- dynamic controls
- dynamic loading

## Covered API Flows

The current API learning block uses `https://jsonplaceholder.typicode.com` as the target API.

- `GET /posts/{id}` happy-path validation
- `GET /posts` collection validation
- `GET /posts/{id}` negative scenario with `404`
- `POST /posts` happy-path creation
- `POST /posts` DTO-based request/response validation
- DataProvider-based API test examples for GET and POST

## Configuration

Default configuration lives in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties).

Current properties:

```properties
baseUrl=https://the-internet.herokuapp.com/
api.baseUrl=https://jsonplaceholder.typicode.com
browser=chrome
headless=true
explicit.wait=10
page.load.timeout=30
reportPath=reports/
screenshotPath=reports/screenshots/
maxStackTraceChars=8000
maxPageSourceChars=12000
environment=qa
openai.model=gpt-4.1
agent.enabled=false
agent.mode=off
agent.timeout.seconds=30
```

Environment variables override file values when present:

```text
BASE_URL
API_BASE_URL
BROWSER
HEADLESS
EXPLICIT_WAIT
PAGE_LOAD_TIMEOUT
SCREENSHOT_PATH
OPENAI_API_KEY
OPENAI_MODEL
AGENT_ENABLED
AGENT_MODE
AGENT_TIMEOUT_SECONDS
```

## Running Tests

Prerequisites:

- Java 17+
- Maven 3.9+
- Chrome installed for UI execution

Run the default suite:

```powershell
mvn clean test
```

Run the full UI suite with Maven profile:

```powershell
mvn clean test -Pui
```

Run the smoke UI suite:

```powershell
mvn clean test -Psmoke
```

Run the API suite:

```powershell
mvn clean test -Papi
```

Run a suite file directly:

```powershell
mvn test "-Dsurefire.suiteXmlFiles=testng-api.xml"
```

Override configuration from the command line:

```powershell
$env:BASE_URL="https://the-internet.herokuapp.com/"
$env:API_BASE_URL="https://jsonplaceholder.typicode.com"
$env:HEADLESS="true"
mvn clean test -Papi
```

Generate and open the Allure report:

```powershell
mvn allure:serve
```

## TestNG Suites

Suite files live in the project root:

- [testng-ui.xml](C:\Users\demra\IdeaProjects\UI_API\testng-ui.xml)
- [testng-smoke.xml](C:\Users\demra\IdeaProjects\UI_API\testng-smoke.xml)
- [testng-api.xml](C:\Users\demra\IdeaProjects\UI_API\testng-api.xml)

Current intent:

- `testng-ui.xml` runs the UI regression-style set
- `testng-smoke.xml` runs the smaller smoke subset
- `testng-api.xml` discovers API tests from the `tests.api` package and runs the `api` group

## API Learning Approach

The API part of the framework is intentionally introduced step by step:

1. `ApiBaseTest` for API lifecycle
2. `ApiManager` for centralized RestAssured configuration
3. `PostClient` for endpoint actions
4. request/response DTO models
5. GET and POST scenarios
6. DataProvider-based scaling examples

This keeps the code educational and easy to explain while still following good framework practices.

## Experimental AI-Assisted Failure Triage

The project includes [OpenAiAgentService.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\OpenAiAgentService.java), a lightweight wrapper around the OpenAI Responses API for QA-oriented prompts.

For failure triage, the project also includes:

- [AgentTriageListener.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\listeners\AgentTriageListener.java)
- [AllureListener.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\listeners\AllureListener.java)
- [FailureContextBuilder.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\triage\FailureContextBuilder.java)

Setup:

- create an API key in the OpenAI Platform dashboard
- set `OPENAI_API_KEY` in your environment
- optionally override `openai.model` in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties)
- enable triage with `agent.enabled=true`
- set `agent.mode=triage` to attach failure context and AI triage notes to Allure on failed UI tests

The service is intentionally small so you can extend it with your own prompts, test generation ideas, or triage workflow.

## CI

GitHub Actions workflow is stored in [ci.yml](C:\Users\demra\IdeaProjects\UI_API\.github\workflows\ci.yml).

The workflow currently runs two separate jobs:

- `ui-tests`
  Runs the UI smoke suite with `mvn --batch-mode clean test -Psmoke`
- `api-tests`
  Runs the API suite with `mvn --batch-mode clean test -Papi`

The pipeline also uploads separate artifacts for:

- UI Surefire reports
- UI Allure results
- API Surefire reports
- API Allure results

## Notes

- Allure result files are written to `target/allure-results`.
- Local screenshots are written to `reports/screenshots/`.
- UI listeners are tied to Selenium and should not be reused for API test classes.
- API tests should inherit from [ApiBaseTest](C:\Users\demra\IdeaProjects\UI_API\src\main\java\core\ApiBaseTest.java), not from UI [BaseTest](C:\Users\demra\IdeaProjects\UI_API\src\main\java\core\BaseTest.java).
- API request/response DTOs are currently demonstrated through [CreatePostRequest.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\models\request\CreatePostRequest.java) and [PostResponse.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\models\response\PostResponse.java).
