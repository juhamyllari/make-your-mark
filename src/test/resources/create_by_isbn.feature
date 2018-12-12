Feature: As a user I can create a bookmark by entering the book's ISBN

  Scenario: user cannot add a bookmark with an invalid ISBN
    Given command isbn is given
    When an invalid ISBN "123" is entered
    Then system will respond with "A valid ISBN consists of 10 or 13 digits."

  Scenario: user can add Murakami Haruki's new novel by its ISBN
    Given command isbn is given
    When a valid ISBN "9784103534327" is entered
    And one empty answer is entered
    Then system will respond with "騎士団長殺し"

  Scenario: user is asked for tags when creating a bookmark by ISBN
    Given command isbn is given
    When a valid ISBN "9784103534327" is entered
    And a valid tag "metaphors" is entered
    And one empty answer is entered
    Then system will respond with "metaphors"
