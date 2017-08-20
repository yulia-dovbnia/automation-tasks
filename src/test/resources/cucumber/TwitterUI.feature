Feature: Use twitter UI

  In order to check UI
  As owner
  Should be able to post tweets

  Scenario: Tweet posting
    Given I open main page
    Given I login with credentials
    Then Check that I am logined
    When I post text 'Test post'
    When I refresh page
    Then Text is present in latest post
    When I send get request and save response into Session
    Then Text is present in API get request

