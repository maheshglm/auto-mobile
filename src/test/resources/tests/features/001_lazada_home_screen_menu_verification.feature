@test1
Feature: Lazada App Testing for Home screen objects

  Scenario: Lazada Home screen Objects Verification

    Given Lazada app is running
    Then I expect Scan element is visible
    And I expect Wallet element is visible
    And I expect Search view element is visible
    And I expect Login Now link is visible
    And I expect Easy login with SMS/Facebook/Google! is available
    And I expect Feed element is visible
    And I expect Messages element is visible
    And I expect Cart element is visible
    And I expect Account element is visible

