Feature: Practice Form

  Scenario: Open Practice Form
    Given User opens main page
    When User clicks on the side menu Forms
    And Choose Practice Form submenu
    Then Practice Form page is opened

  Scenario: Enter valid data
    Given User opens main page
    When User clicks on the side menu Forms
    * Choose Practice Form submenu
    * User fill out the First Name field with "Mary" and Last Name with "Bright"
    * Fill out the Email field with "mary_bright@test.test"
    * User select gender "Female"
    * User fill out the Mobile field with "1234567890"
    * User clicks on the Submit button
    Then User should see a Thanks for submitting the form popup
    And Entered data should be displayed

  Scenario Outline: Leave empty required fields
    Given User opens main page
    When User clicks on the side menu Forms
    * Choose Practice Form submenu
    * User fill out the First Name field with "<firstName>" and Last Name with "<lastName>"
    * Fill out the Email field with "<email>"
    * User select gender "<gender>"
    * User fill out the Mobile field with "<mobile>"
    * User clicks on the Submit button
    Then User should not see a Thanks for submitting the form popup
    Examples:
      | firstName | lastName | email                 | gender | mobile     |
      |           | Bright   | mary_bright@test.test | Female | 1234567890 |
#      | Mary      |          | mary_bright@test.test | Female | 1234567890 |
#      | Mary      | Bright   |                       | Female | 1234567890 |
#      | Mary      | Bright   | mary_bright@test.test |        | 1234567890 |
#      | Mary      | Bright   | mary_bright@test.test | Female |            |



