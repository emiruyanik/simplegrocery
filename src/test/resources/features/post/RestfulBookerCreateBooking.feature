Feature: Create a new booking
  As a user
  I want to be able to create a new booking
  So that I can book a room

  Scenario: Create a new booking
    When I create a new booking
    Then the response status code should be 200
    And the response confirms the booking creation

