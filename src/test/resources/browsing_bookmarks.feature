Feature: As a user I want to browse reading tips
    
    Scenario: user cannot browse bookmarks if there are none
        Given command browse is given
        Then system will respond with "No bookmarks"

    Scenario: user can browse bookmarks after creating one
        Given command samples is given
        Given command browse is given
        Then system will respond with "1/2"
    
    Scenario: user can scroll through bookmarks
        Given command samples is given
        Given command browse is given
        Given command next is given
        Then system will respond with "2/2"
        