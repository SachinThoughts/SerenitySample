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
