Feature: Send requests

  In order to check Ui
  As owner
  Should be able to send requests

  Scenario: Get request
    When I send get request and save response into Session
    Then status code should be: 200
    Then Field 'text' is not empty in all records
    Then Field 'retweet_count' equals '0' in all records

  Scenario: Delete request
    When I send new post request and save response into Session: 'Test'
    When I send get request and save response into Session
    Then There is posted record in response
    When I send destroy request and save response into Session
    Then status code should be: 200
    Then I send get request and save response into Session
    Then There is no posted record in response

  Scenario: Post request
    When I send new post request and save response into Session: 'Test'
    Then status code should be: 200
    When I send get request and save response into Session
    Then status code should be: 200
    Then Field 'created_at' is correct
    Then Field 'retweet_count' is '0'
    Then Field 'text' is 'Test'


  Scenario: Post request duplication
    When I send new post request and save response into Session: 'Test'
    Then status code should be: 200
    When I send old post request and save response into Session: 'Test'
    Then status code should be: 403