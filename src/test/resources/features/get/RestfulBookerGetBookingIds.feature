Feature: Retrieve all booking IDs by first name and check-in date
  As a client
  I want to retrieve all booking IDs by first name and check-in date
  So that I can get all booking IDs for a specific first name and check-in date


  Scenario: Retrieve all booking IDs by first name and check-in date
    When the client retrieves booking IDs for firstname "John" and check-in date "2020-05-09"
    Then the response status code should be 200
    And the response contains a list of booking IDs
