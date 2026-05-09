Feature: GreenKart e-commerce functionality

  Background:
    Given I am on the "ecom-page"

  Scenario Outline: Verify user can search for product
    When I search for "<item>"
    Then product "<item>" should be visible in search results

    Examples:
      | item     |
      | Cucumber |
      | Tomato   |

  Scenario Outline: Verify user can add product to cart
    When I search for "<item>"
    And I add "1" quantity of "<item>" to cart
    And I open the cart
    Then product "<item>" should be visible in cart

    Examples:
      | item     |
      | Cucumber |
      | Carrot   |

  Scenario: Verify selected product is visible in cart
    When I search for "Cucumber"
    And I add "1" quantity of "Cucumber" to cart
    And I open the cart
    Then product "Cucumber" should be visible in cart

  Scenario: Verify user can proceed to checkout
    When I search for "Cucumber"
    And I add "1" quantity of "Cucumber" to cart
    And I open the cart
    And I proceed to checkout
    Then product "Cucumber" should be visible in cart

  Scenario: Verify user can add multiple products
    When I add below products to cart
      | Cucumber |
      | Tomato   |
      | Carrot   |
    And I open the cart
    And I proceed to checkout
    Then product "Cucumber" should be visible in cart