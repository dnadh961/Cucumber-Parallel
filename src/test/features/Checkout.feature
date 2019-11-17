Feature: checkout

  @TC-03 @smoke
  Scenario Outline: Verify Checkout Functionality
    Given User opens automation practice home page
    When user clicks on sign button
    And logs in using already registered section with user id as "<Email_ID>" and password as "<Password>"
    And Navigates to women store
    And adds "<Product>" to cart
    And proceeds to Checkout from product page
    And proceeds to Checkout from cart page
    And proceeds to Checkout from address page
    And agrees to terms of service and proceeds to Checkout from carrier page
    And pay by bank wire
    And confirms the order
    Then "order confirmation" page should be shown with url "controller=order-confirmation"
    And the text "Your order on My Store is complete." should be showcased along with other order confirmation details

    Examples: 
      | Email_ID                         | Password | Product                     |
      | hf_challenge_123456@hf123456.com | 12345678 | Faded Short Sleeve T-shirts |
