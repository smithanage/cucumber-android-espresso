Feature: Create Todo
  Create a Todo Item

  Scenario: Create a Todo Item with title and description only
    Given I am in the listing page
    When I add a Todo
    Then I should see the Todo listed