Feature: As a user I want to load reading tips from a local file

  # Same as in browsing_bookmarks. Unnecessary copypaste?
  Scenario: User cannot load reading tips if a save file does not exist
    Given command browse is given
    Then system will respond with "Nothing to show"

  Scenario: User can load reading tips from an existing file
    Given command browse is given
    Then system with save file will respond with "Showing bookmark 1/2"
