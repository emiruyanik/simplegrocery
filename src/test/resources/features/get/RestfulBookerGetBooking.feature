Feature: Retrieve booking details

  Scenario: Client retrieves booking by ID
    When the client retrieves the booking with ID 10
    Then the response status code should be 200
    And the response should contain the booking details



