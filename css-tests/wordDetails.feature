Feature: word details page
  details page page for word intro

  Scenario: words golu
    Given I visit "http://192.168.33.10/words/#/details/2"
    Then "words golu size" should have "height" of "85px"
    And "words golu font" should have "font-size" of "18px"

  Scenario: word details header translit
    Given I visit "http://192.168.33.10/words/#/details/2"
    Then "main header translit font" should have "font-size" of "21px"
    And "main header translation" should have "font-size" of "18px"
    And "main header translit font" should have "font-family" of "Merriweather"
    And "main header translit font" should have "font-weight" of "300"
    And "main header translit font" should have "font-style" of "normal"
    And "main header translation" should have "font-weight" of "300"
    And "main header translation" should have "font-family" of "Merriweather"
    And "main header translation" should have "font-style" of "italic"
    And "main header translation" should have "text-decoration" of "none"
    And "main header translation" should have "color" of "rgb(153,153,153)"