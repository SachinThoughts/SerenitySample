@AccountDocument
Feature: Verify AccountDocument related scenarios in PRCM
  To check AccountDocument related scenarios in PRCM application

  Background: user is able to navigate to R1 Decision Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391087 @Sprint8 @PRCMUser
  Scenario Outline: Verify that if account uploaded in one of the linked invoices then it should be available in all other invoices as well as in Account
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision - Search" page
    When user login to SQL server and connect to database
    And user runs the "PRCM_Account_Document_391087_SQL2" query to fetch invoice number and ChargeTransactionID
    Then user should be able to fetch InvoiceNumber and ChargeTransactionID
    When user selects "Invoice Number" from search by dropdown
    And user enters the query result in Invoice Number search textbox fetched above
    And user clicks on submit button
    And user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    And user selects file <doctype> using ChooseFile Option
    Then user should be able to view the selected file path/name under File Name text box
    When user clicks on Upload Document button
    Then user should get the information message "Document Uploaded Successfully." on screen
    When user runs the query "PRCM_Account_Document_391087_SQL3" query to fetch invoice number based on result of above query
    When user selects "Invoice Number" from search by dropdown
    And user enters the query result in Invoice Number search textbox fetched above
    And user clicks on submit button
    And user scrolls down till Account Documents section
    And user checks the Show All Documents check box
    Then user should be able to view same document in attachment as uploaded in previous Invoice number
    
    Examples: 
      | doctype |
      | pdf     |