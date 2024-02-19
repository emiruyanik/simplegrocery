Feature: Simple Grocery Store API Test

  Scenario:
    Given Register new a client with "Inar" and "Inar@gmail.com"
    Then the response status code should be 201
    When Get all products
    Then the response status code should be 200
    And the response should have correct products
    And Create a new cart
    Then the response status code should be 201
    And Add three items 5477, 5478, 4875 to Cart
    Then the response status code should be 201
    Then Check if the products you added to the cart are correct
    Then the response status code should be 200
    And Let's cancel purchasing a product and remove it from the cart
    Then the response status code should be 204
    Then Check if the product you removed from the cart are correct
    And Create an new order with name "Erkam"
    Then the response status code should be 201
    Then Check the order "Erkam" have created
    Then the response status code should be 200
    And Delete an order
    Then the response status code should be 204