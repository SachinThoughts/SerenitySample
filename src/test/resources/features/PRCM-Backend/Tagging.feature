@Tagging
Feature: Verify Tagging related scenarios in PRCM_BE

  Background: User is able to navigate to Account Tags Configuration Screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And User clicks on Tag Configuration link

  @419675 @PRCMUser @Sprint102
  Scenario: Verify that user is able to navigate Tag category screen and verifies the UI.
    Given User is on Account Tags Configuration screen
    Then User should be able to view Account Tags Configuration as page title
    And User should able to view Search Category Name textbox
    And User should be able to view TAG CATEGORY tab as selected
    And User should be able to view TAG NAME tab
    And User should be able to view Search Categories label
    And User should be able to view +Add New Category button
    And User should be able to view Continue button
    And User should be able to view following Grid headers
      | Category Name        |
      | Category Description |
      | Application          |
      | Created By           |
      | Updated By           |
      | Active               |

  @419676 @PRCMUser @Sprint102
  Scenario: Verify that No Tag Category Found in blue Tab
    Given User is on Account Tags Configuration screen
    When user enters some random text in Search Category Name Textbox
    Then User should be able to view the validation message "No Tag Category Found" with blue background.

  @419678 @PRCMUser @Sprint102
  Scenario: Verify that "Category Name should not more than 100 characters" validation message display when user enters more than 100 characters in category Name.
    Given User is on Account Tags Configuration screen
    When User clicks on Add New Category button
    And User enters more than 100 alphabets in Category name
    And User enters upto 500 alphabets in Category description
    And User selects "R1Decision" value from the Application drop down
    And User clicks on Active switch
    And User clicks on Save button
    Then User should be able to view the validation message "Category Name should not more than 100 characters"

  @419679 @PRCMUser @Sprint102
  Scenario: Verify that "Category Description should not more than 500 characters" validation message display when user enters more than 100 characters in category Description.
    Given User is on Account Tags Configuration screen 
    When User clicks on Add New Category button
    And User enters upto 100 alphabets in Category name
    And User enters more than 500 alphabets in Category description
    And User selects "R1Decision" value from the Application drop down
    And User clicks on Active switch
    And User clicks on Save button
    Then User should be able to view the validation message "Category Description should not more than 500 characters"

  @419677 @PRCMUser @Sprint102
  Scenario Outline: Verify that user is able to create New Tag category
    Given User is on Account Tags Configuration screen
    When User clicks on Add New Category button
    And User enters upto 100 alphabets in Category Name <Category name>  textbox
    And User enters upto 500 alphabets in Tag Description <Tag Description> textbox
    And User selects "R1Decision" value from the Application drop down
    And User clicks on Active switch
    And User clicks on Save button and captures success message
    Then User should be able to view the Success message "Success! Category has been added successfully"
    When user login to SQL server and connect to "Accretive" database
    And user runs the tag category <query1> query to verify newly added tag category
    Then User should be able to view the newly added Tag Category in SQL result

    Examples:  
      | Category name  | Tag Description | query1              |
      | AutomationTest | TestDesciption  | Tagging_419677_SQL1 |

  @419680 @PRCMUser @Sprint102
  Scenario Outline: Verify that "Success! Category has been updated successfully" message display every time when user click on save button and changes are updated.
    Given User is on Account Tags Configuration screen
    When User clicks on Edit button of added category
    And User edits Category Name <Category name> in textbox
    And User edits Tag Description <Tag Description> in textbox
    And User clicks on Active switch slide bar for Edit
    And User clicks on Save button and captures success message
    Then User should be able to view the Success message "Success! Category has been updated successfully"
    When user login to SQL server and connect to "Accretive" database
    And user runs the tag category query to verify newly updated tag category <query1>
    Then User should be able to view the Updated added Tag Category in SQL result

    Examples: 
      | Category name      | Tag Description      | query1              |
      | AutomationTestEdit | EditedTestDesciption | Tagging_419677_SQL1 |

  @419682 @PRCMUser @Sprint103
  Scenario Outline: Verify that user is able to update tag name page
    Given User is on Account Tags Configuration screen
    When User clicks on Radio button of any category
    And User clicks on Continue button
    Then User should be able to navigate to TAG NAME tab
    And User should be able to view Selected Category Label
    And User should be able to view Selected Category Name label
    And User should be able to view following Grid headers
      | Tag Name        |
      | Tag Description |
      | Facilities      |
      | Created By      |
      | Updated By      |
      | Active          |
    When User clicks on Add New Tag button
    And User enters the value for Tag Name <Tag Name> in Enter Tag Name textbox
    And User enters the value for Tag Description <Tag Description> in Enter Tag Description textbox
    And user selects multiple Facilities <facility1> and <facility2> from Facilities drop down
    And User clicks on Save button and captures success message
    Then User should be able to view the Success message "Success! Tag Name has been added successfully"
    When user login to SQL server and connect to "Accretive" database
    And user runs query two the query to get newly added tag <query2>
    Then User should be able to view the newly added Tag in SQL result.

    Examples: 
      | Tag Name       | Tag Description   | query2              | facility1 | facility2 |
      | AutomationTest | TestTagDesciption | Tagging_419682_SQL1 | WPWI      | ABIL      |
