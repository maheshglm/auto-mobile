@jo_test1
Feature: This feature is to test Joplin Create New Notebook functionality

  Background: Ensure app is running
    Given I have Joplin application running in device

    @test
  Scenario: User should be able to create a new notebook

    When I create a new notebook with name "Test Notebook 1"

    Then I expect "Test Notebook 1" notebook created
    Then element "NoteBookLocators.add" should be present
    Then element "NoteBookLocators.newNoteBookDefaultMessage" should be present
    Then element "NoteBookLocators.search" should be present
    Then element "NoteBookLocators.sortNotesIcon" should be present

  Scenario: Rename Notebook name

    Given I create a new notebook with name "Test Notebook 2"
    Then I expect "Test Notebook 2" notebook created

    When I rename the notebook "Test Notebook 2" to "Test Notebook 2 Mod"
    Then I expect "Test Notebook 2 Mod" notebook renamed

  Scenario: Delete Notebook

    Given I create a new notebook with name "Test Notebook 3"
    When I delete notebook "Test Notebook 3"
    Then I expect notebook "Test Notebook 3" is deleted
