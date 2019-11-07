@DefectOverride
Feature: Verify Defect over ride

  Background: user is able to connect to SQL server for PRCM facility
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link

  @391266 @Sprint8 @PRCMUser
  Scenario Outline: Verify that Defect Workflow section should be display when searched by Invoice#
    Given user is able to login to sql server and connect to database
    When user runs the query <query1>
    Then user should be able to view some invoice id
    When user runs the query <query2>
    Then user should be able to view the PRCM Flag "ON"
    When user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "Like" operator
    And user enters invoice number fetched from database in invoice number textbox
    And user clicks on submit button
    Then user should be able to view the searched account
    When user moves the control on right side of the page and see the Defect Workflow section
    Then user should be able to view the Defect workflow section
    And user should be able to view the progress bar with following steps
      | CONFIRM | TRIAGE | ACTION |
    And user should be able to view the progesssbar selected as "CONFIRM"
    And user should be able to view the assigned defect sub category below progress bar

    Examples: 
      | query1                     | query2                     |
      | DefectOverride_391266_SQL1 | DefectOverride_391266_SQL2 |

  @391267 @Sprint8 @PRCMUser
  Scenario Outline: Verify Defect Override Functionality
    Given user is able to login to sql server and connect to database
    When user runs the query <query1>
    Then user should be able to view some invoice id
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "=" operator
    And user enters invoice number fetched from database in invoice number textbox
    And user clicks on submit button
    When user moves the control on right side of the page and see the Defect Workflow section
    And user selects No radio button to Override Subcategory
    And user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button
    And user refreshes a page
    Then user should be able to view changed sub category below current defect under Defect workflow section
    And user runs the with DefectType query <query2>
    Then user should be able to view all active defect Type
    And user runs with DefectTypeID the query <query3>
    Then user should be able to view All Defect Subcategory for that Selected Defect Type

    Examples: 
      | query1                     | query2                     | query3                     |
      | DefectOverride_391266_SQL1 | DefectOverride_391267_SQL3 | DefectOverride_391267_SQL4 |

  @391268 @Sprint8 @PRCMUser
  Scenario Outline: Verify Defect Classification section after overriding Defect
    Given user is able to login to sql server and connect to database
    When user runs the query <query1>
    Then user should be able to view some invoice id
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "=" operator
    And user enters invoice number fetched from database in invoice number textbox
    And user clicks on submit button
    And user selects No radio button to Override Subcategory
    And user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button
    And user refreshes a page
    When user moves to Defect Classification Section
    Then user should be able to view the updated defect category in Defect Classification section

    Examples: 
      | query1                     |
      | DefectOverride_391266_SQL1 |

  @391270 @Sprint8 @PRCMUser
  Scenario Outline: Verify that Sop Actions should be  loaded according to current Defect of an Invoice
    Given user is able to login to sql server and connect to database
    When user runs the query <query1>
    Then user should be able to view some invoice id
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "=" operator
    And user enters invoice number fetched from database in invoice number textbox
    And user clicks on submit button
    When user moves the control on right side of the page and see the Defect Workflow section
    And user selects No radio button to Override Subcategory
    And user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button
    And user refreshes a page
    Then user should be able to view changed sub category below current defect under Defect workflow section
    And user runs the with DefectType query <query2>
    Then user should be able to view all active defect Type
    And user runs with DefectTypID and DefectSubCategoryDesc the query <query3>
    And user fetches defect subCategory from data base
    When user clicks on Next button
    Then user should be able to view "Verify All Steps Taken" section open and placeholder Triage
    And user should be able to view below fields
      | Previous | Next |
    When user clicks on Previous button
    Then user should be able to view navigation to Override Defect Category section
    When user clicks on Next button
    Then user should be able to view the "Verify All Steps Taken" section
    And user runs the to fetch SOP actions of  isrequired is zero query <query4>
    Then user should be able to view SOP actions  having IsRequired=0
    And user should be able to view Required Sops actions in verify All steps taken
    And user clicks on Next button on TriagePage
    And user should be able to view optional "Step(s) Taken"
    When user runs the to fetch SOP actions of isrequired is one  <query5>
    Then user should be able to view  SOP actions having Isrequired=1
    And user should be able to view Required Sops actions in Step taken section

    Examples: 
      | query1                     | query2                     | query3                      | query4                     | query5                      |
      | DefectOverride_391266_SQL1 | DefectOverride_391267_SQL3 | DefectOverride_391267_SQL4A | DefectOverride_391270_SQL9 | DefectOverride_391270_SQL11 |
