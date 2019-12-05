@FinancialInformation
Feature: This feature is to verify the financial Information functionality

  Background: User is able to navigate to R1D Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391020 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user is able to see "Financial Information" section
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view following fields
      | Total Balance | Insurance Balance | Patient Balance | Insurance Balance | Unbilled Balance | Total Charges | Expected Payment | Insurance Payments | Patient Payments | Adjustments |

    Examples: 
      | queryName1                                |
      | Financial_Information_Section_391021_SQL1 |

  @391030 @Sprint8 @PRCMUser
  Scenario Outline: Verify the Drilldown detail  for Adjustment >0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName5>
    Then user should be able to fetch Invoice Number
    When user enters fetched Invoice Number in the Invoice Number textbox
    And user clicks on Submit button on R1D page
    And user scrolls down till Financial Information Section
    And User Clicks on drill down icon of Adjustment drilldown
    Then User should be able to view following fields:
      | Payment Code        |
      | Payment Description |
      | PayorPlanCode       |
      | PayorPlanName       |
      | Date Posted         |
      | DateOfTransaction   |
      | TypeOfTransaction   |
      | Amount              |
      | GLCode              |
    When user runs Financial_Information_Section_SQL9 <queryName9>
    Then User should be able to view same data in drilldown section of Adjustment as SQL result

    Examples: 
      | queryName5                                | queryName9                                |
      | Financial_Information_Section_391026_SQL5 | Financial_Information_Section_391030_SQL9 |

  @423934 @Sprint8 @PRCMUser
  Scenario Outline: Verify the amount for Total Balance column
    Given user is able to login to sql server and connect to database
    When user runs Financial_Information_Section_SQL3 <queryname3>
    Then user should be able to fetch Invoice Number and Total Balance
    When user enters fetched Invoice Number in the Invoice Number textbox
    And user clicks on Submit button on R1D page
    And user scrolls down till Financial Information Section
    And user moves to Total Balance column
    Then User should be able to view the same amount of Total Balance as SQL result

    Examples: 
      | queryname3                                |
      | Financial_Information_Section_423934_SQL3 |

  @391026 @Sprint8 @PRCMUser
  Scenario Outline: Verify the amount for Adjustment column
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName5>
    And user fetch the InvoiceNumber and "Adjustments" from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then user should be able to view the same amount in Adjustment column as SQL result

    Examples:   
      | queryName5                                |
      | Financial_Information_Section_391026_SQL5 |

  @391027 @Sprint101 @PRCMUser
  Scenario Outline: Verify the Drilldown section of total charges>0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryname1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    And user Clicks on drill down icon of total charges
    Then User should be able to view following total charges fields:
      | Service Date        |
      | Charge Posting Date |
      | UB-04 Rev Code      |
      | Revenue Center Code |
      | CPT code            |
      | Number of Units     |
      | Total Charge        |
      | ChargeItemCode      |
      | Charge Description  |
    And user executes the query to fetch total charges details <queryname6>
    Then user should be able to view same data in drilldown section of Total Charges as SQL result

    Examples: 
      | queryname1                                | queryname6                                |
      | Financial_Information_Section_391021_SQL1 | Financial_Information_Section_391027_SQL6 |

  @391021 @Sprint101 @PRCMUser
  Scenario Outline: Verify the Expected payment column
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber and "TotalCharges" from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view some dollar value in Total Charges column

    Examples: 
      | queryName1                                |
      | Financial_Information_Section_391021_SQL1 |

  @391022 @Sprint101 @PRCMUser
  Scenario Outline: Verify the Expected payment column
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view "$0.00" as value of Expected Payment under Financial Information section

    Examples: 
      | queryName1                                |
      | Financial_Information_Section_391021_SQL1 |

  @426781 @Sprint101 @PRCMUser
  Scenario Outline: Verify  the amount for Patient Payments
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName17>
    And user fetch the InvoiceNumber and "PatientPayment" from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view some dollar value in Patient Payment column 

    Examples:   
      | queryName17                                |
      | Financial_Information_Section_426781_SQL17 |

  @424877 @Sprint102 @PRCMUser
  Scenario Outline: Verify the message received when amount for  Insurance Payment=0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName11>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then user should able to view "N/A" against Insurance Payments under Financial Information section

    Examples:  
      | queryName11                                |
      | Financial_Information_Section_424877_SQL11 |

  @424892 @Sprint102 @PRCMUser
  Scenario Outline: Verify the message received when amount for  Patient Payment Amount=0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName13>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then user should be able to view "N/A" against Patient Payment under Financial Information section

    Examples:  
      | queryName13                                |
      | Financial_Information_Section_424877_SQL13 |

  @424887 @Sprint102 @PRCMUser
  Scenario Outline: Verify the message received when amount for  Adjustment Amount=0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName13>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    And User clicks on Adjustment Amount drill down
    Then User should be able to view the message "No adjustments have been posted for this account" under Adjustment amount column 

    Examples:  
      | queryName13                                |
      | Financial_Information_Section_424877_SQL13 |

  @425643 @Sprint102 @PRCMUser
  Scenario Outline: Verify the drilldown detail for Insurance Payments column>0
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName2>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    And user clicks on drill down icon of Insurance Payments
    Then User should be able to view following fields under insurance payment section:
      | Payment Code        |
      | Payment Description |
      | PayorPlanCode       |
      | PayorPlanName       |
      | Date Posted         |
      | DateOfTransaction   |
      | TypeOfTransaction   |
      | Amount              |
      | GLCode              |
    When user is able to login to sql server and connect to database
    And user executes the query to fetch insurance payment details <queryName7>
    Then user should be able to view same data in drilldown section of Insurance Payments as SQL result

    Examples: 
      | queryName2                                | queryName7                                |
      | Financial_Information_Section_391023_SQL2 | Financial_Information_Section_391028_SQL7 |

  @424015 @Sprint102 @PRCMUser
  Scenario Outline: Verify the amount for Unbilled Balance column
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName15>
    And user fetch the InvoiceNumber and "UnbilledBalance" from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then user should be able to view the same amount in Unbilled Balance column as SQL result 

    Examples: 
      | queryName15                                |
      | Financial_Information_Section_424015_SQL15 |
