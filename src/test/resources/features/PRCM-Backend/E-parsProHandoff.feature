Feature: Verify E-pars pro handoff related testcases in PRCM-BE

  Background: 
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And User clicks on ePARS-Pro link

  @438746
  Scenario: Verify that ePARS Pro page for PRCM site Invoice number should be default criteria
    Given user is on ePARS Pro screen
    Then user should be able to view Invoice Number selected by default in Search By dropdown on ePARS Pro Page
