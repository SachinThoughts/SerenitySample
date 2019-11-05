@AccountDocument
Feature: Verify AccountDocument related scenarios in PRCM
  To check AccountDocument related scenarios in PRCM application

  Background: user is able to navigate to R1 Decision Account Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user login to SQL server and connect to database
    And user runs the "PRCM_Account_Document_391084_SQL1" query to fetch invoice number
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button

  @391084 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user is not able to upload a file of invalid format under Account Documents section
    Given user is on R1 Decision Account page
    When user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    Then user should be able to view the selected document type in Document type drop down
    When user enters document title "Title" in Document Title field
    Then user should be able to view the entered text in Document Title field
    When user selects file <invalid doctype> using ChooseFile Option
    Then user should be able to view the selected file path/name under File Name text box
    When user clicks on Upload Document button
    Then user should be able to view message "Upload Error! ." <invalid doctype> " is invalid, allowed extensions are: .doc, .docx, .xls, .xlsx, .pdf, .gif, .jpg, .png"

    Examples: 
      | invalid doctype |
      | bmp             |
      | txt             |
      | tiff            |
      | xlsm            |
      | docm            |

  @391085 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user is not able to upload a file of size > 20MB
    Given user is on R1 Decision Account page
    When user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    And user selects file <doctype> with size of more than 20 MB
    Then user should be able to view the selected file path/name under File Name text box
    When user clicks on Upload Document button
    Then user should be able to view the error message on uploading the document "Upload Error! Please upload a document of 20MB size or less."

    Examples: 
      | doctype |
      | PDF     |

  @434912 @Sprint8 @PRCMUser
  Scenario Outline: Verify that the error message is displayed when the user tries to upload a document without filling in mandatory details
    Given user is on R1 Decision Account page
    When user scrolls down till Account Documents section
    Then user should be able to view the message "Allowed extensions: .doc, .docx, .xls, .xlsx, .pdf, .gif, .jpg, .png Maximum size limit is 20 MB" below File Name field in maroon color text
    When user clicks on Upload Document button without entering value in any field
    Then user should be able to view the error message on uploading the document "Upload Error! Please select a Document Type"
    When user selects file <doctype> using ChooseFile Option
    And user clicks on Upload Document button
    Then user should be able to view the error message on uploading the document "Upload Error! Please select a Document Type"
    When user selects any document type from Document Type dropdown
    And user clicks on Upload Document button without entering Document Title
    Then user should be able to view the error message on uploading the document "Upload Error! Please enter a valid Document Title"
    When user click on cancel to clear the field under Document Section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    And user clicks on Upload Document button without selecting any file in the File Name field
    Then user should be able to view the error message on uploading the document "Upload Error! Please select a Document to Upload"

    Examples: 
      | doctype |
      | pdf     |

  @391086 @Sprint8 @PRCMUser
  Scenario: Verify user is getting validation message if no document attached with the account
    Given user is on R1 Decision Account page
    When user runs the "PRCM_Account_Document_391084_SQL1" query to fetch invoice number having no document
    And user enters the query result in Invoice Number search textbox having no document
    And user clicks on submit button
    When user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    Then user should be able to view the selected document type in Document type drop down
    When user checks the Show All Documents check box
    Then user should be able to view the Show All Documents checkbox checked
    And user should be able to view the validation message "No document uploaded!" below Upload Document button

  @434903 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user can successfully upload file of correct format and size after filling in all the mandatory fields
    Given user is on R1 Decision Account page
    When user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    When user selects file <doctype> using ChooseFile Option
    Then user should be able to view the selected file path/name under File Name text box
    When user clicks on Upload Document button
    Then user should get the information message "Document Uploaded Successfully." on screen
    When user checks the Show All Documents check box
    Then user should able to view the documents grid containing a list of all uploaded documents with their information
    When user clicks on the Document Title from the grid to open the corresponding document
    Then user should be able to view downloaded document on the system

    Examples: 
      | doctype |
      | pdf     |

  @434904 @Sprint8 @AHtoDecisionAdmin
  Scenario Outline: Verify  that user is able to upload document successfully with option "Associated to MRN"
    Given user is on R1 Decision Account page
    And user scrolls down till Account Documents section
    When user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    When user selects file <doctype> using ChooseFile Option
    And user checks Associated to MRN checkbox
    Then user should be able to view Associated to MRN checkbox checked
    When user clicks on Upload Document button
    Then user should get the information message "Document Uploaded Successfully." on screen
    When user clicks on Related Accounts button under Patient & Facility Info section
    Then user should be able to view the Related Accounts pop-up with list of all related accounts
    When user clicks on any account of same facility from Related account list
    Then user should be able to navigate to selected account
    When user go to Account Documents section
    And user checks the Show All Documents check box
    Then user should be able to view all the uploaded documents in list which were associated with MRN
    When user clicks on the Document Title from the grid to open the corresponding document
    Then user should be able to view downloaded document on the system

    Examples: 
      | doctype |
      | pdf     |

  @434906 @Sprint8 @AHtoDecisionAdmin
  Scenario Outline: Verify  that user is able to upload document successfully without selects "Associated to MRN" checkbox
    Given user is on R1 Decision Account page
    When user scrolls down till Account Documents section
    And user selects any document type from Document Type dropdown
    And user enters document title "Title" in Document Title field
    And user selects file <doctype> using ChooseFile Option
    And user clicks on Upload Document button
    Then user should get the information message "Document Uploaded Successfully." on screen
    When user clicks on Related Accounts button under Patient & Facility Info section
    Then user should be able to view the Related Accounts pop-up with list of all related accounts
    When user clicks on any account of same facility from Related account list
    Then user should be able to navigate to selected account
    When user go to Account Documents section
    And user checks the Show All Documents check box
    Then user should not be able to view the uploaded documents in the list which were uploaded in previous account

    Examples: 
      | doctype |
      | pdf     |
