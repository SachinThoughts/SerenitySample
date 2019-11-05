@WorkflowConfiguration
Feature: Verify WorkFlowConfiguration related scenarios in PRCM
  To check WorkFlowConfiguration related scenarios in PRCM application

  Background: User is able to navigate to Settings home page
    Given user having AHtoDecision Admin role is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on Workflow Configuration link

  @434762 @AHtoDecisionAdmin @Sprint8
  Scenario: Verify user with AHtoDecision Admin role is able to access "Workflow Configuration" screen
    Given user having AHtoDecision Admin role is on workflow configuration home page
    Then user should be able to view Hand off tab selected by default
    And the hand off tab is highlighted in blue color

  @434763 @AHtoDecisionAdmin @Sprint8
  Scenario: Verify controls under Handoff tab in "Workflow Configuration" screen
    Given user having AHtoDecision Admin role is on workflow configuration home page
    Then user should be able to view tabs
      | HANDOFF          |
      | RECIPIENT        |
      | ACTION TYPE      |
      | DISPOSITION TYPE |
    And user should be able to view Workflow Summary label with selected Handoff type value
    And user should be able to view +Add Handoff button
    And user should be able to view Continue > button
    And user should be able to view grid with column headers
      | Handoff | Description | Visible to Group | Activate Handoff |
    And user should be able to view Edit link button
    And user should able to view Radio button checked against first handoff type

  @434765 @AHtoDecisionAdmin @Sprint8
  Scenario: Verify that user is able to edit handoff type
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user clicks on Edit link button against particular hand off type in Choose Handoff grid
    Then user should be able to view Edit Handoff pop up window with labels
      | Workflow Name | Workflow Description | Worklist | AH Module Code | Visible to Group | Activate Handoff |
    Then user should able to view following controls
      | Close | Save changes |
    And user should be able to view prepopulated values in all controls
    When user clicks on Close button
    Then Edit Handoff pop up window should get closed with no data saved
    When user clicks on Edit link button against particular hand off type in Choose Handoff grid
    And user updates value in any of the fields
    And user clicks on Save changes button
    Then user should be able to view handoff message "HandOff Updated Successfully"
    And user should no longer be able to view Edit Handoff popup window
    And user should be able to view Updated values related to handoff type in Choose Handoff grid

  @434764 @AHtoDecisionAdmin @Sprint8
  Scenario: Verify that user is able to add new handoff type
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user clicks on +Add Handoff button
    Then user should be able to view Add Handoff pop-up window with controls
      | Workflow Name        |
      | Workflow Description |
      | Worklist             |
      | AH Module Code       |
      | Visible to Group     |
      | Activate Handoff     |
    Then user should able to view following controls
      | Close        |
      | Save changes |
    When user clicks on Close button
    When user clicks on +Add Handoff button
    And user enters value in Workflow Name on popup window
    And user enters value in Workflow Description textbox: "DescriptionTest123"
    And user selects AHtoDecisionHandoff value from Worklist dropdown
    And user enters value in AH Module Code textbox: "CodeTest1"
    And user selects any value from Visible to Group dropdown for Add Handoff
    And user clicks on Save Changes button for Add Handoff
    Then user should be able to view the appropriate success message: "HandOff Inserted Successfully"
    And user should no longer be able to view Add Handoff pop-up window
    And user should be able to view newly added handoff in the Choose Handoff grid

  @434766 @AHtoDecisionAdmin @Sprint8
  Scenario Outline: Verify controls under Recipient page
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button
    Then user should be able to navigate to Recipient tab
    Then user should be able to view Recipient tab highlighted in blue color
    And user should be able to view Workflow Summary label with selected Recipient appended after Handoff type value
    And user should be able to view +Add Recipient and Continue > button
    And user should able to view Choose Recipient Label
    And user should be able to view grid with columns headers
      | Name | Description | Active |
    And user should be able to view Edit icon button adjacent to Recipient and Radio button checked against first Recipient
    And user should be able to view Details link button for respective Recipient
    When user clicks on Details link button
    Then user should be able to view detailed columns
      | Created Date | Created By | Updated Date | Updated By |

    Examples: 
      | query1                         | query2                          |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 |
