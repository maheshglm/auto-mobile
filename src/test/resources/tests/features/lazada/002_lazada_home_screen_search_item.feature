@test2
Feature: Lazada app Serach for an item

  Scenario: Searching Lazada for an item should return relevant results

    Given Lazada app is running
    When I search for "Apple laptops"
    Then I should see search results related to "Apple"