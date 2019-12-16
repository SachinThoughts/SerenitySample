@WriteOff
Feature: Verify write off related testcases in PRCM-BE

  Background: User is able to navigate to R1 Decision page
    Given user having level 1 Approver role is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on Workflow Configuration link
    And user clicks radio button against "AR Supervisor" Handoff
    And user clicks on Continue button on HandOff Tab
    And user clicks on radio button against "Supervisor Request" Recipient
    And user clicks on Continue button on Recipient tab
    And user clicks on Edit link against "Write-off Request" Action Type
    And for Actions user enters "0" in Follow Up Days textbox
    And for Actions user enters: "999" in Follow Respond Deadline textbox
    And user clicks on Save Changes button on action popup
    Then user should be able to view the appropriate success message: "Saved successfully"
    When user clicks on Billing & Follow-up link from footer
    And user clicks on R1_Decision link
    And user login to SQL server and connect to database
    And user runs the "getAccountsForWriteOff" query to fetch the invoice number
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user fetch the InvoiceNumber from database
    And user enters Invoice number
    And user clicks on Submit button

  @391092 @Sprint103 @PRCMUser
  Scenario: Verify that user is able to  see Write-Off  Pop-up Window with mandatory fields and Nextfollowup date=0 and Timelimitdate=999
    Given user is on account page
    When user clicks on Approvals link
    Then user should be able to view the Approval Request  popup
    And user should be able to view Category drop down
    And user should be able to view Write Off Type dropdown
    And user should be able to view Write Off Amount dropdown
    And user should be able to view Notes text area
    And user should be able to view Close button
    And user should be able to view Save button
    When user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    Then user should be able to view T-Code to Use drodown
    And user selects "DENIED FOR FREQUENCY ADJUSTMENT-7057" from T-Code to Use dropdown
    And user enters amount "1.00" in Write off Amount textbox
    And user enters "Automation Testing Note" in Notes textbox
    And user clicks on write off Save button
    Then user should be able to view the message "Writeoff Request Saved Successfully" after clicking Save button
