@WorkflowDistribution
Feature: Verify Workflow Distribution related scenarios in PRCM-BE

  Background: User is able to navigate to Workflow Distribution page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision
    And user clicks on Workflow Distribution link

  @391287 @ARSupervisor @Sprint102
  Scenario: Verify AR Supervisor is able to access all the  tabs  under "Workflow Distribution" screen
    Given user is on Workflow Distribution screen
    Then user should be able view following tabs under Workflow Distribution
      | Payer Inventory | Patient Inventory | Facility Inventory | Teams | Reps |
    And user should able to view "Payer Inventory" opened by default
    When user clicks on Patient Inventory Tab
    Then user should be able to view "Patient Inventory" tab
    When user clicks on Facility Inventory Tab
    Then user should be able to view "Facility Inventory" tab
    When user clicks on Team Tab
    Then user should be able to view "Teams" tab
    When user clicks on Reps tab
    Then user should be able to view "Reps" tab

  @391288 @ARSupervisor @Sprint102
  Scenario: Verify  Notes Section in WFD screen beside  "Filter Result By" sections  in all Tabs under "Workflow Distribution" screen.
    Given user is on Workflow Distribution screen
    Then user should be able to view Notes section between  Filter Result by section and Bread Crumb
    And user should able to view Note message as "If any facility group reassignment, team reconfiguration or updates are required, please coordinate with R1 Service Desk (servicedesk@R1RCM.COM)"
    When user clicks on Facility Inventory Tab
    Then user should be able to view Notes section between  Filter Result by section and Bread Crumb
    And user should able to view Note message as "If any facility group reassignment, team reconfiguration or updates are required, please coordinate with R1 Service Desk (servicedesk@R1RCM.COM)"
    When user clicks on Team Tab
    Then user should be able to view Notes section between  Filter Result by section and Bread Crumb
    And user should able to view Note message as "If any facility group reassignment, team reconfiguration or updates are required, please coordinate with R1 Service Desk (servicedesk@R1RCM.COM)"
    When user clicks on Reps tab
    Then user should be able to view Notes section between  Filter Result by section and Bread Crumb
    And user should able to view Note message as "If any facility group reassignment, team reconfiguration or updates are required, please coordinate with R1 Service Desk (servicedesk@R1RCM.COM)"

  @442326 @PRCMUser @Sprint102
  Scenario: Verify the Facility inventory tab for PRCM enabled Facility group when All is selected
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
  Scenario: Verify the Facility inventory tab for PRCM enabled Facility group when Payer is selected
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

  @442333 @PRCMUser @Sprint102
  Scenario: Verify the Facility inventory tab for PRCM enabled Facility group when Patient is selected
    Given user is on Workflow Distribution screen
    And user clicks on Facility Inventory Tab
    And User clicks on Patient radio button under the Account Inventory Filter in Filter Section
    Then user should be able to view the grid
      | Facility Group | Unassigned Accounts | Unassigned Balance | Assigned Accounts | Assigned Balances | Account Age | Teams | Reps | Burn Rate | Days Inventory |
    And user should not be able to view any data in side the grid

  @427405 @ARSupervisor @Sprint102
  Scenario: Verify AR Supervisor is able to access all the  tabs  under "Workflow Distribution" screen
    Given user is on Workflow Distribution screen
    Then user should be able view following tabs under Workflow Distribution
      | Payer Inventory | Patient Inventory | Facility Inventory | Teams | Reps |
    And user should able to view "Payer Inventory" opened by default
    Then user should be able to view the grid
      | Payer Name | Unassigned Accounts | Unassigned Balance | Assigned Accounts | Assigned Balances | Account Age | Teams | Reps | Burn Rate | Days Inventory |
    And user should be able to view All Radio button, Professional Radio button, Technical Radio button under Payer Inventory section under Filter section
    When user clicks on Patient Inventory Tab
    Then user should be able to view "Patient Inventory" tab
    When user clicks on Facility Inventory Tab
    Then user should be able to view "Facility Inventory" tab
    When user clicks on Team Tab
    Then user should be able to view "Teams" tab
    When user clicks on Reps tab
    Then user should be able to view "Reps" tab