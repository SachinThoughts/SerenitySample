@ProfessionalUDC
Feature: Verify ProfessionalUDC related scenarios in PRCM
  This is to verify ProfessionalUDC related scenarios in PRCM application

  Background: user is able to navigate to universal defect configuration screen
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user login to SQL server and connect to facility database
    And user runs the "PRCM_Fetch_Classified_Invoices" query to fetch invoice number
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button

  @391397 @PRCMUser @Sprint102
  Scenario: Verify the SOPs for Professional Denials
    Given user is on R1 Decision Account page
    When user clicks on next button in defect workflow section until user reaches SOPs
    Then user should be able to view the SOP action
    When user login to SQL server and connect to "Accretive" database
    And user runs the "UDC_391397_SQL5" query to fetch defect subcategory ID
    Then user should be able to view defectsubcategoryid
    When user runs the "UDC_391397_SQL4" query to fetch the list of SOPs
    Then user should be able view SOP list for the passed defectsubcategory
    When user is on R1 Decision Account page
    Then user should be able to view Same SOPs Step in DB and UI
