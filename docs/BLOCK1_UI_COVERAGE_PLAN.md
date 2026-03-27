# Block 1 UI Coverage Plan

## Purpose

Block 1 defines the first wave of UI coverage for `https://the-internet.herokuapp.com/`.
The goal is to:

- lock the initial page list
- prioritize pages by framework value
- split delivery into waves
- record the current status of this repository against that plan

## Current Project Revision

### Implemented in the repository

- `Form Authentication`
- `Checkboxes`
- `Dropdown`
- `Inputs`
- `Add/Remove Elements`

### Block 1 status

- Current completion estimate: `90-95%`
- Reason:
  - all `Wave 1A` pages now exist in the framework
  - each `Wave 1A` page has page-object coverage
  - each `Wave 1A` page has at least a basic positive scenario
  - `Dropdown` and `Add/Remove Elements` were added after the first Block 1 revision
  - coverage depth is still starter-level, but the first-wave surface is now present

### Scenario depth note

- `Dropdown` currently has one test method with two data-driven runs: `Option 1` and `Option 2`
- `Add/Remove Elements` currently has two separate scenarios:
  - add elements
  - add and remove elements
- `Form Authentication`, `Checkboxes`, and `Inputs` already provide stronger basic depth than the newly added modules

## Prioritization Model

### P1

Pages that give the best return for framework maturity, reusable patterns, and stable regression value.

- `Form Authentication`
- `Checkboxes`
- `Dropdown`
- `Inputs`
- `Add/Remove Elements`
- `Dynamic Controls`
- `Dynamic Loading`
- `JavaScript Alerts`
- `Multiple Windows`
- `Frames`
- `Notification Messages`

### P2

Pages that are useful after the P1 foundation is stable.

- `Context Menu`
- `File Upload`
- `File Download`
- `Horizontal Slider`
- `Hovers`
- `Key Presses`
- `Sortable Data Tables`
- `Broken Images`
- `Disappearing Elements`
- `Shifting Content`
- `Nested Frames`

### P3

Pages that are either lower-value for the first framework iteration or more prone to instability / special handling.

- `A/B Testing`
- `Basic Auth`
- `Challenging DOM`
- `Digest Authentication`
- `Drag and Drop`
- `Dynamic Content`
- `Entry Ad`
- `Exit Intent`
- `Floating Menu`
- `Forgot Password`
- `Geolocation`
- `Infinite Scroll`
- `JQuery UI Menus`
- `Large & Deep DOM`
- `Redirect Link`
- `Secure File Download`
- `Shadow DOM`
- `Slow Resources`
- `Status Codes`
- `WYSIWYG Editor`

## Delivery Waves

### Wave 1A

The minimum useful UI foundation.

- `Form Authentication`
- `Checkboxes`
- `Dropdown`
- `Inputs`
- `Add/Remove Elements`

### Wave 1B

Dynamic behavior and browser context switching.

- `Dynamic Controls`
- `Dynamic Loading`
- `JavaScript Alerts`
- `Multiple Windows`
- `Frames`

### Wave 1C

Useful medium-complexity scenarios once the base architecture is stable.

- `Notification Messages`
- `File Upload`
- `Context Menu`
- `Horizontal Slider`
- `Sortable Data Tables`

## Repository Status By Wave

### Wave 1A

- `Form Authentication` -> implemented
- `Checkboxes` -> implemented
- `Dropdown` -> implemented
- `Inputs` -> implemented
- `Add/Remove Elements` -> implemented

### Wave 1B

- not started

### Wave 1C

- not started

## Exit Criteria For Block 1

Block 1 is considered complete when:

- all `Wave 1A` pages exist in the framework
- each `Wave 1A` page has at least one stable page object
- each `Wave 1A` page has a basic positive test set
- critical negative or state-based checks are defined for each `Wave 1A` page
- this plan remains the source of truth for first-wave coverage

### Current exit criteria review

- `all Wave 1A pages exist in the framework` -> done
- `each Wave 1A page has at least one stable page object` -> done
- `each Wave 1A page has a basic positive test set` -> done
- `critical negative or state-based checks are defined for each Wave 1A page` -> partially done
- `this plan remains the source of truth for first-wave coverage` -> done

## Recommended Next Steps

1. Perform a short final Block 1 polish pass:
   - add one more explicit state/negative scenario for `Dropdown`
   - add one more edge-case scenario for `Add/Remove Elements`
2. If that polish is intentionally deferred, treat Block 1 as functionally closed and move to Block 2.
3. Start Block 2 with architecture rules for reusable page patterns, assertions, and component-level helpers.
