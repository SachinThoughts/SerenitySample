@BSODayNight
Feature: Verify BSODayNight related scenarios in PRCM
  To check BSODayNight related scenarios in PRCM application

  @391279 @Sprint103
  Scenario: Verify Tables in DB after taking BSO-Day/Night Followup Handoff
    Given user is able to login to sql server and connect to database
    When user runs the "BSO-Day/Night_Followup_391279_SQL2" query to fetch the handoffed Invoice ID
    Then user should be able to view invoiceid as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_A" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_B" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_C" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_D" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_E" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_F" query to fetch result Set
    Then user should be able to view hand off action as sql result
    When user runs the "BSO-Day/Night_Followup_391279_SQL4_G" query to fetch result Set
    Then user should be able to view hand off action as sql result
