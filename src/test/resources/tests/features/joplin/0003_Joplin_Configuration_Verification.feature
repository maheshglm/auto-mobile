@jo_test3
Feature: Joplin Configuration screen tests

  Scenario: Verify Configuration Screen

    Given I have Joplin application running in device

    When I navigate to Configuration screen

    When I set Configuration language to "Svenska (80%)"
    Then I verify Configuration "language" is set to "Svenska (80%)"

    When I set Configuration dateFormat to "01/30/17"
    Then I verify Configuration "dateFormat" is set to "01/30/17"

    And I verify Configuration "timeFormat" is set to "20:30"

    When I set Configuration maxConcurrentConnections_setValue to "10"
    Then I verify Configuration "maxConcurrentConnections_currentValue" is set to "10"

    When I set Configuration enableSoftBreaks to "ON"
    Then I verify Configuration "enableSoftBreaks" is set to "ON"

    When I set Configuration saveGeoLocationWithNotes to "OFF"
    Then I verify Configuration "saveGeoLocationWithNotes" is set to "OFF"

