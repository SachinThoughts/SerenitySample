@WorkflowDistribution
Feature: Verify Workflow Distribution related scenarios in PRCM-BE

  Background: User is able to navigate to Workflow Distribution page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link

  @442326 @PRCMUser @Sprint102
  Scenario: Verify the Facility inventory tab for PRCM enabled Facility group  when  All is selected
    Given user is on Workflow Distribution screen
    And user clicks on Facility Inventory Tab
    And User clicks on All radio button under the Account Inventory Filter in Filter Section
    Then user should be able to view the grid
      | Facility Group | Unassigned Accounts | Unassigned Balance | Assigned Accounts | Assigned Balances | Account Age | Teams | Reps | Burn Rate | Days Inventory |
    When user clicks on any Facility Group from the grid
    Then user should be able to view the below sections
      | Unassigned Accounts | Assigned |
    And user should be able to view following sub sections under Unassigned (Due for Work ) section
      | Facility Name | Number of Accounts | Account Age | Balance | Teams | Reps | Burn Rate | Days Inventory |
    And user should be able to view following sub sections under Assigned (Due for Work ) section
      | Facility Name | Number of Accounts | Account Age | Balance | Teams | Reps | Burn Rate | Days Inventory |

  @442332 @PRCMUser @Sprint102
  Scenario: Verify the Facility inventory tab for PRCM enabled Facility group  when  Payer is selected 
    Given user is on Workflow Distribution screen
    And user clicks on Facility Inventory Tab
    And User clicks on Payer radio button under the Account Inventory Filter in Filter Section
    Then user should be able to view the grid
      | Facility Group | Unassigned Accounts | Unassigned Balance | Assigned Accounts | Assigned Balances | Account Age | Teams | Reps | Burn Rate | Days Inventory |
    When user clicks on any Facility Group from the grid
    Then user should be able to view the below sections
      | Unassigned Accounts | Assigned |
    And user should be able to view following sub sections under Unassigned (Due for Work ) section
      | Facility Name | Number of Accounts | Account Age | Balance | Teams | Reps | Burn Rate | Days Inventory |
    And user should be able to view following sub sections under Assigned (Due for Work ) section
      | Facility Name | Number of Accounts | Account Age | Balance | Teams | Reps | Burn Rate | Days Inventory |
