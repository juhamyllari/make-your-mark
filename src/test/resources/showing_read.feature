Feature: As a user I want to see only unread bookmarks by default

  Scenario: user cannot browse read bookmarks by default
    Given command samples is given
    Given command mark is given
    Then system will respond with "Showing bookmark 1/1"

  Scenario: user can browse all bookmarks if requested
    Given command samples is given
    Given command mark is given
    Given command show is given
    Then system will respond with "Showing bookmark 1/2"

  Scenario: user can hide read bookmarks if set to show
    Given command samples is given
    Given command mark is given
    Given command show is given
    Given command hide is given
    Then system will respond with "Showing bookmark 1/1"
