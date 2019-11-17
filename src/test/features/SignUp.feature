Feature: Signup

  @TC-01 @smoke
  Scenario Outline: Verify SignUp functionality
    Given User opens automation practice home page
    When user clicks on sign button
    And enters email id as "<Email_ID>" and clicks create an account
    And fills all the personal information as below
      | Title | FirstName | LastName | Password | Day | Month | Year |
      | Mrs   | FirstName | LastName | Qwerty   |   1 |     1 | 2000 |
    And fills all address details as below
      | Company | Address1    | Address2 | City   | State    | ZipCode | Addn Info | HomePhone   | Mobile      | Alias Addr |
      | Company | Qwerty, 123 | zxcvb    | Qwerty | Colorado |   12345 | Qwerty    | 12345123123 | 12345123123 | hf         |
    And clicks on register button
    Then "MY ACCOUNT" page should be displayed with message as "Welcome to your account"
    And the url of the page should be redirected to "?controller=my-account"
    And a logout button should be visible on page
    And the user name should be displayed as "FirstName LastName"

    Examples: 
      | Email_ID         |
      | abcdef@gmail.com |
