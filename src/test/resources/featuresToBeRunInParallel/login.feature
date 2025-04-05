Feature: Login Functionality

  @TestNG
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "visual_user" and password "secret_sauce"
    Then I click the login button
    
  @TestNG 
  Scenario: UnSuccessful login with valid credentials
  Given I am on the login page
  When I enter username "visual_user" and password "secret_saucex"
  Then I click the login button
  
