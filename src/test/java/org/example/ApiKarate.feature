  Feature: Test request
    Scenario: API GET request test
      Given url "https://swapi.dev/api/"
      When method GET
      Then status 200