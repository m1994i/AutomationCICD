
@tag
Feature: Purchase the order from Ecomerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecomerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                    | password          | productName |
      | milanivanovic@gmail.com | Milanivanovic-1994| ZARA COAT 3 |
      
