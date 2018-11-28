Feature: As a user I want to edit reading tips

  Scenario: User cannot set an empty title
    Given command samples is given
    And command browse is given
    And command edit is given
    When a valid field "title" is entered
    And an invalid title "" is entered
    Then system will respond with "No change made."

  Scenario: User can set a new non-empty title
    Given command samples is given
    And command browse is given
    And command edit is given
    When a valid field "title" is entered
    And a valid title "foo" is entered
    Then system will respond with "New title set."
