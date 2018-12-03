Feature: As a user I want to delete reading tips

  Scenario: User can remove a bookmark
    Given command samples is given
    And command remove is given
    And command yes is given
    Then system will respond with "You have 1 unread bookmark."

  Scenario: Removing can be halted
    Given command samples is given
    And command remove is given
    And command no is given
    Then system will respond with "You have 2 unread bookmarks."

  Scenario: Can't remove bookmarks when there are none
    Given command remove is given
    Then system will respond with "Unknown command. Please try again."
