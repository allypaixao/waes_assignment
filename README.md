# waes_assignment
This project contains the files and code related to Waes QA assignment.

Author: Alessandra Paixao

#You will need the following project in order to run the application used to this functional tests:
 https://bitbucket.org/waesworks/differ-for-testers/overview;
 Follow Readme instructions to run;

#Notes: 
 - The test scenarios were writing in BDD(Cucumber Features) assuming that they were created in agreement with at least one BA and one developer(Los 3 amigos). Them can be find at: /features
 - Later the test scenarios were converted to testNG. TestNG was used instead of Cucumber to be able to run the tests in parallel without having to use any other plug-in.
 - A separated folder is related to unit test report. weaesworks-differ-for-testers-comments



#The project:

- PageObjec pattern was used, even though no pages exists. The classes can be found at the package com.alessandra.waes-assignment.pageobjects

- The support package supports: 
	- HTML Report with Extend Reports:
		- Automactly Report by TestNG;
		- Exceptions added to the report;
	- Test context to manage report;
		- Class support.factories.ContextFactory manages by threads ID.
		- Context Class makes paralell execution possible;
	- Config.properties 

##Project Code Notes:
 - This project was created using Java. It uses Maven to control dependencies and builds.
 - Dependencies:
    - TestNG 6.14.3 - test management and execution.
    - Extent Reports 3 - Generate reports.
    - Rest Assured for API Tests
	
##The Report:
- Can be found at /reports/report.html An example is added on the folder, each new run ir will be overided;


#Infos:
Maven 3.5.3, 
JDK 1.8.0_161

#TO RUN:
- Start application on project differ-for-testers by following previous instruction;
- Donwload the project on your local machine
- Open terminal console 
- From the project root:
	- run the command: mvn compile test
- After the run a failure will be displayed as one scenario is failling:
	- Scenario ErrorMessages#004 is failing as right side empty message is not returning as expected.
- The results can be verified at: /reports/report.html

