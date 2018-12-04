Feature: As a user I want to browse reading tips

  Scenario: user cannot browse bookmarks if there are none
    Given application is started
    Then system will respond with "Nothing to show"

  Scenario: user can browse bookmarks after creating some
    Given command samples is given
    Then system will respond with "You have 2 unread bookmarks."

  Scenario: bookmarks are displayed newest first
    Given command samples is given
    Then system will respond with "Suomalainen kalaopas"

  Scenario: user can scroll through bookmarks
    Given command samples is given
    And command next is given
    Then system will respond with "Showing bookmark 2/2"
