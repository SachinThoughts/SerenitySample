@DefaultHandoff
Feature: Verify default handoff

  Background: 
    Given user is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And click on Workflow Configuration link

  @434985 @434986 @434987 @434988 @Sprint8 @PRCMUser
  Scenario: Verify the functionalities of Add Handoff, Recipient, Action Type and Disposition Type in Workflow Configuration screen

    Given PRCM user is on "AHtoDecision Workflow Configuration" Screen
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
    And user enters text in Disposition Description textbox: "DispositionDesc123"
    And user selects any option from Next Desposition By dropdown
    And For disposition user enters "0" in Follow Up Days textbox
    And For disposition user enters: "999" in Follow Respond Deadline textbox
    And For disposition user selects "Identified" option from Desposition Status dropdown
    And user enters text: "Note123" in Predefined Note text area
    And user clicks on Save Changes button on disposition popup
    Then user should be able to view the appropriate success message: "Saved successfully"
    And user should be able to view the newly created Disposition in Choose Disposition Type grid with correct data in the columns

  @434989 @434991 @PRCMUser @Sprint101
  Scenario: Verify user is able to update the Process ID of newly added handoff in FacilitySetting Configuration screen and Verify user is able to update the WorkflowTypeID of newly added handoff in FacilitySetting Configuration screen
    Given PRCM user is on "AHtoDecision Workflow Configuration" Screen
    Then user should be able to view +Add Handoff button on Handoff screen grid
    When user clicks on +Add Handoff button
    When user enters value in Workflow Name
    And user enters value in Workflow Description textbox: "DescriptionTest123"
    And user selects AHtoDecisionHandoff value from Worklist dropdown
    And user enters value in AH Module Code textbox: "CodeTest1"
    And user selects any value from Visible to Group dropdown for Add Handoff
    And user clicks on Save Changes button for Add Handoff
    Then user should be able to view the appropriate success message: "HandOff Inserted Successfully"
    When user clicks on Settings link from footer
    And user mouse hover on IT Tools link
    And user click on FacilitySetting Configuration link
    Then user having AHtoDecision Admin role is on "FacilitySetting Configuration" Screen
    When user selects "Code" option from Search dropdown
    And user enters any facility code in the Search textbox
    And user clicks on Search Data icon
    Then user should be able to view the row for searched facility in Locations grid
    When user clicks on View link of searched facility
    Then user should be able to view Facility Setting grid for searched facility
    When user selects "Setting Name" option from facility setting Search dropdown
    And user enters "YBFU_HISTORY_PROCESSID" in the Search textbox
    And user clicks on facility setting Search Data icon
    Then user should be able to view the row for searched Setting Name in Facility Settings grid
    When user clicks on Edit icon of searched facility setting
    Then user should be able to view Facility Setting Details pop up
    When user is able to login to sql server and connect to database
    And user runs the "Defaulthandoff_434989_SQL2" query to fetch ID
    Then user should be able to fetch the ProcessID
    When user enters comma followed by fetched ProcessID in Setting Value text area of Facility Settings Detail pop up
    And user clicks on Update Facility Setting button
    Then user should be able to view Facility Settings Details pop up as closed
    And user runs the "Defaulthandoff_434989_SQL3" query for default handoff
    Then user should be able to view added ProcessID in SQL result
    When user clicks on Settings link from footer
    And user mouse hover on IT Tools link
    And user click on FacilitySetting Configuration link
    Then user having AHtoDecision Admin role is on "FacilitySetting Configuration" Screen
    When user selects "Code" option from Search dropdown
    And user enters any facility code in the Search textbox
    And user clicks on Search Data icon
    Then user should be able to view the row for searched facility in Locations grid
    When user clicks on View link of searched facility
    Then user should be able to view Facility Setting grid for searched facility
    When user selects "Setting Name" option from facility setting Search dropdown
    And user enters "YBFU_HISTORY_WORKFLOWTYPEID" in the Search textbox
    And user clicks on facility setting Search Data icon
    Then user should be able to view the row for searched Setting Name in Facility Settings grid
    When user clicks on Edit icon of searched facility setting
    Then user should be able to view Facility Setting Details pop up
    And user runs the "Defaulthandoff_434991_SQL5" query to fetch ID
    Then user should be able to fetch the WorkflowTypeID
    When user enters comma followed by fetched WorkflowTypeID in Setting Value text area of Facility Settings Detail pop up
    And user clicks on Update Facility Setting button
    Then user should be able to view Facility Settings Details pop up as closed
    And user runs the "Defaulthandoff_434991_SQL6" query for default handoff
    Then user should be able to view added WorkflowTypeID in SQL result

  @434990 @PRCMUser @Sprint101
  Scenario: Verify user is able to view newly added handoff type in Handoff Type dropdown on R1 Decision screen
    Given PRCM user is on "AHtoDecision Workflow Configuration" Screen
    When user selects existing added handoff
    And user clicks on Billing & Follow-up link from footer
    And user hover on R1_Decision
    And user clicks on search sub menu
    Then user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
    And user selects "=" option from Operator dropdown
    When user is able to login to sql server and connect to database
    And user runs the "Defaulthandoff_434990_SQL4" query for default handoff
    And user enters the SQL result in Visit Number Search textbox
    And user clicks on Submit button
    Then user should be able to view R1 Decision Account page
    When user clicks on Handoff button
    And user clicks on Handoff Type dropdown
    Then user should be able to view the newly added handoff in Handoff Type dropdown