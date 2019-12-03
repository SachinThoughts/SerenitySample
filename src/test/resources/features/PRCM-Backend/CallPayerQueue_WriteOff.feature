@CallPayerQueue
Feature: Verify Call Payer Queue functionality

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
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "DENIED FOR FREQUENCY ADJUSTMENT-7057" from T-Code to Use dropdown
    And user enters amount "1.00" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view write-off request on account
    When user clicks on Billing & Follow-up link from footer
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user clicks on Add to queue button in Call Payer Queue Section
    And user clicks Add without note
    Then user should be able to view write-off request on account
    And user should be able to view the account to users CPQ
    When user logout from the application
    And user logins to the application with R1D Approval Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user scrolls down to the Write Off section
    And user clicks on radiobutton Deny
    And user clicks on write off response Submit button
    And user clicks on review save button
    Then user should be able to view the request "Rejected" status
    When user logout from the application
    And user logins to the application with BSO_Followup Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    Then user should be able to view the account dropped from CPQ

  @427122 @Sprint102 @BSOFollowUpUser
  Scenario: Verify that the Account is dropped from CPQ of Rep when R1D Approver takes Approve Action on the Account.
    Given user is on account page
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "2.00" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view write-off request on account
    When user clicks on Billing & Follow-up link from footer
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user clicks on Add to queue button in Call Payer Queue Section
    And user clicks Add without note
    Then user should be able to view write-off request on account
    And user should be able to view the account to users CPQ
    When user logout from the application
    And user logins to the application with R1D Approval Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user scrolls down to the Write Off section
    And user clicks on radiobutton Approve
    And user clicks on write off response Submit button
    And user clicks on review save button
    And user should be able to view the request "Approved" status
    When user logout from the application
    And user logins to the application with BSO_Followup Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    Then user should not be able to view the account in users CPQ

  @427123 @Sprint102 @BSOFollowUpUser
  Scenario: Verify that the Account is dropped from CPQ of Rep when R1D Approver takes Deny Action on the Account
    Given user is on account page
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Non-Covered" from Write Off Type dropdown
    And user selects "BARIATRIC WRITE-OFF-7164" from T-Code to Use dropdown
    And user enters amount "3.00" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view write-off request on account
    When user clicks on Billing & Follow-up link from footer
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user clicks on Add to queue button in Call Payer Queue Section
    And user clicks Add without note
    Then user should be able to view write-off request on account
    And user should be able to view the account to users CPQ
    When user logout from the application
    And user logins to the application with R1D Approval Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    And user scrolls down to the Write Off section
    And user clicks on radiobutton Deny
    And user clicks on write off response Submit button
    And user clicks on review save button
    And user should be able to view the request "Rejected" status
    When user logout from the application
    And user logins to the application with BSO_Followup Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    Then user should be able to view the account dropped from CPQ
