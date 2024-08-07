Feature: Test Automation API

  @api
  Scenario: Test Get List Data Normal
    Given prepare url valid for "GET_LIST_USER"
    And hit api get list user
    Then validation status code is equal 200
    Then validation response body get list user
    Then validation response json with JSONschema "get_list_users_normal.json"

  @api
  Scenario: Test create New User Normal
    Given prepare url valid for "CREATE_NEW_USER"
    And hit api post create new user
    Then validation status code is equal 201
    Then validation response body post create new user
    Then validation response json with JSONschema "post_create_user_normal.json"

  @api
  Scenario: Test create New User Wrong
    Given prepare url valid for "CREATE_NEW_USER"
    And hit api post create wrong new user
    Then validation status code is equal 201
    Then validation response body post create new user
    Then validation response json with JSONschema "post_create_user_normal.json"


