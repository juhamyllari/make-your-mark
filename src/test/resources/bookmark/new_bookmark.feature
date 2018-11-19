Feature: As a user I want to add a new reading tip

    Scenario: Creating a new reading tip
        Given Kirja is initialized with title "Lumi" and writer "Orhan Pamuk" and isbn "951-31-2898-9"
        When a comment "Mahtavaa" is set
        Then the value of comment should be "Mahtavaa"