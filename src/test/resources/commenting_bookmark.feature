Feature: As a user I want to comment reading tips

    Scenario: user can comment on bookmarks
        Given command samples is given
        And command comment is given
        When a valid comment "Lue luvut 4-10" is entered
        And one empty answer is entered
        Then system will respond with "Lue luvut 4-10"

    Scenario: all comments are shown
        Given command samples is given
        And command comment is given
        When a valid comment "Lue luvut 4-10" is entered
        And one empty answer is entered
        And command comment is given
        And a valid comment "Lue luvut 12-13" is entered
        And one empty answer is entered
        Then system will respond with "Lue luvut 4-10" and "Lue luvut 12-13"
