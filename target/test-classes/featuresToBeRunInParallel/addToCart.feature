Feature: verify if user is able to add products to the cart

  Scenario: add products to the cart
    Given I am on the login page
    And I enter username "visual_user" and password "secret_sauce"
    And I click the login button
    And I add few products in cart
    When I click on cart icon
    Then I should land to cart details page

