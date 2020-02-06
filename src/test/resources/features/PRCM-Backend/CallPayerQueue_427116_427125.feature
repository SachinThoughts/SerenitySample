@CallPayerQueue
Feature: Verify Call Payer Queue functionality

  Background: user is able to navigate to R1 Configuration Setting Page
    Given user is on R1 Hub page
    When user clicks on setting link
    And user hovers IT Tools link
    And user clicks on R1 Configuration

  @427116 @Sprint102 @PRCMUser
  Scenario Outline: There should be configuration settings for Call payer queue Park,Skip and Checkout Limit
    Given user is on R1 Configuration Setting Page
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Park-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update A2D-Park-Limit Value as per configuration requirement
    And user enters the setting value <A2D-Park-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the Application setting list screen
    And user should be able to view the setting "A2D-Park-Limit" in acceretive configuration page
    And user should be able to view the updated value of setting <A2D-Park-Limit>
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Skip-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update A2D-Skip-Limit Value as per configuration requirement
    And user enters the setting value <A2D-Skip-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the updated value of setting <A2D-Skip-Limit>
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Checkout-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update A2D-Checkout-Limit Value as per configuration requirement
    And user enters the setting value <A2D-Checkout-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the Application setting list screen
    And user should be able to view the setting "A2D-Checkout-Limit" in acceretive configuration page
    And user should be able to view the updated value of setting <A2D-Checkout-Limit>

    Examples: 
      | A2D-Park-Limit | A2D-Skip-Limit | A2D-Checkout-Limit |
      |             48 |             24 |                  4 |

  @427125 @Sprint103 @PRCMUser
  Scenario: Verify that account is removed from call queue after taking Writeoff request
    Given user is on R1 Configuration Setting Page
    When user selects "Setting Name" from search dropdown
    And user enters "R1D_CPQCommandAPI" in textbox
    And user clicks on search button
    And user clicks on edit button to update value
    And user enters the setting value FALSE in setting value textbox
    And user clicks on Update Appsetting button
    And user enters "R1D_CPQQueryAPI" in textbox
    And user clicks on search button
    And user clicks on edit button to update value
    And user enters the setting value FALSE in setting value textbox
    And user clicks on Update Appsetting button
    When user logout from the application
    And user logins to the application with "R1D_Approval" Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    When user login to SQL server and connect to database
    And user runs the "getAccountsForWriteOff8" query to fetch account for CPQ
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" from operator dropdown
    And user fetch the InvoiceNumber from database
    And user enters Invoice number
    And user clicks on Submit button
    And Remove searched account if present in Call payer queue
    And user clicks on Add to queue button in Call Payer Queue Section
    And user clicks Add without note
    Then user should be able to view the account to users CPQ
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "2.00" in Write off Amount textbox
    And user enters "Automation Testing Note1" in Notes textbox
    And user clicks on write off Save button
    And user enters Invoice number
    And user clicks on Submit button
    Then user should be able to view write-off request on account
    And user should be able to view the saved account in Account Action History section
    And user should be able to view the account deleted from CPQ
    When user clicks on Next Account button
    Then user should be able to view the account deleted from CPQ
    When user logout from the application
    And user logins to the application with "PRCM BE" Role
    When user clicks on setting link
    And user hovers IT Tools link
    And user clicks on R1 Configuration
    When user selects "Setting Name" from search dropdown
    And user enters "R1D_CPQCommandAPI" in textbox
    And user clicks on search button
    And user clicks on edit button to update value
    And user enters the setting value TRUE in setting value textbox
    And user clicks on Update Appsetting button
    And user enters "R1D_CPQQueryAPI" in textbox
    And user clicks on search button
    And user clicks on edit button to update value
    And user enters the setting value TRUE in setting value textbox
    And user clicks on Update Appsetting button
    When user logout from the application
    And user logins to the application with "R1D_Approval" Role
    And user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" from operator dropdown
    And user fetch the InvoiceNumber from database
    And user enters Invoice number
    And user clicks on Submit button
    And Remove searched account if present in Call payer queue
    And user clicks on Add to queue button in Call Payer Queue Section
    And user clicks Add without note
    Then user should be able to view the account to users CPQ
    When user clicks on Approvals link
    And user selects "Write Off" option from Category dropdown
    And user selects "Medical Necessity" from Write Off Type dropdown
    And user selects "MEDICAL NECESSITY ADJUSTMENT-7018" from T-Code to Use dropdown
    And user enters amount "2.00" in Write off Amount textbox
    And user enters "Automation Testing Note2" in Notes textbox
    And user clicks on write off Save button
    And user enters Invoice number
    And user clicks on Submit button
    Then user should be able to view write-off request on account
    And user should be able to view the saved account in Account Action History section
    And user should be able to view the account deleted from CPQ
    When user clicks on Next Account button
    Then user should be able to view the account deleted from CPQ