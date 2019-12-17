@CallPayerQueue
Feature: Verify Call Payer Queue functionality

  Background: user is able to navigate to Account Page
    Given user is on R1 Hub Page with BSO_Followup user
    When user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    When user login to SQL server and connect to database
    And user runs the "getAccountsForWriteOff" query to fetch account for CPQ
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
    And user logins to the application with "R1D_Approval" Role
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
    And user logins to the application with "BSO_Followup" Role
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
    And user logins to the application with "R1D_Approval" Role
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
    And user logins to the application with "BSO_Followup" Role
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
    And user logins to the application with "R1D_Approval" Role
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
    And user logins to the application with "BSO_Followup" Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user enters Invoice number
    And user clicks on Submit button
    Then user should be able to view the account dropped from CPQ

  @427124 @Sprint103 @BSOFollowUpUser
  Scenario: Verify that the Account on which Write-off Response has been taken are displayed in the Recently Worked Accounts section for the R1D Approver who has taken Write-off Response
    Given user is on account page
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "4.00" in Write off Amount textbox
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
    And user logins to the application with "R1D_Approval" Role
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
    When user clicks on Recent Account Button
    Then user should be able to view the popup with all the recently worked accounts
    And user should be able to view the account on which Write-Off Response has been taken

  @427127 @Sprint103 @BSOFollowUpUser
  Scenario: Verify that user is not able to add duplicate accounts in Call Queue
    Given user is on account page
    When user clicks on Add to queue button in Call Payer Queue Section
    Then user should be able to view Add to Call Queue pop-up
    When user enters notes "Automation testing Notes" in Notes Section
    And user clicks Add with Note button
    Then user should be able to view the incremented count of accounts by 1 in Call Queue Section
    And user should be able to view the account to users CPQ
    When user clicks on Add to queue button in Call Payer Queue Section
    Then user should be able to view Add to Call Queue pop-up
    When user enters notes "Automation testing Notes2" in Notes Section
    And user clicks Add with Note button to add the account that already exists in Call Queue
    Then user should not be able to view the incremented count of accounts by 1 in Call Queue Section
    And user should not be able to view duplicate account in Call Queue Section

  @427119 @Sprint103 @BSOFollowUpUser
  Scenario: Verify that the Account is dropped from CPQ when R1D Approver takes Approve Action on the Account
    Given user is on account page
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "4.00" in Write off Amount textbox
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
    And user logins to the application with "R1D_Approval" Role
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
    Then user should be able to view the account dropped from CPQ

  @427126 @Sprint103 @R1DApproverUser
  Scenario: Verify that account get dropped from CPQ if the write off action is still pending
    Given user is on account page
    When user clicks on Add to queue button in Call Payer Queue Section
    When user enters notes "Automation testing Notes" in Notes Section
    And user clicks Add with Note button
    And user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "4.00" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view write-off request on account
    And user should be able to view the account dropped from CPQ
