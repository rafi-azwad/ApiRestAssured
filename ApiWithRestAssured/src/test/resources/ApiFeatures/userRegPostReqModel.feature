Feature: User registration

  @postApi

  Scenario Outline: post api check of page
    Given user has the api '<path>'
    When user hit '<name>' and '<job>'
    And call the api with body
    Then it will return created data

    Examples:
      | path |   name   |  job  |
      | user | morpheus | leader|