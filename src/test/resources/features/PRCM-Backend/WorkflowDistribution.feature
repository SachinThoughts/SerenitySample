@WorkflowDistribution
Feature: Verify Workflow Distribution related scenarios in PRCM-BE

  Background: User is able to navigate to Workflow Distribution page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link

  @441301 @ARSupervisor @Sprint102
  Scenario: Verify the Filters for the PRCM enabled Facility group for Facility Inventory
    Given user is on Workflow Distribution screen
    #  And user selects PRCM enabled facility group from Facility Group dropdown
    And user clicks on Facility Inventory tab
    Then user should be able to view the following filters under Account Inventory under Filters section
      | (All)        |
      | Payer        |
      | Technical    |
      | Professional |
      | Patient      |
      | Technical    |
      | Professional |
    And user should be able to view Hide link
    And user should be able to view Professional Radio button selected by default under Payer section
    When User clicks on Hide link
    Then user should be able to view Show link label
    And user should not be able to view the following filters under Account Inventory under Filters section 
      | (All)        |
      | Payer        |
      | Technical    |
      | Professional |
      | Patient      |
      | Technical    |
      | Professional |
    When User clicks on Show link
    Then user should be able to view the following filters under Account Inventory under Filters section
      | (All)        |
      | Payer        |
      | Technical    |
      | Professional |
      | Patient      |
      | Technical    |
      | Professional |
    And user should be able to view Hide link
    And user should be able to view Professional Radio button selected by default under Payer section    
