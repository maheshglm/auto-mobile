Feature: This feature is to test moving Note to different notebook functionality

  Background: Ensure app is running
    Given I have Joplin application running in device

  Scenario: New notebook and note are created

    Given I delete "Test Notebook 1" if exists
    And I create a new notebook with name "Test Notebook 1"

    And I add a new note with below params
      | Title | Note1: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

    And I delete "Test Notebook 2" if exists
    And I create a new notebook with name "Test Notebook 2"

    When I move below note from "Test Notebook 1" to "Test Notebook 2"
      | Title                        |
      | Note1: Assignment Completion |

    Then I expect below note is moved to "Test Notebook 2"
      | Title | Note1: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

    And I expect below note does not exist under "Test Notebook 1"
      | Title | Note1: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |









