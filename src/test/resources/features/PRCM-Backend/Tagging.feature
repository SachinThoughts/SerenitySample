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
    Given User is on Account Tags Configuration screen 
    When User clicks on Add New Category button
    And User enters more than 100 alphabets in Category name
    And User enters upto 500 alphabets in Category description
    And User selects "R1Decision" value from the Application drop down
    And User clicks on Active switch
    And User clicks on Save button
    Then User should be able to view the validation message "Category Name should not more than 100 characters"