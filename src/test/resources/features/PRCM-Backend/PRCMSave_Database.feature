@PRCMSave
Feature: Verify PRCM Save related test cases in PRCM_BE

  Background: user is able to connect to SQL server for PRCM facility
    Given user login to SQL server and connect to facility database

  @391019 @Sprint103 @PRCMUser
  Scenario: Verify that Sop Actions should be loaded in Defect Workflow section according to current Defect of an Invoice
    Given user login to SQL server and connect to facility database
    And user is on R1 Hub page
    When user runs the query "Account_Action_History_391051_SQL1" to fetch "Invoice Number"
    Then user should be able to view some invoice id fetched from DB
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" from operator dropdown
    And user enters Invoice Number fetched from database in invoice number textbox
    And user clicks on Submit button
    Then user is able to view the Account Page
    When user clicks on Next button
    Then user should be able to view "Verify All Steps Taken" section open and placeholder Triage
    And user should be able to view below fields
      | Previous | Next |
    When user clicks on Previous button
    Then user should be able to view navigation to Override Defect Category section
    When user clicks on Next button
    Then user should be able to view the "Verify All Steps Taken" section
    When user runs the query "PRCM_Save_391019_SQL2" for fetching DefectTypeId
    And user fetch Defect typeid
    And user runs the query "PRCM_Save_391019_SQL3" with passing by defecttypeid
    And user fetch Defectsubcategoryid
    Then user should be able to view Defect SubCategory id for which SOP loaded
    When user clicks on Next button on TriagePage
    Then user should be able to view optional and mandatory actions
    When user clicks on Previous button on Action Section
    And user runs the query "PRCM_Save_391019_SQL4" for fetching SOP having IsRequired=0
    Then user should be able to view SOP actions having IsRequired=0
    And user should be able to view the optional Sop actions in verify All Steps Taken section
    When user clicks on Next button on TriagePage
    And user runs the query "PRCM_Save_391019_SQL5" for fetching SOP having IsRequired=1
    Then user should be able to view SOP actions having IsRequired=1
    And user should be able to view the optional Sop actions in Steps Taken section

  @391214 @Sprint103
  Scenario: Verify new application Name R1DProfessional has been created
    Given user login to SQL server and connect to "Accretive" database
    When user runs the query "PRCM_Save_391214_SQL17" to fetch "Application name and Application ID"
    Then user should be able to view the Application name and Application ID
    And user should be able to view selected database as "Accretive"

  @391216 @Sprint103 @PRCMUser
  Scenario: Verify the Skills for professional defect subcategory
    Given user login to SQL server and connect to facility database
    And user is on R1 Hub page
    When user runs the query "Account_Action_History_391051_SQL1" to fetch "Invoice Number"
    Then user should be able to view some invoice id fetched from DB
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" from operator dropdown
    And user enters Invoice Number fetched from database in invoice number textbox
    And user clicks on Submit button
    Then user is able to view the Account Page
    When user runs the query "PRCM_Save_391216_SQL19" to fetch "DefectCategoryID"
    And user fetch Defectsubcategoryid
    And user runs the query "PRCM_Save_391216_SQL20" with passing by defectsubcategoryid
    Then user should be able to view the Skillid for all Major payer for defectsubcategoryid