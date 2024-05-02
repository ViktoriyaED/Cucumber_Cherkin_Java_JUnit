Feature: Main Page

  Scenario: Open Main Page
    When User opens main page
    Then Main Page is opened

  Scenario: Open Elements page
    When User opens main page
    And User clicks on the side menu Elements page
    Then Elements Page is opened
