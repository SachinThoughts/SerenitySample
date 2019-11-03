@ProfessionalUDC
Feature: Verify ProfessionalUDC related scenarios in PRCM
  This is to verify ProfessionalUDC related scenarios in PRCM application

  @391396 @Sprint8
  Scenario Outline: Verify new application Name R1DProfessional has been created
    Given user is able to login to sql server and connect to database
    When user runs the "UDC_391396_SQL3" query to fetch application name and application ID
    Then user should be able to view Application name <applicationName> and Application ID

    Examples: 
      | applicationName |
      | R1DProfessional |
