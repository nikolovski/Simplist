Feature: Login
  As a user
  In order to use Simplist
  I have to login to the system

  Scenario Outline: User entered the correct username/password
    Given I am on the login page
    When I type in username <username>
    And I type in password <password>
    Then I should be logged in
    Examples:
      | username | password |
      | test     | test     |
      | test123  | test123  |

  Scenario Outline: User entered the incorrect username/password
    Given I am on the login page
    When I type in username <username>
    And I type in password <password>
    Then I shouldn't be able logged in
    Examples:
      | username | password |
      | test     | test     |
      | test123  | test123  |



