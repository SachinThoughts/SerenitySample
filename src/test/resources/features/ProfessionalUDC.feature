@ProfessionalUDC
Feature: Verify Professional UDC configurations in PRCM-BE
  This is to verify Professional UDC configs in PRCM-BE

  Background: user is able to navigate to universal defect configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user hovers Settings-R1_Decision
    And user clicks on UDC Admin configuration

  @391392
  Scenario: Verify that user having admin role is able to view UDC screen for Technical as well as Professional separately
    Given user is on UDC Admin configuration page
    When user clicks on setting link
    And user hovers on Settings-R1_Decision and clicks on facility group configuration screen
    Then user should be able to view application name for PRCM
    And user should be able to view one prcm enable facility group
    When user is on UDC Admin configuration page
    Then user should able to view both application Tabs for technical as well as professional
      | Decision config | PRCM R1Decision Config |
    When user clicks on R1Decision Config Tab
    Then user should be able to view populated screen to configure at Technical account level
    When user clicks on PRCM R1Decision Config
    Then user should be able to view populated screen to configure at professional account invoice
