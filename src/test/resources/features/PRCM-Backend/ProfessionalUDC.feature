@ProfessionalUDC
Feature: Verify ProfessionalUDC related scenarios in PRCM
  This is to verify ProfessionalUDC related scenarios in PRCM application

  Background: user is able to navigate to universal defect configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration

  @391392 @PRCMUser
  Scenario: Verify that user having admin role is able to view UDC screen for Technical as well as Professional separately
    Given user is on UDC Admin configuration page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on facility group configuration screen
    Then user should be able to view one prcm enable facility group
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration
    Then user should able to view both application Tabs for technical as well as professional
    When user clicks on R1Decision Config Tab
    Then user should be able to view populated screen to configure at Technical account level
    When user clicks on PRCM R1Decision Config
    Then user should be able to view populated screen to configure at professional account invoice

  @391395 @NonPRCMUser
  Scenario: Verify that for Professional Config UDC user should have atleast one Facility that have PRCM access
    Given user is on UDC Admin configuration page
    Then user should be able to view R1 Decision UDC screen for technical only
    When user logout from the application
    And user login with the user "prcmBeUsername" who have atleast one facility which is prcm enable
    And user clicks on Login button
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration
    Then user should able to view both application Tabs for technical as well as professional

  @434958 @PRCMUser
  Scenario: Verify that user is able to add SOP Actions
    Given user having AHtoDecision Admin role is on Universal Defect Configuration  page
    When user select the radio button against any defect type
    And user clicks on the Continue button on defect type page
    And user select the radio button corresponding to a defect subcategory
    And user clicks on the Continue button on defect sub category page
    Then user should be able to view the selected Defect Type Defect SubCategory and default SOP Action in breadcrumb
    And user should be able to view Choose a SOP Action grid
    When user clicks on the Continue button on SOP type page
    And user clicks on the Add New SOP Actions button on SOP Actions screen
    And user enters all the mandatory fields
      | TestActionName        |
      | TestActionDescription |
      | AHtoDecision          |
      |                     2 |
      |                     2 |
      | Complete              |
    And user clicks on Save Changes SOP Actions button
    Then user should be able to view message as "SOP Action Saved Successfully."
    And Add SOP Action pop-up should disappear
    And user should be able to view added SOP Action on SOP Actions screen
