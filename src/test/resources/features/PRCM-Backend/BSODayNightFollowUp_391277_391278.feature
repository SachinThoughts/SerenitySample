@BSODayNight @NonDB
Feature: Verify BSODayNight related scenarios in PRCM
  To check BSODayNight related scenarios in PRCM application

  Background: user is able to log in the application
    Given user having AHtoDecision Admin role is on R1 Hub page
    When user clicks on setting link

  @391277 @Sprint102 @PRCMQueueUser
  Scenario: Verify Controls in BSO-Day Followup Handoff at Invoice Level and Request Workflow
    Given user is on setting page
    When user clicks on Settings-R1_Decision
    And user clicks on Workflow Configuration link
    And user selects "BSO-Day/Night FollowUp" radio button and clicks on continue button
    And user selects "BSO Day FollowUp" radio button and clicks on continue button to move Action Type tab
    Then user should be able to view name as "BSO Day/Night FollowUp"
    And user should be able to view follow-up date as 0 and Time limit as 999
    When user clicks on Billing & Follow-up Footer link
    And user clicks on R1_Decision link
    And user clicks on Hand-off link
    And user select "BSO-Day/Night FollowUp" as Handoff type from Handoff type dropdown
    Then user should be able to view
      | Hand Off Type                 |
      | Create                        |
      | Note                          |
      | Save                          |
      | Close                         |
      | Save and Move to Next Account |
    And user should be able to view note as "TextArea"
    When user clicks on Create Dropdown menu
    Then user should be able to view below dropdown value
      | --Directions--     |
      | BSO Day FollowUp   |
      | BSO Night FollowUp |
    And "--Directions--" value should be display by default
    When user clicks "BSO Day FollowUp" in Create dropdown
    Then user should be able to select "BSO Day FollowUp" in Create Dropdown
    When user clicks any value other then Directions in create dropdown
    Then user should be able to view Why dropdown
    When user clicks on Why Dropdown menu
    Then user should be able to view "BSO Day/Night FollowUp"
    When user selects any other value then action in why dropdown
    Then user should be able to view Disposition Dropdown
    And user should be able to provided the disposition is active for that action in Workflow Configuration screen
    When user enters any value in Note
    And user clicks on Save button on HandOff
    Then user should be able to view the validation message "Handoff Record Saved Successfully."
    When user scrolls to account action history section
    And user clicks on Show Account Action History Notes
    Then user should be able to view following details with saved value 
      | Type:        |
      | Action:      |
      | Disposition: |
      | Added:       |
      | Created:     |
      | Followup:    |
    And user should be able to view Added appeared as system date and Created user appeared as handoff taken user

  @391278 @Sprint102 @PRCMQueueUser
  Scenario: Verify Controls in BSO-Night Followup Handoff at Invoice Level  and Request Workflow
    Given user is on setting page
    When user clicks on Settings-R1_Decision
    And user clicks on Workflow Configuration link
    And user selects "BSO-Day/Night FollowUp" radio button and clicks on continue button
    And user selects "BSO Night FollowUp" radio button and clicks on continue button to move Action Type tab
    Then user should be able to view name as "BSO-Night/Day FollowUp"
    And user should be able to view follow-up date as 0 and Time limit as 999
    When user clicks on Billing & Follow-up Footer link
    And user clicks on R1_Decision link
    And user clicks on Hand-off link
    And user select "BSO-Day/Night FollowUp" as Handoff type from Handoff type dropdown
    Then user should be able to view
      | Hand Off Type                 |
      | Create                        |
      | Note                          |
      | Save                          |
      | Close                         |
      | Save and Move to Next Account |
    And user should be able to view note as "TextArea"
    When user clicks on Create Dropdown menu
    Then user should be able to view below dropdown value
      | --Directions--     |
      | BSO Day FollowUp   |
      | BSO Night FollowUp |
    And "--Directions--" value should be display by default
    When user clicks "BSO Night FollowUp" in Create dropdown
    Then user should be able to select "BSO Night FollowUp" in Create Dropdown
    When user clicks any value other then Directions in create dropdown
    Then user should be able to view Why dropdown
    When user clicks on Why Dropdown menu
    Then user should be able to view "BSO-Night/Day FollowUp"
    When user selects any other value then action in why dropdown
    Then user should be able to view Disposition Dropdown
    And user should be able to provided the disposition is active for that action in Workflow Configuration screen
    When user enters any value in Note
    And user clicks on Save button on HandOff
    Then user should be able to view the validation message "Handoff Record Saved Successfully."
    When user scrolls to account action history section
    And user clicks on Show Account Action History Notes
    Then user should be able to view following details with saved value 
      | Type:        |
      | Action:      |
      | Disposition: |
      | Added:       |
      | Created:     |
      | Followup:    |
    And user should be able to view Added appeared as system date and Created user appeared as handoff taken user
