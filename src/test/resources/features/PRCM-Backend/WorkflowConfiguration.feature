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

  @434773 @AHtoDecisionAdmin @Sprint9
  Scenario Outline: Verify Add New Disposition functionality
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    And user verifies that radio button is selected against the Recipient
    And user clicks on Continue button on Recipient tab
    And user verifies that radio button is selected to associated Action Type
    And user clicks on Continue button on Action type Tab
    And user clicks on +Add New Disposition button
    Then user should be able to view Add New Disposition pop up with controls
      | Disposition Code | Disposition Name | Next Disposition By | Follow Up Days | Respond Deadline | Disposition Status | Predefined Note | Active |
    And user can see Save changes button on the Disposition popup
    When user clicks on Save Changes button without entering any text
    Then user should able to view info message "Please enter Disposition Code"
    When user enters alphanumeric text in Disposition Code textbox
    And user clicks on Save Changes button on Disposition pop up
    Then user should able to view info message "Please enter Disposition Name"
    And user enters text in Disposition Description textbox: "DispositionDesc123"
    And user clicks on Save Changes button on Disposition pop up
    Then user should able to view info message "Please select Next Disposition By."
    When user select "AR Supervisor" value from Next Disposition By drop down, other than --Select one-- option
    And user clicks on Save Changes button on Disposition pop up
    Then user should be able to view selected value in Next Disposition By drop down
    And user should able to view info message "Please enter Follow Up Days"
    And For disposition user enters "0" in Follow Up Days textbox
    And user clicks on Save Changes button on Disposition pop up
    Then user should able to view info message "Please enter Respond Deadline"
    And For disposition user enters: "999" in Follow Respond Deadline textbox
    And user clicks on Save Changes button on Disposition pop up
    Then user should able to view info message "Please select Disposition Status."
    When For disposition user selects "Identified" option from Desposition Status dropdown
    Then user should be able to view selected value in Disposition Status drop down
    And user enters notes under Predefined Note textarea
    And user clicks on Save Changes button on Disposition pop up
    Then user should be able to view the appropriate success message: "Saved successfully"
    And user should no longer be able to view Add New Disposition pop-up window
    And user should be able to view the newly created Disposition in Choose Disposition Type grid with correct data in the columns
    When user clicks on Details link button adjacent to newly created Disposition Name
    And user login to SQL server and connect to database
    And user runs the Add Disposition Detail query "434773_WFConfig_NewDisposition"
    Then user should be able to view same value in Created Date and CreatedBy columns on UI as in SQL result

    Examples: 
      | query1                         | query2                          |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 |

  @434767 @AHtoDecisionAdmin @Sprint101
  Scenario Outline: Verify user is able to add a new recipient using Add Recipient functionality
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    And user clicks on +Add Recipient button under choose recipient
    Then user should be able to view Add Recipient pop up with controls
      | Recipient Name        |
      | Recipient Description |
      | Active                |
    And user should able to view following options on Recipient popup
      | Close         |
      | Add Recipient |
    When user clicks on Close button on Add Recipient popup
    And user clicks on +Add Recipient button under choose recipient
    And user enters text in Recipient Name textbox on Recipient popup
    And user enters text in Recipient Description textbox like "RecipientDescriptionTest123"
    And user clicks on Add Recipient button on the popup
    Then user should be able to view the appropriate success message: "Record inserted successfully !"
    And user should be able to view newly created Recipient in Choose Recipient grid with correct data
    And user should be able to view newly created Recipient name in Workflow Summary breadcrumb just after Handoff type
    When user clicks on Details link button adjacent to newly created Recipient
    And user login to SQL server and connect to database
    And user executes the query to fetch added recipient <query3>
    Then user should be able to view same value in following columns on Recepient Tab as in SQL result

    Examples: 
      | query1                         | query2                          | query3                               |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 | 434767_BDD_R1D_WFConfig_AddRecipient |

  @434770 @AHtoDecisionAdmin @Sprint101
  Scenario Outline: Verify Add New Action functionality
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    And user run the query to fetch recipient name <query3>
    And user clicks on radio button against any fetched Recipient
    And user clicks on Continue button on Recipient tab
    And user clicks on +Add New Action button
    Then user should be able to view Add New Action pop up window with controls
      | Action Name | Action Description | Next Action By | Follow Up Days | Respond Deadline | Action Status | Active | Required | Close | Save changes |
    When user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please enter Action Name"
    When user enters text in Action Name textbox
    And user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please enter Action Description"
    When for Actions user enters text: "ActionDescTest123" in Action Description textbox
    And user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please select Next Action By."
    When for Actions user selects "BSO_Followup" option from Next Action By dropdown
    And user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please enter Follow Up Days"
    When for Actions user enters "0" in Follow Up Days textbox
    And user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please enter Respond Deadline"
    When for Actions user enters: "999" in Follow Respond Deadline textbox
    And user clicks on Save Changes button on action popup
    Then user should able to view info message on action popup "Please select Action Status."
    When for Actions user selects: "Identified" option from Action Status dropdown
    And user clicks on Required checkbox
    When user clicks on Save Changes button on action popup
    Then user should be able to view the appropriate success message: "Saved successfully"
    And user should no longer be able to view Add New Action pop-up window
    And user should be able to view newly created Action in Choose Action Type grid
    When user clicks on Details link button adjacent to newly created Action Name
    And user login to SQL server and connect to database
    And user run the query to fetch Action Details <query4>
    Then user should be able to view same value in details columns on UI as in SQL result

    Examples: 
      | query1                         | query2                          | query3                        | query4                            |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 | 434767_WFConfig_ReorderAction | 434770_BDD_R1D_WFConfig_NewAction |

  @434772 @AHtoDecisionAdmin @Sprint101
  Scenario Outline: Verify data & controls in "Choose a Disposition Type" grid
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user clicks on continue button on Handoff tab
    And user verifies that radio button is selected against the Recipient
    And user clicks on Continue button on Recipient tab
    And user verifies that radio button is selected to associated Action Type
    And user clicks on Continue button on Action type Tab
    Then user should be able to navigate to Disposition type page
    And user should be able to view Disposition Type tab selected highlighted in blue color
    And user should able to view Workflow Summary label with selected Disposition Type appended
    And user should be able to view Choose a Disposition Type grid with buttons underneath
      | Add New Disposition | Save Configuration |
    And user should be able to view Save Configuration button disabled
    And user should be able to view disposition grid with columns headers
      | Disposition Name | Follow Up Days | Time Limit | Status | Active |
    And user should be able to view Edit link button adjacent to associated Disposition Type
    And user should be able to view Details button for particular Disposition Type
    And user should be able to view Reorder link button against each Disposition Type
    When user clicks on Details link button adjacent to any Disposition Type
    Then user should be able to view detailed columns
      | Created Date | Created By | Updated Date | Updated By |
    When user clicks on Details link again
    Then expanded grid for selected Disposition Type gets collapsed
    And user should no longer be able to view the associated fields

    Examples: 
      | query1                         | query2                          |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 |

  @434776 @AHtoDecisionAdmin @Sprint102
  Scenario Outline: Verify saved handoff type, associated Recipient, Actions and Disposition types from DB
    Given user having AHtoDecision Admin role is on workflow configuration home page
    When user login to SQL server and connect to database
    And user run the query to fetch hand-off id <query1>
    And user run the query to fetch hand-off name <query2>
    And user fetches any Handoff Type from DB
    And user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid
    And user run the query by passing selected Handoff name <query3>
    And user clicks on continue button on Handoff tab
    And user run the query to fetch recipient name <query4>
    And user clicks on radio button against any fetched Recipient
    And user run the query by passing selected Recipient name <query5>
    Then user should be able to fetch the Workflowtypeid and SubTypeID for respective Handoff type and associated Recipient
    When user run the query by passing fetched Workflowtypeid and SubTypeID <query6>
    And user clicks on Continue button on Recipient tab
    And user run the query by passing Workflowtypeid and SubTypeID to fetch ActionID <query7>
    Then user should be able to view Number of actions in db as reflected in UI
    When user run the query by passing fetched ActionId <query8>
    Then user should be able to view same action in db as reflected on UI
    When user selects any actions from Choose Action Type grid
    And user clicks on Continue button on Action type Tab
    And user run the query by passing ActionId of selected Action <query9>
    Then user should be able to view same disposition type in DB as as reflected on UI

    Examples: 
      | query1                         | query2                          | query3                                | query4                        | query5                                   | query6                                   | query7                                      | query8                          | query9                                     |
      | 434767_WFConfig_CheckRecipient | 434767_WFConfig_CheckRecipient1 | 434776_BDD_R1D_WFConfig_workflowtypes | 434767_WFConfig_ReorderAction | 434776_BDD_R1D_WFConfig_workflowSubtypes | 434776_BDD_R1D_WFConfig_workflowSubtypesDetails | 434776_BDD_R1D_WFConfig_WorkflowTypeActions | 434776_BDD_R1D_WFConfig_Actions | 434776_BDD_R1D_WFConfig_ActionDispositions |
