Feature: Smoke Test Validation
  ##This suite will run before other test suites to make sure the application is running and also the GET and POST methods are working as expected
  
  
  @GetMethodSuccessToDiff
  Scenario Outline: 001 - Validate that when a GET method is submitted to /diff it is returning success code
    Given that the application is running
    When a GET method to /diff path is submitted 
    Then the return code is 200
    Examples:
    
  @GetMethodSuccess
  Scenario Outline: 002 - Validate that the GET method is returning 404 code
    Given that the application is running
    When a GET method for a new ID is submitted
    Then the return code is 404
    Examples: 

  @PostMethodSuccess
  Scenario Outline: 003 - Validate that when a POST method is returning 200 code for a correct data
    Given that the application is running
    When a POST method adding a new ID with SIDE left and VALUE abcd1234 is submitted
    Then the return code is 200
    Examples: