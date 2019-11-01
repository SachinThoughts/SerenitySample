@DefectOverride
Feature: Verify Defect over ride

  Background: user is able to connect to SQL server for PRCM facility
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link

  @391266
  Scenario Outline: Verify that Defect Workflow section should be display when searched by Invoice#
    Given user is able to login to sql server and connect to database
    When user runs the to fetch invoicid <query1> query
    Then user should be able to view some invoice id
    When user runs the query <query2>
    And user should be able to view the PRCM Flag "ON"
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "Like" operator
    And user enters invoice number fetched from database in invoice number textbox
    And user clicks on submit button
    Then user should be able to view the searched account
    When user moves the control on right side of the page and see the Defect Workflow section
    Then user should be able to view the Defect workflow section
    And user should be able to view the progress bar with following steps
      | CONFIRM | TRIAGE | ACTION |
    And user should be able to view the progesssbar selected as "CONFIRM"
    And user should be able to view the assigned defect sub category below progress bar

    Examples: 
      | query1                     | query2                     |
      | DefectOverride_391266_SQL1 | DefectOverride_391266_SQL2 |
