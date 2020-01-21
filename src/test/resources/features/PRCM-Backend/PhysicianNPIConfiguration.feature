@PhysicianNPIConfiguration
Feature: Verify Physician NPI Configuration in PRCM-BE

  Background: user is able to navigate to Facility group configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user hovers Payor and Plan Config
    And user clicks on PRCM Eligibility NPI Configuration

  @391444 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Load Edit config popup
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    And user should be able to view the section "Total Payors Disabled" with the payors disabled for the Physician
    And user should be able to view the section "Total Eligible Payors" with the payors chosen to configure
    And user should be able to view provide Add All Payors and Remove All Payors buttons
    And user should be able to view the Search Disabled Payors section
    And user should be able to view Search Eligible Payors section
    And user should be able to view the cancel and Save buttons

  @391445 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Breadcrumb text Physician NPI is available
    Given user is on PRCM Eligibility NPI Configuration page
    When user copies Physician's Name and NPI
    And user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    And user should be able to view "EDIT PHYSICIAN PAYOR LIST > " Physician's Name NPI message on edit pop up

  @391447 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Search disabled Payors
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user copies payor of any disabled payor
    And user enters a search text Payor in Search Disabled Payors textbox
    Then user should be able to view the filtered list of payors in Total Payors Disabled
    And user should be able to view updated count in header Total Payors disabled: Count

  @391448 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Search Eligible Payors
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user copies payor of any eligible payor
    And user enters a search text Payor in Search Eligible Payors textbox
    Then user should be able to view the filtered list of payors in Total Eligible Payors
    And user should be able to view the updated count in header Total Eligible Payors: Count

  @391449 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Select Payors to enable for physician
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user copies payor of any disabled payor
    And user enters a search text Payor in Search Disabled Payors textbox
    And the user clicks on '*' sign for a Payor record under Total Payors Disabled section
    Then user should be able to view the displayed payor name in Total Eligible Payors section
    And user should be able to view the removed Payor name from Total Payors Disabled section

  @391450 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Select Payors to disable for physician
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user copies payor of any eligible payor
    And user enters a search text Payor in Search Eligible Payors textbox
    And the user clicks on '+' sign for a Payor record under Total Eligible Payors section
    Then user should be able to view the display payor name in Total Payors Disabled section
    And user should be able to view the removed Payor name from Total Eligible Payors section

  @391451 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Enable all Payors
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user clicks on <<Add All Payors button
    Then user should be able to view the display all Payors in Total Payors Disabled section
    And user should be able to view removed all Payors from Total Eligible Payors section

  @391452 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that Disable all Payors
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user clicks on Remove All Payors>> button
    Then user should be able to view the display all Payors in Total Eligible Payors section
    And user should be able to view removed all Payors from Total Disabled Payors section

  @391453 @Sprint103 @PRCMUser
  Scenario: Verify that User is able to save the physician configuration when physician is disabled
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user login to SQL Server and connect to facility database
    When user runs the "Facility_Group_Configuration_391453_SQL2" query for Physician NPI
    And user view existing entry in physician in EligibilityNPIDisabled column
    And user copies payor of any eligible payor
    And user enters a search text Payor in Search Eligible Payors textbox
    And user clicks on '+' button to disabled some of the payer
    And user gets count of the Total Payors Disabled
    And user clicks on save button
    Then user should be able to view the success message "Physician configuration is saved successfully."
    And user is able to see the number of payer which are disabled in total disabled payer column
    When user runs the "Facility_Group_Configuration_391453_SQL2" query for Physician NPI
    Then user should be able to view the entry in physician in EligibilityNPIDisabled column

  @391456 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify the PRCM NPI Configuration screen is opened then application should display list of Physicians with data
    Given user is on PRCM Eligibility NPI Configuration page
    Then physician list should be displayed with the columns
      | Physician's Name | NPI | Facility Physician ID | Total Payors Disabled |
    And user should be able to view Edit Links
    And user should be able to view pagination should be displayed
    And user should be able to view header and Footer should be displayed like
      | Total Physicians: | Displaying Page: |
    And user should be able to view page Header "FACILITY PHYSICIANS" should be displayed
    And user should be able to view the physicians records should be sorted based on Total Payors Disabled desc

  @391458 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify the Search Physician functionality
    Given user is on PRCM Eligibility NPI Configuration page
    Then user should be able to view the title Physician Search is displayed.
    Then physician list should be displayed with the columns
      | Physician's Name | NPI | Facility Physician ID | Total Payors Disabled |
    When user clicks on search text field on  PRCM NPI configuration page
    Then user search on the basis of Physician's and user should be able to search successfully on  PRCM NPI configuration page
      | XIONG-HANG, PLA XOUA | 1235493214 | 63974 |
    Then user should be able to view header and Footer should be displayed like
      | Total Physicians: | Displaying Page: |

  @391454 @Sprint103 @PRCMUser
  Scenario: Verify that User is able to save the physician configuration when physician is enabled
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user login to SQL Server and connect to facility database
    And user runs the "Facility_Group_Configuration_391454_SQL3" query for Physician NPI
    And user view existing entry in physician in EligibilityNPIDisabled column
    When user copies payor of any disabled payor
    And user enters a search text Payor in Search Disabled Payors textbox
    And user clicks on '*' button to enable some of the payer
    And user gets count of the Total Payors Disabled
    And user clicks on save button
    Then user should be able to view the success message "Physician configuration is saved successfully."
    And user is able to see the count of disabled payer should decreased by the number of payer that are increased
    And user runs the "Facility_Group_Configuration_391454_SQL3" query for Physician NPI
    Then user should be able to view the entry deleted from EligibilityNPIDisabled Table

  @391455 @Sprint103 @PRCMUser @NonDB
  Scenario: Verify that user is canceling the configuration then no changes should be done
    Given user is on PRCM Eligibility NPI Configuration page
    When user gets the count from total disabled payer column
    When user clicks on to Edit link corresponding to the Physicians Name
    And user copies payor of any disabled payor
    And user enters a search text Payor in Search Disabled Payors textbox
    And user clicks on '*' button to enable some of the payer
    And user clicks on cancel button
    Then user should be able to view that changes are not saved
