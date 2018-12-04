Feature: As a user I want to add a new bookmark

    Scenario: user can create a new bookmark with proper title, type, author and ISBN
        Given command new is given
        And a valid title "Lumi" is entered
        And a valid author "Orhan Pamuk" is entered
        And two empty answers are entered
        And a valid ISBN "328932-32232" is entered
        And four empty answers are entered
        Then system will respond with "Bookmark created."

    Scenario: bookmarks have an "added on" timestamp
        Given command samples is given
        Then system will respond with "Added on: "