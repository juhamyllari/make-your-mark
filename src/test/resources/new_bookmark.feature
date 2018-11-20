Feature: As a user I want to add a new reading tip

    Scenario: user can add a new reading tip with proper title, author and ISBN
        Given command new is given
        And command book is given
        When a valid title "Lumi" is entered
        And four empty answers are entered
        And a valid author "Orhan Pamuk" is entered
        And a valid ISBN "328932-32232" is entered
        Then system will respond with "Bookmark created"