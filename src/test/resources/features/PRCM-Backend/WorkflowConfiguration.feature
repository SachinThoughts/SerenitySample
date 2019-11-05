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

  @434766 @AHtoDecisionAdmin @Sprint9
  Scenario Outline: Verify controls under Recipient page
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    Then user should be able to navigate to Recipient tab
    Then user should be able to view Recipient tab highlighted in blue color
    And user should be able to view Workflow Summary label with selected Recipient appended after Handoff type value
    And user should be able to view +Add Recipient and Continue > button
    And user should able to view Choose Recipient Label
    And user should be able to view grid with columns headers
      | Name | Description | Active |
    And user should be able to view Edit icon button adjacent to Recipient and Radio button checked against first Recipient
    And user should be able to view Details link button for respective Recipient
    When user clicks on Details link button on Recipient Tab
    Then user should be able to view detailed columns on Recipient Tab
      | Created Date | Created By | Updated Date | Updated By |

    Examples: 
      | query1                         | query2                          |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 |

  Scenario Outline: Verify Add New Disposition functionality
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    And user clicks on radio button adjacent to associated Recipient and clicks on Continue button on the Recipient Tab
    And user clicks on radio button adjacent to associated Action Type
    And user clicks on Continue button on Action Tab
    And user clicks on +Add New Disposition button
    Then user should be able to view Add New Disposition pop up with controls
      | Disposition Code | Disposition Name | Next Disposition By | Follow Up Days | Respond Deadline | Disposition Status | Predefined Note | Active | Save changes |
    When user clicks on Save Changes button without entering any text
    Then user should be able to view info message "Please enter Disposition Code"
    When user enters alphanumeric text in Disposition Code textbox
    And user clicks on Save Changes button
    Then user should able to view info message "Please enter Disposition Name"
    When user enters text in Disposition Name textbox
    And user clicks on Save Changes button
    Then user should be able to view info message "Please select Next Disposition By."
    When user select any value from Next Disposition By drop down, other than --Select one-- option
    And user clicks on Save Changes button
    Then user should be able to view selected value in Next Disposition By drop down
    And user should be able to view info message "Please enter Follow Up Days"
    When user enters numeric value in Follow Up Days
    And user clicks on Save Changes button
    Then user should be able to view info message "Please enter Respond Deadline"
    When user enters numeric value in Respond Deadline
    And user clicks on Save Changes button
    Then user should be able to view info message "Please select Disposition Status."
    When user selects value "Identified" from Disposition Status drop down
    Then user should be able to view selected value in Disposition Status drop down
    When user clicks on Predefined Note textarea
    And user enters text in Predefined Note Textarea And user clicks on Save Changes button
    And user should be able to view message "Saved successfully"
    And user should no longer be able to view Add New Disposition pop-up window
    And user should be able to view saved data in Choose a Disposition Type grid in Disposition Name, Follow Up Days, Time Limit, Status and Active columns
    When user clicks on Details link button adjacent to newly created Disposition Name
    And user login to SQL server and connect to facility database
    And user runs the "434773_WFConfig_NewDisposition" query
    Then user should be able to view same value in following columns on UI as in SQL result
      | Created Date | Created By |
