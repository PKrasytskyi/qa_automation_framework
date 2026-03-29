# QA Automation Framework

Java-based UI automation framework for web testing with Selenium WebDriver, TestNG, Allure reporting, and GitHub Actions CI. Includes configurable execution, failure screenshots, and experimental AI-assisted failure triage for debugging failed tests.

## Why This Project

This project demonstrates how to build a maintainable UI automation framework rather than a collection of isolated tests. It focuses on reusable page objects, centralized configuration, suite-based execution, reporting, and CI readiness. As an extra exploration, it also includes experimental AI-assisted failure triage for failed UI runs.

## What is included

- Page Object Model for UI flows
- TestNG-based suite execution
- Allure reporting with screenshots on failure
- Separate failure listeners for screenshots and AI triage attachments
- Configurable browser, base URL, and timeouts
- Optional OpenAI-powered QA agent and triage flow via API key
- GitHub Actions workflow for CI execution

## Project structure

```text
src
|-- main/java
|   |-- api
|   |   `-- triage
|   |-- config
|   |-- core
|   |-- data
|   |-- driver
|   |-- listeners
|   |-- pages
|   `-- utils
`-- test
    |-- java/tests/ui
    `-- resources
```

## Covered Flows

- Login with valid credentials
- Validation for invalid username and invalid password
- Logout flow
- Direct navigation protection for secured pages
- Session persistence after browser refresh
- Inputs field behavior
- Checkboxes interactions
- Dropdown selection
- Add/Remove elements interactions

## Architecture

- `core` - driver lifecycle and base test setup
- `pages` - Page Object Model classes
- `data` - test data providers
- `listeners` - screenshots, failure context, and AI triage hooks
- `utils` - waits, screenshots, and helper utilities
- `.github/workflows` - CI execution


## Configuration

Default configuration lives in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties).

Supported properties:

```properties
baseUrl=https://the-internet.herokuapp.com/
browser=chrome
headless=false
explicit.wait=10
page.load.timeout=30
screenshotPath=reports/screenshots/
openai.model=gpt-4.1
agent.enabled=false
agent.mode=off
agent.timeout.seconds=30
```

Environment variables override file values when present:

```text
BASE_URL
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

## Running tests

Prerequisites:

- Java 17+
- Maven 3.9+
- Chrome installed

Run the default UI suite:

```bash
mvn clean test
```

Run a specific TestNG suite file:

```powershell
mvn test "-Dsurefire.suiteXmlFiles=testng-ui.xml"
```

Run the smoke suite:

```powershell
mvn test "-Dsurefire.suiteXmlFiles=testng-smoke.xml"
```

Override settings from the command line:

```powershell
$env:BASE_URL="https://the-internet.herokuapp.com/"
$env:HEADLESS="true"
mvn clean test
```

Generate and open Allure report:

```bash
mvn allure:serve
```

Run the OpenAI QA agent demo:

```powershell
$env:OPENAI_API_KEY="your_api_key"
mvn -q "-Dexec.mainClass=api.OpenAiAgentDemo" exec:java
```

Pass your own task:

```powershell
mvn -q "-Dexec.mainClass=api.OpenAiAgentDemo" "-Dexec.args=Create regression ideas for dropdown coverage" exec:java
```

## Experimental AI-Assisted Failure Triage

The project now includes [OpenAiAgentService.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\OpenAiAgentService.java), a small wrapper around the OpenAI Responses API for QA-oriented prompts.

For failure triage, the project also includes:

- [AgentTriageListener.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\listeners\AgentTriageListener.java)
- [AllureListener.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\listeners\AllureListener.java)
- [FailureContextBuilder.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\api\triage\FailureContextBuilder.java)

Setup:

- create an API key in the OpenAI Platform dashboard
- set `OPENAI_API_KEY` in your environment
- optionally override `openai.model` in [config.properties](C:\Users\demra\IdeaProjects\UI_API\src\test\resources\config.properties)
- enable triage with `agent.enabled=true`
- set `agent.mode=triage` to attach failure context and AI triage notes to Allure on failed tests

The service is intentionally small so you can extend it with your own prompts, tool-calling loop, bug triage flow, or test-case generation logic.

## CI

GitHub Actions workflow is stored in [ci.yml](C:\Users\demra\IdeaProjects\UI_API\.github\workflows\ci.yml).

The workflow:

- checks out the repository
- installs Java 17
- caches Maven dependencies
- runs `mvn clean test`
- uploads Surefire and Allure artifacts

## Notes

- TestNG suite files live in the project root: [testng-ui.xml](C:\Users\demra\IdeaProjects\UI_API\testng-ui.xml) and [testng-smoke.xml](C:\Users\demra\IdeaProjects\UI_API\testng-smoke.xml).
- Allure result files are written to `target/allure-results`.
- Local screenshots are written to `reports/screenshots/`.
- Allure screenshot and AI triage attachments are handled by separate listeners and share a small helper: [AllureAttachmentSupport.java](C:\Users\demra\IdeaProjects\UI_API\src\main\java\listeners\AllureAttachmentSupport.java).
