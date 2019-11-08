@jo_test2 @jo
Feature: Joplin E2E scenarios - Create New Note and Move

  Scenario: Create a Note and Move to another Notebook

    Given I have Joplin application running in device

    Given I generate value with date format "HmSs" and assign to variable "RANDOM"

    And I create a new notebook with name "Notebook 1${RANDOM}"

    When I assign "Assignment Completion" to a variable "TITLE_VAR"
    And I assign "Assignment should be completed by today COB" to a variable "BODY_VAR"

    When I add a new note with below params under notebook "Notebook 1${RANDOM}"
      | Title | ${TITLE_VAR} |
      | Body  | ${BODY_VAR}  |

    Then I expect a new note available under notebook "Notebook 1${RANDOM}"
      | Title | ${TITLE_VAR} |
      | Body  | ${BODY_VAR}  |

    And I create a new notebook with name "Notebook 2${RANDOM}"

    When I move note with title "${TITLE_VAR}" from "Notebook 1${RANDOM}" to "Notebook 2${RANDOM}"

    Then I expect a new note available under notebook "Notebook 2${RANDOM}"
      | Title | ${TITLE_VAR} |
      | Body  | ${BODY_VAR}  |

    Then I expect note with title "${TITLE_VAR}" should not be available under notebook "Notebook 1${RANDOM}"

    When I delete a note with title "${TITLE_VAR}" under notebook "Notebook 2${RANDOM}"

    Then I expect note with title "${TITLE_VAR}" should not be available under notebook "Notebook 2${RANDOM}"