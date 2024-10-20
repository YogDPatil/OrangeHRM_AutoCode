@LoginPageFeature
Feature: To validate the login page

  @Test1
  Scenario Outline: To validate the user can login from UI
    Given User is on login page
    When User enters cred "<id>" and "<password>" and click on sign in button
    Then Validate user navigate to "Dashboard" page

    Examples: 
      | id    | password |
      | Admin | admin123 |
