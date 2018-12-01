Feature: As a user I want to browse reading tips
    
    Scenario: user cannot browse bookmarks if there are none
        Given command browse is given
        Then system will respond with "Unknown command. Please try again."

    Scenario: user can browse bookmarks after creating one
        Given command samples is given
        Then system will respond with "You have 1 unread bookmark."
    
    Scenario: user can scroll through bookmarks
        Given command samples is given
        And command next is given
        Then system will respond with "You have 2 unread bookmarks."
        