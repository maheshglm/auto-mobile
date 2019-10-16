@jo_test1
Feature: This feature is to test Joplin Create New Notebook functionality


  Scenario: Test function

    Given I have Joplin application running in device
    Then element "NoteBookLocators.menu" should be present
    Then element "NoteBookLocators.newNoteBookDefaultMessage" should not be present



#  Background: Ensure app is running
#    Given I have Joplin application running in device
#
#  Scenario: User should be able to create a new notebook
#
#    Given I delete notebook "Test Notebook 1" if exists
#
#    When I create a new notebook with name "Test Notebook 1"
#
#    Then I expect "Test Notebook 1" notebook created
#
#    Then element "NoteBookLocators.menu" should be present
#
#
#    And I should see "Test Notebook 1" should be listed under "Notebooks" section
#
#  Scenario: Rename Notebook name
#
#    Given I delete "Test Notebook 2" if exists
#    And I delete "Test Notebook 2 Mod" if exists
#
#    When I create a new notebook with name "Test Notebook 2"
#    And I rename the notebook to "Test Notebook 2 Mod"
#    Then I expect Notebook name should be changed to "Test Notebook 2 Mod"
#
#  Scenario: Delete Notebook
#
#    Given I delete "Test Notebook 3" if exists
#    And I create a new notebook with name "Test Notebook 3"
#
#    When I delete notebook "Test Notebook 3"
#    Then I expect notebook "Test Notebook 3" is deleted
