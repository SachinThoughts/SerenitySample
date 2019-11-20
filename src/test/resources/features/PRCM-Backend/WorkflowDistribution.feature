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
    