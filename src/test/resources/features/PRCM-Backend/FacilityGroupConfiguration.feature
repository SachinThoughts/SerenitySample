Feature: Verify Factility Group Configuration related scenarios in PRCM-BE

  Background: user is able to navigate to Facility group configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user mouse hovers on Settings-R1_Decision link
    And user clicks on Facility Group Configuration link

  @391159 @AHtoDecisionAdmin
  Scenario: Verify that new column as IsPRCMenabled should be added in facility group table for invoice
    Given user is on Facility Group Configuration screen
    And user login to SQL server and connect to database
    When user runs the "Facility_Group_Configuration_391159_SQL1" query
    Then user should be able to view newly added column name as "IsPRCMenabled"

  @391160
  Scenario Outline: Verify that user should able to view Facility group configuration screen with controls at invoice level under Settings-R1_Decision
    Given user is on Facility Group Configuration screen
    Then user should be able to view header name as Facility Group Configuration
    Then user should be able to view column
      | Facility Group Name | Facilities | Add New Facility Group | Edit |
    When user is on Facility Group Configuration screen
    Then user should be able to view data in facility group column <Facility Group Name>
    Then user should be able to view data in facilities <Facilities>
    Then user should be able to view header name as Facility Group Configuration
    And user login to SQL server and connect to database
    When user runs the "Facility_Group_Configuration_391159_SQL1" query
    Then user should be able to view PRCM flag should be enabled having value as "1"

    Examples: 
      | Facilities | Facility Group Name      |
      | wpwi       | Ascension - Wheaton PRCM |
      | A400       | Intermountain PRCM       |
