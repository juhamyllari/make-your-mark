Feature: As a user I want to save bookmarks to a local file

  Scenario: user can discard changes when there is no preexisting file
    Given command samples is given
    Given command quit is given
    Given command no is given
    Then system will respond with "Quitting without saving."

  Scenario: user can save bookmarks when there is no preexisting file
    Given command samples is given
    Given command quit is given
    Given command yes is given
    Then system will respond with "Saved."

  Scenario: user can save bookmarks to an existing file
    Given command samples is given
    Given command quit is given
    Given command yes is given
    Then system will respond with "Saved."

  Scenario: user can discard changes to an existing file
    Given command new is given
    And a valid title "Lumi" is entered
    And a valid author "Orhan Pamuk" is entered
    And two empty answers are entered
    And a valid ISBN "328932-32232" is entered
    And four empty answers are entered
    Given command quit is given
    Given command no is given
    Then system with save file will respond with "Quitting without saving."
