@CallPayorQueue
Feature: Verify Call Payor Queue functionality

  Background: user is able to navigate to R1 Configuration Setting Page
    Given user is on R1 Hub page
    When user clicks on setting link
    And user hovers IT Tools link
    And user clicks on R1 Configuration

  @427116 @Sprint102 @PRCMUser
  Scenario Outline: There should be configuration settings for Call payer queue Park,Skip and Checkout Limit
    Given user is on R1 Configuration Setting Page
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Park-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update "A2D-Park-Limit" Value as per configuration requirement
    And user enters the setting value <A2D-Park-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the Application setting list screen
    And user should be able to view the setting "A2D-Park-Limit" in acceretive configuration page
    And user should be able to view the updated value of setting <A2D-Park-Limit>
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Skip-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update "A2D-Skip-Limit" Value as per configuration requirement
    And user enters the setting value <A2D-Skip-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the updated value of setting <A2D-Skip-Limit>
    When user selects "Setting Name" from search dropdown
    And user enters "A2D-Checkout-Limit" in textbox
    And user clicks on search button
    And user clicks on edit button to update "A2D-Checkout-Limit" Value as per configuration requirement
    And user enters the setting value <A2D-Checkout-Limit> in setting value textbox
    And user clicks on Update Appsetting button
    Then user should be able to view the Application setting list screen
    And user should be able to view the setting "A2D-Checkout-Limit" in acceretive configuration page
    And user should be able to view the updated value of setting <A2D-Checkout-Limit>

    Examples: 
      | A2D-Park-Limit | A2D-Skip-Limit | A2D-Checkout-Limit |
      |             48 |             24 |                  4 |
