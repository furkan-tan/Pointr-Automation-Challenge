@ui @search
Feature: Search
  As a user, I want to be able to search for items


  Background: open home page
    Given user is on N11 home page


  @search_term
  Scenario: Search for a term to get results
    When user searches for a valid term
    Then user should see search results
    Then search term, results and screenshots are logged in "results.txt"

  @unsuccessful_search
  Scenario: Search for no results
    When user searches for an invalid term
    Then user should see warning message on search results
    Then search term, results and screenshots are logged in "results.txt"
