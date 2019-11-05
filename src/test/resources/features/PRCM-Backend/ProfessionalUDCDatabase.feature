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

  @391398 @Sprint8
  Scenario: Verify the Skills for professional defectsubcategory
    Given user is able to login to sql server and connect to database
    When user runs the "UDC_391398_SQL5" query to fetch defect subcategory ID
    Then user should be able to view defectsubcategoryid
    When user runs the "UDC_391398_SQL6" query to fetch skills id
    Then user should be able to view Skillid for all Major payer for defectsubcategoryid
