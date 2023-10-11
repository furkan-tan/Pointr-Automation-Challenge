@ui @login
Feature: Login
  As a user, I want to be able to login with username and password


  Background: open home page
    Given user is on N11 home page


  @successful_login @order2
  Scenario: Login and logout successfully
    When user navigates to login page
    Then user enters valid username and password
    Then log if it is unsuccessful login attempt
    Then user should should verify that account avatar has initials
    When user clicks logout button
    Then user should be logged out successfully


  @unsuccessful_login @order1
  Scenario Outline: Attempting an unsuccessful login - <test_type>
    When user navigates to login page
    Then user enters "<username>" username and "<password>" password
    Then an error message is displayed
    And the error message is logged in "loginerror.txt" as "<test_type>" "<username>" "<password>"

    Examples: credentials
      | username                      | password | test_type                  |
      |                               |          | Empty email and password   |
      | abc                           | User123  | Invalid email              |
      | n11testaccount@mailinator.com | 123      | Password less than 6 chars |
#      | n11testaccount@mailinator.com | User123  | Invalid password           |
