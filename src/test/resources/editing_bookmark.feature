Feature: As a user I want to edit reading tips
    
    Scenario: User cannot set an empty title
        Given command samples is given
        And command edit is given
        When a valid field "title" is entered
        And an invalid title "" is entered
        Then system will respond with "No change made."

    Scenario: User can set a new non-empty title
        Given command samples is given
        And command edit is given
        When a valid field "title" is entered
        And a valid title "Kalaoppaan päiväkirjat" is entered
        Then system will respond with "New title set."

    Scenario: User can add new tags
        Given command samples is given
        And command edit is given
        When a valid field "tags" is entered
        And command new is given
        When a valid tag "boulderointi" is entered
        Then system will respond with "Value added."

    Scenario: User can edit tags
        Given command samples is given
        And command edit is given
        And a valid field "tags" is entered
        And command change is given
        And a valid tag "hobbies" is entered
        When a valid tag "harrastukset" is entered
        Then system will respond with "Value changed."
    
    Scenario: User can remove tags
        Given command samples is given
        And command edit is given
        And a valid field "tags" is entered
        And command change is given
        And a valid tag "hobbies" is entered
        And command delete is given
        Then system will respond with "Value removed."

    Scenario: User can edit multiple fields
        Given command samples is given
        And command editall is given
        When a valid title "Kalamatin päiväkirja" is entered
        And a valid URL "kalamatti.com" is entered
        And a valid description "Mahtavaa" is entered
        And a valid author "Kala Matti" is entered
        And a valid ISBN "43289-234324" is entered
        When command yes is given
        Then system will respond with "Title: Kalamatin päiväkirja"
        