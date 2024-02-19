@smoke @regression @user_login
Feature: User login

  Scenario Outline: Successful login with valid credentials
    Given I have login credentials "<username>" and "<password>"
    When I send a POST request to the login endpoint
    Then the response status code should be <status_code>
    And I receive a token in the response
    Examples:
      | username | password     | status_code |
      | admin    | password123  | 200         |


  Scenario Outline: Successful login with invalid credentials
    Given I have login credentials "<username>" and "<password>"
    When I send a POST request to the login endpoint
    Then the response status code should be <status_code>
    Examples:
      | username               | password   | status_code |
      | admin01                | password   | 400         |