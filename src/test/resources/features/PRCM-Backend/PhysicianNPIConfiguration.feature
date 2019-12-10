@PhysicianNPIConfiguration
Feature: Verify Physician NPI Configuration in PRCM-BE

  Background: user is able to navigate to Facility group configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user hovers Payor and Plan Config
    And user clicks on PRCM Eligibility NPI Configuration

  @391444 @Sprint103 @PRCMUser
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

  @391445 @Sprint103 @PRCMUser
  Scenario: Verify that Breadcrumb text Physician NPI is available
    Given user is on PRCM Eligibility NPI Configuration page
    When user copies Physician's Name and NPI
    And user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    And user should be able to view "EDIT PHYSICIAN PAYOR LIST > " Physician's Name NPI message on edit pop up

  @391447 @Sprint103 @PRCMUser
  Scenario: Verify that Search disabled Payors
    Given user is on PRCM Eligibility NPI Configuration page
    When user clicks on to Edit link corresponding to the Physicians Name
    Then user should be able to view edit pop up
    When user copies payor of any disabled payor
    And user enters a search text Payor in Search Disabled Payors textbox
    Then user should be able to view the filtered list of payors in Total Payors Disabled
    And user should be able to view updated count in header Total Payors disabled: Count
