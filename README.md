# QA Automation Framework

Java-based automation framework that combines UI testing with Selenium WebDriver and API testing with RestAssured. The project uses TestNG for execution, Allure for reporting, and GitHub Actions for CI. It also includes optional OpenAI-powered failure triage for UI failures.

## Why This Project

This repository is built as a learning-oriented automation framework rather than a collection of isolated tests. The main goal is to keep the structure readable, layered, and easy to extend while still following good engineering practices:

- separate UI and API layers
- centralized configuration
- reusable base classes and managers
- DTO-based API requests and responses
- builder and DataProvider examples for test data
- suite-based execution with TestNG
- Allure reporting and CI support

## What Is Included

- UI automation with Selenium WebDriver and Page Object Model
- API automation with RestAssured
- public API examples on JSONPlaceholder
- authorized API examples on GoRest
- DTO-based request/response handling for API tests
- reusable API assertions and response specifications
- builder- and DataProvider-based API test data setup
- failure screenshots for UI tests
- failure-only API request/response attachments for Allure
- experimental AI-assisted failure triage for UI failures
- GitHub Actions workflow with separate UI and stable API jobs

## Current Project Structure

```text
src
|-- main/java
|   |-- api
|   |   |-- clients
|   |   |-- filters
|   |   |-- logging
|   |   |-- models
|   |   |   |-- request
|   |   |   `-- response
|   |   |-- specs
|   |   `-- triage
|   |-- config
|   |-- core
|   |-- driver
|   |-- listeners
|   |-- pages
|   `-- utils
`-- test/java
    |-- assertions
    `-- tests
        |-- api
        |   `-- goRestApiTests
        |-- apiData
        |-- builder
        |-- data
        |-- Helpers
        `-- ui
```

## Architecture Overview

- `core`
  Shared framework setup such as `BaseTest`, `ApiBaseTest`, `DriverFactory`, `PageManager`, and `ApiManager`.
- `pages`
  UI Page Object classes and page-specific actions.
- `api.clients`
  Endpoint-level API operations such as `PostClient` and `GoRestUserClient`.
- `api.models.request`
  DTO classes for request payloads such as `CreatePostRequest` and `CreateGoRestUserRequest`.
- `api.models.response`
  DTO classes for response payloads such as `PostResponse` and `GoRestUserResponse`.
- `api.specs`
  Reusable RestAssured response specifications for common HTTP expectations.
- `api.filters` and `api.logging`
  API traffic capture and storage used for failure-only Allure attachments.
- `assertions`
  Reusable assertion helpers for API domain checks.
- `tests.api`
  Public and stable API scenarios such as JSONPlaceholder.
- `tests.api.goRestApiTests`
  Token-based GoRest scenarios isolated in a separate auth-focused test group.
- `tests.apiData` and `tests.builder`
  DataProviders and payload builders for API tests.
- `listeners`
  UI screenshots, Allure hooks, API failure attachments, and AI triage hooks.

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

### JSONPlaceholder

- `GET /posts/{id}` happy-path validation
- `GET /posts` collection validation
- `GET /posts/{id}` negative `404` scenario
- `POST /posts` happy-path creation
- DTO-based request/response validation
- DataProvider-based examples for GET and POST

### GoRest

- `GET /public/v2/users` users collection validation
- `POST /public/v2/users` authorized happy-path creation
- builder- and DataProvider-based user creation examples
- negative auth scenario without token
- duplicate email validation scenario
- cleanup via authorized delete call after selected create tests

GoRest scenarios are isolated in the `api-auth` group so the main API regression flow stays stable even when the external token-based service is unavailable or restricted.

## API Design Notes

The API layer is intentionally built in steps:

1. `ApiBaseTest` for API lifecycle
2. `ApiManager` for centralized RestAssured configuration
3. client classes for endpoint actions
4. request/response DTO models
5. reusable assertions
6. response specifications
7. builders and DataProviders for test data
8. failure-only Allure attachments for API traffic
9. authorized request flow for token-based APIs

This keeps the code educational and easy to explain while still following good framework practices.

## Configuration

Default configuration lives in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties).

Current properties:

```properties
baseUrl=https://the-internet.herokuapp.com/
api.baseUrl=https://jsonplaceholder.typicode.com
api.tokenBaseUrl=https://gorest.co.in/
browser=chrome
headless=true
explicit.wait=10
page.load.timeout=30
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
API_TOKEN_BASE_URL
API_TOKEN
API_KEY
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
- GoRest personal access token for authorized GoRest tests only

Run the default suite:

```powershell
mvn clean test
```

Run the full UI suite:

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

Run the authorized API suite:

```powershell
$env:API_TOKEN="your_gorest_token"
mvn clean test -Papi-auth
```

Run one authorized API test class:

```powershell
$env:API_TOKEN="your_gorest_token"
mvn test -Papi-auth "-Dtest=tests.api.goRestApiTests.UserCreateTests"
```

Run one authorized API test method:

```powershell
$env:API_TOKEN="your_gorest_token"
mvn test -Papi-auth "-Dtest=tests.api.goRestApiTests.UserCreateTests#shouldCreateUserWithValidData"
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
- [testng-api-auth.xml](C:\Users\demra\IdeaProjects\UI_API\testng-api-auth.xml)

Current intent:

- `testng-ui.xml` runs the UI regression-style set
- `testng-smoke.xml` runs the smaller smoke subset
- `testng-api.xml` runs the stable `api` group
- `testng-api-auth.xml` runs the token-based `api-auth` group for GoRest

## Allure Reporting

- UI failures attach screenshots and page context
- API failures attach captured request/response traffic only on failed tests
- API auth headers are masked before traffic is stored for reporting

Allure result files are written to `target/allure-results`.

## CI

GitHub Actions workflow is stored in [ci.yml](C:\Users\demra\IdeaProjects\UI_API\.github\workflows\ci.yml).

The workflow currently runs separate jobs for UI and stable public API execution.

Authorized GoRest tests are kept in the project as a separate `api-auth` suite, but are not part of the current CI pipeline because the external token-based service can make the pipeline unstable.

If you later decide to run GoRest auth tests in CI, add the repository secret:

- `API_TOKEN`

Then expose it in a dedicated API auth job environment:

```yaml
env:
  API_TOKEN: ${{ secrets.API_TOKEN }}
```

The pipeline uploads separate artifacts for:

- UI Surefire reports
- UI Allure results
- API Surefire reports
- API Allure results

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

## Notes

- UI listeners are tied to Selenium and should not be reused for API test classes.
- API tests should inherit from [ApiBaseTest](C:\Users\demra\IdeaProjects\UI_API\src\main\java\core\ApiBaseTest.java), not from UI [BaseTest](C:\Users\demra\IdeaProjects\UI_API\src\main\java\core\BaseTest.java).
- GoRest positive scenarios require `API_TOKEN` and run under the separate `api-auth` group.
- `api-auth` is currently intended for local or manual execution, not for the default CI pipeline.
- JSONPlaceholder examples remain useful as public, no-auth training scenarios and stay in the main `api` suite.
