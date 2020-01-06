@CallPayerQueue
Feature: Verify Call Payer Queue functionality

  Background: user is able to connect to SQL server for PRCM facility
    Given user is able to login to sql server and connect to database
    When user runs the "getAccountsForWriteOff6" query to fetch account for CPQ
    And user fetch the "RegistrationID" from database

  @427120 @Sprint103
  Scenario: Verify that the entry for write-off Response is created in Payer.Defect AccountAttribute and CrossSiteWorkQueueArchive
    When user runs the query "Call_Payer_Queue_427120_SQL11" to check if entry for write-off is created
    Then user verify that the new Defect Attribute has been created
    When user runs the query "Call_Payer_Queue_427120_SQL12" to check if entry for DefectAttributeType is created
    And user fetch the DefectAttributeTypeID
    And user runs the query "Call_Payer_Queue_427120_SQL13" to check if entry for write-off is created
    And user fetch the DefectAccountKey
    And user runs the query "Call_Payer_Queue_427120_SQL14" to check if entry for write-off is created in AhCrossSite_CrossSiteWorkQueueArchive
    Then user should be able to view the account in AhCrossSite_CrossSiteWorkQueueArchive table with reason Approval Request
    And user should be able to view new Defect Attribute created
