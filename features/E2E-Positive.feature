Feature: Validate End to End scenarios with return on Differ 
##End to end scenarios adding values and validating that the message is as expected

@EqualMessage
Scenario Outline: 001 - Validate that when equal values are added the return will be EQUAL
Given that the application is running
And a POST method adding a new ID with SIDE left and VALUE abcd1234 is submitted
And a POST method adding for the same ID with SIDE right and VALUE abcd1234 is submitted
When the GET method for the ID added is submitted
Then the Json returns EQUAL message
Examples:

@DiffLengthMessage
Scenario Outline: 002 - Validate that when values with different length are added the return will be DIFFERENT_LENGTH
Given that the application is running
And a POST method adding a new ID with SIDE left and VALUE abcd1234 is submitted
And a POST method adding for the same ID with SIDE right and VALUE ab12 is submitted
When the GET method for the ID added is submitted
Then the Json returns DIFFERENT_LENGTH message
Examples: 

@DiffCharsMessage
Scenario Outline: 003 - Validate that when values with different Chars are added the return will be DIFFERENT_CHARS
Given that the application is running
And a POST method adding a new ID with SIDE left and VALUE abcd1234 is submitted
And a POST method adding for the same ID with SIDE right and VALUE abrd1284 is submitted
When the GET method for the ID added is submitted
Then the Json returns DIFFERENT_CHARS message
Examples:

@DiffCharsAndLengthMessage
Scenario Outline: 004 - Validate that when values with different Chars and length are added the return will be DIFFERENT_LENGTH
Given that the application is running
And for a new ID the application returns empty
And a POST method adding a new ID with SIDE left and VALUE abcd1234 is submitted
And a POST method adding for the same ID with SIDE right and VALUE 123abc is submitted
When the GET method for the ID added is submitted
Then the Json returns DIFFERENT_CHARS message
Examples: