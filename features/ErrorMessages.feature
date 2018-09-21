Feature: Validate error Messages Scenarios 
##This scenarios are related to expected error messages

@PostNotOnJsonFormat 
Scenario Outline:
001 - Validate that when the values are not submitted on json format the error 415 will be returned 
	Given that the application is running 
	When a POST method adding ID 'new' SIDE 'left' VALUE 'abcd1234' is submitted in a not Json format 
	Then status code 415 is returned 
	Examples: 
	
@IdNotInitialized 
Scenario Outline:
002 - Validate that when the requested ID was not yet submitted the message "ID " + id + " not initialized." will be displayed 
	Given that the application is running 
	When a GET method for ID '1' is requested 
	Then the ErrorMessage returned in Json is: 'ID 1 not initialized.' 
	Examples: 
	
@DataNotInBase64 
Scenario Outline:
003 - Validate that when the value submitted is not on Base64 the message "Data in body not Base64 formatted." will be displayed 
	Given that the application is running 
	When a POST method adding ID 'new' SIDE 'left' VALUE ';]' is submitted not using base 64 format 
	Then the ErrorMessage returned in Json is: 'Data in body not Base64 formatted' 
	Examples: 
	
@GetNullRight 
Scenario Outline:
004 - Validate that when just Left side has values submitted the message Right side contains no value. will be displayed 
	Given that the application is running 
	And the GET method for ID new returns empty 
	Given that a POST method adding ID new SIDE left VALUE abcd1234 is submitted 
	When the GET method for ID new is submitted 
	Then the ErrorMessage returned in Json is: 'Right side contains no value.' 
	Examples: 
@GetNullLeft 
Scenario Outline:
005 - Validate that when just Right side has values submitted the message Left side contains no value. will be displayed 
	Given that the application is running 
	And the GET method for ID new returns empty 
	Given that a POST method adding ID new SIDE right VALUE abcd1234 is submitted 
	When the GET method for ID new is submitted 
	Then the ErrorMessage returned in Json is: 'Left side contains no value.' 
	Examples: 
	
@PostEmptyBody 
Scenario Outline:
006 - Validate that when a post request is submitted with an empty body the message "Value in request body cannot be empty." will be returned 
	Given that the application is running 
	And the GET method for ID new returns empty 
	When a POST method adding ID 'new' SIDE 'left' VALUE '' is submitted not using base 64 format 
	Then the ErrorMessage returned in Json is: 'Value in request body cannot be empty.' 
	Examples: 
	
@SideNotSupported 
Scenario Outline:
007 - Validate that when a post request is submitted with a differ value than left/right the message "This side is not supported, please use either 'left' or 'right'." will be returned 
	Given that the application is running 
	When a POST method adding ID 'new' SIDE 'up' VALUE 'abcd1234' is submitted 
	Then the ErrorMessage returned in Json is: 'This side is not supported, please use either 'left' or 'right'.' 
	Examples: 
