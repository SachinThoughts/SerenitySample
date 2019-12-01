@CallPayerQueue
Feature: Verify Call Payor Queue functionality

  Background: user is able to navigate to Account Page
    Given user is on R1 Hub Page with BSO_Followup user
    When user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    When user login to SQL server and connect to database
    And user runs the "getAccountsForWriteOff" query to fetch account for writeoff
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user fetch the InvoiceNumber from database
    And user enters Invoice number
    And user clicks on Submit button

  @427121 @Sprint102 @BSOFollowUpUser
  Scenario: Verify that the Account is dropped from CPQ when R1D Approver takes Deny Action on the Account
    Given user is on account page
    When user clicks on Approvals link
    And user selects "Write off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "DENIED FOR FREQUENCY ADJUSTMENT-7057" from T-Code to Use dropdown
    And user enters amount "1" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view write-off request on account
    When user clicks on Billing & Follow-up link from footer
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    #And user clicks on Add to queue button in Call Payer Queue Section
    #Then user should be able to view first account loaded in users queue
    #And user should be able to view write-off request on account
    #And user should be able to view the account to users CPQ
    #When user logins to the application with R1D Approval Role
    #When user clicks on Billing & Follow-up link
    #And user clicks on R1_Decision link
    #And user search for account on which Write off request has been taken
    #And user scrolls down to the Write Off section
    #And user clicks on radiobutton Deny
    #And user clicks on Submit button
    #Then user should be able to view the message Writeoff Request Denied
    #When user logins to the application with BSO_Followup Role
    #And user clicks on Billing & Follow-up link
    #And user clicks on R1_Decision link
    #Then user search for account on which Write off request has been taken
    #And user should be able to view the account dropped from CPQ
