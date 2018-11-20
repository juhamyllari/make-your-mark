Feature: As a user I want to add a new reading tip

    Scenario: user can create a new reading tip with proper title, type, author and ISBN
        Given command new is given
        When a valid type "book" is entered
        And a valid title "Lumi" is entered
        And four empty answers are entered
        And a valid author "Orhan Pamuk" is entered
        And a valid ISBN "328932-32232" is entered
        Then system will respond with "Bookmark created."

    Scenario: creation fails with an invalid type
        Given command new is given
        When an invalid type "asdklasd" is entered
        Then system will respond with "Invalid type."