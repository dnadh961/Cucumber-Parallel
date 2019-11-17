Feature: Login

  @TC-02 @smoke
  Scenario: Verify login for valid credentials
  	Given User opens automation practice home page
    When user clicks on sign button
    And logs in using already registered section with user id as "hf_challenge_123456@hf123456.com" and password as "12345678"
    Then "MY ACCOUNT" page should be displayed with message as "Welcome to your account"
    And the url of the page should be redirected to "?controller=my-account"
    And a logout button should be visible on page
    And the user name should be displayed as "Joe Black"
