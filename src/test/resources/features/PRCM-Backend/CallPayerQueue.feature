@CallPayoerQueue
Feature: Verify Call Payor Queue functionality

Background: user is able to navigate on Account Page
Given user is on R1 Hub page
When user clicks on Billing & follow up link
And user hovers on R1_Decision link
And user clicks on Search sub menu
And user selects Invoice Number from search by dropdown
And user selects = option
And user enters Invoice Number fetched from database in invoice number textbox
And user clicks on Submit button

@427107
Scenario: Verify that user is able to add an account without Notes in Call Queue  and view the same along with incremented count of accounts in Call Queue section
Given user is on Account Page
When user clicks on Add to Queue button under Call Queue Section
Then user should be able to view Add to Call Queue Pop up window with option to add account to Queue with or without note
And user should be able to view Add without note button enabled by default
When user clicks on Add Without Note button
And user navigates to Call Queue Section
Then user should not be able to view Add to Call Queue pop-up on clicking Add with Note button
And user should be able to view the count in Call Queue Section incremented by 1 for each added account
When user navigates to Settings Link
And user hovers IT Tools Link
And user clicks on R1 Configuration Link
Then user should be able to view R1 Configuration Screen
When user selects settings Name from Search Dropdown
And user enters setting name  A2D_MOVE_TO_NEXTACCOUNT_AHC01
And user clicks on search button
Then user should be able to view the settings value ON or OFF
When user selects the setting Value of A2D_MOVE_TO_NEXTACCOUNT_AHC01 setting ON
Then user should be able to view next account for shared services
When user selects the setting Value of A2D_MOVE_TO_NEXTACCOUNT_AHC01 setting OFF
Then user should be able to view on account page
When user enters setting name  A2D_MOVE_TO_NEXTACCOUNT_AHC06
And user clicks on search button
Then user should be able to view the settings value ON or OFF
When user selects the setting Value of A2D_MOVE_TO_NEXTACCOUNT_AHC06 setting ON
Then user should be able to view the next account for IMH
When user selects the setting Value of A2D_MOVE_TO_NEXTACCOUNT_AHC01 setting OFF
Then user should be able to view the account page
When user clicks on Toggle Call Queue arrow button to expand the Call Queue section
Then user should be able to view the matching information details of newly added account from Patient & Facility Info Section |information|Major Payor|Payer Name|Account Number|Facility Code|
When user hovers the Account Number
Then user should be able to view the account information in Payer Queue Info Pop up in CPQ Section |Patient Name|Defect Category|Insurance Balance|Time Added|Time Expired|
When user click on hyperlinked account number of added account
Then user should be able to view the navigation to the account screen irrespective of the current facility selected
