@FacilityGroupConfiguration
Feature: Verify Factility Group Configuration related scenarios in PRCM-BE

  Background: user is able to navigate to Facility group configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user mouse hovers on Settings-R1_Decision link
    And user clicks on Facility Group Configuration link

  @391159 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify that new column as IsPRCMenabled should be added in facility group table for invoice
    Given user is on Facility Group Configuration screen
    And user login to SQL server and connect to database
    When user runs the "Facility_Group_Configuration_391159_SQL1" query
    Then user should be able to view newly added column name as "IsPRCMenabled"

  @391160 @AHtoDecisionAdmin @Sprint103
  Scenario Outline: Verify that user should able to view Facility group configuration screen with controls at invoice level under Settings-R1_Decision
    Given user is on Facility Group Configuration screen
    Then user should be able to view header name as Facility Group Configuration
    Then user should be able to view column
      | Facility Group Name | Facilities |
    And user should be able to view Add New Facility Group button in top right and bottom right corner
    And user should be able to view the Edit Link button
    When user is on Facility Group Configuration screen
    Then user should be able to view data in facility group column <Facility Group Name>
    Then user should be able to view data in facilities <Facilities>
    Then user should be able to view header name as Facility Group Configuration
    And user login to SQL server and connect to database
    When user runs the facility group query"Facility_Group_Configuration_391159_SQL2"
    Then user should be able to view PRCM flag should be enabled having value as "1"

    Examples: 
      | Facilities | Facility Group Name      |
      | WPWI       | Ascension - Wheaton PRCM |
      | A400       | Intermountain PRCM       |

  @391161 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify Availability of checkbox  named as physican scope if user add any new facility group
    Given user is on Facility Group Configuration screen
    When user clicks on Add New Facility Group button
    Then user should be able to view new pop up window on clicking
    Then user should be able to view controls Facility
      | Facility Group Name | Search Facilities | All Facilities (Click Plus Button to Add facility) | Current Assigned facilities: | Physician InScope: | close | Save | Add |
    Then user should be able to view +Add button as disabled
    And user search facilities in the textbox present with label search facilities, example: "T"
    Then user should be able to view +Add button should get enabled

  @391162 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify Availability of checkbox named as physician scope if user edit any existing facility group
    Given user is on Facility Group Configuration screen
    When user is on Facility Group Configuration screen
    Then user should be able to view edit button for each facility group present in facility group column
    When user clicks on edit button
    Then user should be able to view edit window popup should be display
    And user should be able to view physician in scope checkbox
    And user can check or uncheck physician in scope checkbox

  @391163 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify that if PRCM checkbox is  in UI then it should be true in DB as well
    Given user is on Facility Group Configuration screen
    When user clicks on any edit button and clicks physician checkbox to enable it
    And user login to SQL server and connect to database
    When user runs the facility group query"Facility_Group_Configuration_391159_SQL2"
    Then user should be able to view PRCM flag should be enabled having value as "1"

  @391164 @AHtoDecisionAdmin @Sprint103
  Scenario Outline: Verify that if PRCM enable checkbox is enable for any facility group then user should able to view prcm view for that facility group in payer inventory tab
    Given user is on Facility Group Configuration screen
    When user clicks on edit button of the <Facility Group Name>
    And user clicks and enable the physician scope checkbox
    Then user should able to enable the checkbox for existing facility group
    And clicks on save button
    When user clicks on billing & follow-up from the footer
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link
    Then user should be able to view workflow distribution screen
    When user is on Workflow Distribution screen
    And user selects PRCM enabled "Ascension - Providence DC" facility group from Facility Group dropdown
    Then user should be able to view that facility group in the dropdown facility group list
    Then user should be able to view payer inventory filter for prcm enable facility group

    Examples: 
      | Facility Group Name       |
      | Ascension - Providence DC |

  @391424 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify the IsPRCMEnabled checkbox is automatically checked when Facility group has any PRCM enabled facility
    Given user is on Facility Group Configuration screen
    When user clicks on Add New Facility Group button
    Then user should be able to view new pop up window on clicking
    And user enters the Facility Group Name as "Test"
    And user selects facilities such that the Facility group has atleast one PRCM enabled Facility
    Then user should be able to view that the IsPRCMEnabled checkbox is automatically checked
    And clicks on save button
    When user clicks on billing & follow-up from the footer
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link
    Then user should be able to view workflow distribution screen
    When user is on Workflow Distribution screen
    And user selects PRCM enabled facility group from Facility Group dropdown
    Then user should be able to view the WFD screen should be displayed in PRCM view

  @391166 @AHtoDecisionAdmin @Sprint103
  Scenario Outline: Verify that if there is only one facility group is prcm enable and user selects All facility Group option then prcm view should be display
    Given user is on Facility Group Configuration screen
    When atleast one facility group <Facility Group Name> is enable in list of all facility group
    Then user should able to view atleast one facility group "Ascension - Wheaton PRCM" in facility group configuration screen
    When user clicks on billing & follow-up from the footer
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link
    Then user should be able to view workflow distribution screen
    When user is on Workflow Distribution screen
    And user selects PRCM enabled "Ascension - Wheaton PRCM" facility group from Facility Group dropdown
    And user should able to select "All Facilities" from facility dropdown
    When user clicks to select any value from facility dropdown
    Then user should able to view payer inventory filter for professional and technical accounts

    Examples: 
      | Facility Group Name      |
      | Ascension - Wheaton PRCM |

  @391425 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify the IsPRCMEnabled checkbox is remains unchecked when Facility group has NO PRCM enabled facility
    Given user is on Facility Group Configuration screen
    When user clicks on Add New Facility Group button
    Then user should be able to view new pop up window on clicking
    And user enters the Facility Group Name as "TestNoPrcmEnabled"
    And user selects Facilities "GRMC" such no Selected Facility is PRCM enabled
    Then user should be able to view that the IsPRCMEnabled checkbox is automatically unchecked
    And clicks on save button
    When user clicks on billing & follow-up from the footer
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link
    Then user should be able to view workflow distribution screen
    When user is on Workflow Distribution screen
    And user clicks on facility group dropdown list
    Then user should be able to view the WFD screen should be displayed in non-PRCM view

  @391165 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify if same facility is in non prcm facility group then user should not able to view payor inventory filter for that facility group
    Given user is on Facility Group Configuration screen
    When user clicks on Add New Facility Group button
    And user enters the Facility Group Name as "Test1WithEnabledFacility"
    And user enters facility as "PHDC"
    And clicks on save button
    When user clicks on Add New Facility Group button
    And user enters the Facility Group Name as "Test2WithNoEnabledFacility" having no PRCM enabled facilities
    And user enters facility as "GRMC" and "PHDC"
    And clicks on save button
    Then user should be able to view 2 facility group Test1 and Test2 having one facility "PHDC" common on both
    When user is on Facility Group Configuration screen
    And user clicks on edit button for Test1 facility group
    And user enable prcm checkbox that is physician in scope checkbox
    Then user should be able to enable that check box
    And clicks on save button
    When user is on Facility Group Configuration screen
    And user clicks on edit button for Test2 facility group
    Then user  should be able to view Test2 facility group should be non prcm facility group
    And clicks on close button
    When user clicks on billing & follow-up from the footer
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link
    Then user should be able to view workflow distribution screen
    When user is on Workflow Distribution screen
    And user selects Test1 from facility group dropdown
    And user selects common "PHDC" facility from facility dropdown which is common in both Test1 and Test2
    Then user  should be able to view PRCM enable view
    And payer inventory filter should be display
    When user is on Workflow Distribution screen
    And user selects Test2 from facility group dropdown
     And user selects common "PHDC" facility from facility dropdown which is common in both Test1 and Test2
    Then user should be able to view R1D enable view
    And payer inventory filter for technical and professional should not be display
