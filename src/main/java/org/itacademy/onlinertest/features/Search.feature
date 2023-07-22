Feature: Search feature

  Scenario Outline: Search for product
    Given I am on the main page
    When I enter the product name "<product>" in the search field
    Then I check that a frame with a list of found products has appeared
    Then I check that the product name "<product>" is in the results list

    Examples:
      | product       |
      | iphone 11     |
#      | playstation     |