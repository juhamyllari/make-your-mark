Feature: As a user I want to edit reading tips

    Scenario: User cannot set an empty title
        Given command edit is given
        When a valid field "title" is entered
        And an invalid title "\n" is entered
        Then system will respond with "No change made."