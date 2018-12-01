#Feature: As a user I want to search for reading tips
#
#  Scenario: user can find reading tips by tag
#    Given command samples is given
#    Given command browse is given
#    Given command search is given
#    And a valid tag "hobbies" is entered
#    And one empty answer is entered
#    Then system will respond with "Kalaopas 1/1"
#
#  Scenario: user may not find reading tips by searching
#    Given command samples is given
#    Given command browse is given
#    Given command search is given
#    And an invalid tag "none" is entered
#    And one empty answer is entered
#    Then system will respond with "No bookmarks matching the search criteria."
