@DefaultHandoff
Feature: Verify default handoff

  Background: 
    Given user is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And click on Workflow Configuration link

  @434985 @434986 @434987 @434988 @Sprint8 @PRCMUser
  Scenario: Verify the functionalities of Add Handoff, Recipient, Action Type and Disposition Type in Workflow Configuration screen
    Given user having AHtoDecision role is on "AHtoDecision Workflow Configuration" Screen
    And user is able to login to sql server and connect to database
    Then user should be able to view +Add Handoff button on Handoff screen grid
    When user clicks on +Add Handoff button
    Then user should be able to view popup window with following fields
      | Workflow Name        |
      | Workflow Description |
      | Worklist             |
      | AH Module Code       |
      | Visible to Group     |
      | Activate Handoff     |
      | Close                |
      | Save changes         |
    When user enters value in Workflow Name
    And user enters value in Workflow Description textbox: "DescriptionTest123"
    And user selects AHtoDecisionHandoff value from Worklist dropdown
    And user enters value in AH Module Code textbox: "CodeTest1"
    And user selects any value from Visible to Group dropdown for Add Handoff
    And user clicks on Save Changes button for Add Handoff
    Then user should be able to view the appropriate success message: "HandOff Inserted Successfully"
    And Default Handoff user runs the query to verify if the handoff is inserted "Defaulthandoff_434985_SQL1"  
    Then user should be able to view newly inserted Handoff type in SQL result
    When user selects radio button corresponding to the newly created Handoff and user clicks on Continue button
    Then user should be able to navigate to Recipient tab and user should be able to view the message "No Recipient exists for the selected Handoff" under Choose Recipient Grid
    When user clicks on +Add Recipient button
    And user enters text in Recipient Name textbox
    And user enters text in Recipient Description textbox: "RecipientDescriptionTest123"
    And user clicks on Add Recipient button on the popup
    Then user should be able to view the appropriate success message: "Record inserted successfully !"
    And user should be able to view newly created Recipient in Choose Recipient grid with correct data in columns
    And user should be able to view newly created Recipient name in Workflow Summary breadcrumb just after Handoff type (For eg. Handoff Type >> Recipient name)
    And user should be able to navigate to Action Type tab by clicking on Continue button
    Then user should be able to view the info message "No Action exists for the selected Recipient" under Choose Action Type grid
    When user clicks on +Add New Action button
    And user enters text in Action Name textbox
    And for Actions user enters text: "ActionDescTest123" in Action Description textbox
    And for Actions user selects "BSO_Followup" option from Next Action By dropdown
    And for Actions user enters "0" in Follow Up Days textbox
    And for Actions user enters: "999" in Follow Respond Deadline textbox
    And for Actions user selects: "Identified" option from Action Status dropdown
    And user clicks on Save Changes button on action popup
    Then user should be able to view the appropriate success message: "Saved successfully"
    And user should be able to view newly created Action in Choose Action Type grid with correct data in the columns
    And user should be able to view newly created Action Name updated in Workflow Summary breadcrumb just after Handoff type and Recipient Name (For eg. Handoff Type >> Recipient name >> Action Name)
    And user clicks on Continue button to move to Desposition tab and user should be able to view  the message "No Disposition exists for the selected action type" under Choose Disposition Type grid
    When user clicks on +Add New Disposition button
    And user enters text in Disposition Code textbox
    And user enters text in Disposition Description textbox: "DespositionDesc123"
    And user selects any option from Next Desposition By dropdown
    And For disposition user enters "0" in Follow Up Days textbox
    And For disposition user enters: "999" in Follow Respond Deadline textbox
    And For disposition user selects "Identified" option from Desposition Status dropdown
    And user enters text: "Note123" in Predefined Note text area
    And user clicks on Save Changes button on disposition popup
    Then user should be able to view the appropriate success message: "Saved successfully"
    And user should be able to view the newly created Disposition in Choose Disposition Type grid with correct data in the columns
