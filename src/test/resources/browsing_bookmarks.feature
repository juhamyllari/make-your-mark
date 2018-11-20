Feature: As a user I want to browse reading tips
    
    Scenario: user cannot browse bookmarks if there are none
        Given command browse is given
        Then system will respond with "No bookmarks"

    Scenario: user can browse bookmarks after creating one
        Given command new is given
        When a valid type "book" is entered
        And a valid title "Sinuhe Egyptiläinen" is entered
        And four empty answers are entered
        And a valid author "Mik Waltari" is entered
        And a valid ISBN "9384-32849" is entered
        Given command browse is given
        Then system will respond with "Sinuhe Egyptiläinen 1/1"