Feature: This feature is to test Joplin Create New Note functionality


  Background: Ensure app is running
    Given I have Joplin application running in device

  Scenario: User should be able to add new note under notebook

    Given I delete "Test Notebook 1" if exists
    And I create a new notebook with name "Test Notebook 1"

    When I add a new note with below params
      | Title | Note1: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

    Then I expect a new note with below params is added successfully
      | Title | Note1: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

  Scenario: User should be able to amend a note

    Given I have a notebook "Test Notebook 1"

    When I add a new note with below params
      | Title | Note2: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

    Then I modify a new note with below params
      | Title | Note2: Assignment Completion               |
      | Body  | Assignment should be completed by tomorrow |

    Then I expect a new note with below params is added successfully
      | Title | Note2: Assignment Completion               |
      | Body  | Assignment should be completed by tomorrow |

  Scenario: User should be able to Delete note

    Given I have a notebook "Test Notebook 1"

    And I add a new note with below params
      | Title | Note3: Assignment Completion                |
      | Body  | Assignment should be completed by today COB |

    When I delete a note with title "Note3: Assignment Completion"

    Then I expect note with title "Note3: Assignment Completion" should be deleted