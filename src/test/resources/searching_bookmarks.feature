Feature: As a user I want to search for reading tips

  Scenario: user can find reading tips by tag
    Given command samples is given
    Given command tagsearch is given
    And a valid tag "hobbies" is entered
    And one empty answer is entered
    Then system will respond with "Showing bookmark 1/1"

  Scenario: user may not find reading tips by searching
    Given command samples is given
    Given command tagsearch is given
    And an invalid tag "none" is entered
    And one empty answer is entered
    Then system will respond with "No bookmarks matching the search criteria."

  Scenario: search filter can be dropped
    Given command samples is given
    And command tagsearch is given
    And a valid tag "hobbies" is entered
    And one empty answer is entered
    And command drop is given
    Then system will respond with "You have 2 unread bookmarks"

  Scenario: user can find reading tips by title
    Given command samples is given
    Given command search is given
    And a valid title "kalaopas" is entered
    Then system will respond with "Title: Suomalainen kalaopas"

  Scenario: user can find reading tips by author
    Given command samples is given
    Given command search is given
    And a valid author "gough" is entered
    Then system will respond with "Title: An Introduction to GCC"

  Scenario: user can find reading tips by description
    Given command samples is given
    Given command search is given
    And a valid description "koville jätkille" is entered
    Then system will respond with "Title: An Introduction to GCC"