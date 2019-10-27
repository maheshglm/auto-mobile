@jo_test3
Feature: Joplin Configuration screen tests

  Scenario: Verify Configuration Screen

    Given I have Joplin application running in device

    When I navigate to Configuration screen

    Then I verify Configuration "language" is set to "English (US) (100%)"
    And I verify Configuration "dateFormat" is set to "30/01/2017"
    And I verify Configuration "timeFormat" is set to "20:30"
    And I verify Configuration "maxConcurrentConnections_currentValue" is set to "5"
    And I verify Configuration "saveGeoLocationWithNotes" is set to "ON"
    And I verify Configuration "enableSoftBreaks" is set to "OFF"
