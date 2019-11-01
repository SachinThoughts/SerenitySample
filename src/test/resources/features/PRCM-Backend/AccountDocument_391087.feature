@AccountDocument
Feature: Verify AccountDocument related scenarios in PRCM
  To check AccountDocument related scenarios in PRCM application

  Background: user is able to navigate to R1 Decision Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391087
  Scenario: Verify that if account uploaded in one of the linked invoices then it should be available in all other invoices as well as in Account
    Given user is on R1 Decision Search page
    When user login to SQL server and connect to database
    And user runs the "PRCM_Account Document_391087_SQL2" query
    Then user should be able to fetch InvoiceNumber and ChargeTransactioID
    When user selects "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button
    And user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    And user selects file <doctype> using ChooseFile Option
    And user clicks on Upload Document button
    Then user should get the information message "Document Uploaded Successfully." on screen
    
    When user runs the query "PRCM_Account Document_391087_SQL3"
    And user selects "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button
    And user scrolls down till Account Documents section
    And user checks the Show All Documents check box
    Then user should be able to view same document in attachment as uploaded in previous Invoice number
