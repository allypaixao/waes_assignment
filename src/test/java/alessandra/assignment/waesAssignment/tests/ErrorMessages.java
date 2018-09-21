package alessandra.assignment.waesAssignment.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import alessandra.assignment.waesAssignment.pageobjects.ApiControlerGet;
import alessandra.assignment.waesAssignment.pageobjects.ApiControlerPost;
import alessandra.assignment.waesAssignment.support.ApplicationControler;
import alessandra.assignment.waesAssignment.support.RandomDataGenerator;
import alessandra.assignment.waesAssignment.support.SetData;
import alessandra.assignment.waesAssignment.support.TestClass;
import alessandra.assignment.waesAssignment.support.TestContext;
import alessandra.assignment.waesAssignment.support.factory.ContextFactory;
import alessandra.assignment.waesAssignment.support.listeners.NgReportListener;

@TestClass("ErrorMessages")
@Listeners(NgReportListener.class)
public class ErrorMessages {

	public ExtentTest report;

	@Test(testName = "001 - Validate that when the values are not submitted on json format the error 415 will be returned", description = "Will send a Post method to /diff method and validate 415 code returned")
	public void validatePostNotOnJsonFormatResponseCode() throws Exception {

		String testID;
		String side = "left";
		String body = "abcd1234";
		int responseCode;
		int expectedCode = 415;

		// Initializing pages and supports
		ApplicationControler cApp = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 001 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		cApp.validateApplicationIsOn();

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * When a POST method adding ID 'new' SIDE 'left' VALUE 'abcd1234' is submitted
		 * in a not Json format
		 **/
		responseCode = cPost.sendPostReturnStatusCode(testID, side, body);

		/** Then status code 415 is returned **/
		// Asserting that the code is as expected
		Assert.assertEquals(responseCode, expectedCode);
	}

	@Test(testName = "002 - Validate that when the requested ID was not yet submitted the message 'ID id not initialized.' will be returned as JSON", description = "Will send a GET method to /diff with a non added ID and validate the returned message to: ID id not initialized.")
	public void validateGetIdNotInitializedResponseMessage() throws Exception {

		String testID = "1";
		int expectedError = 404;
		String expectedMessage = "ID 1 not initialized.";
		String responseMessage;
		String expectedResponse;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		ApiControlerGet cGet = new ApiControlerGet();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();

		context.report.info("Running scenario 002 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		// get the expectedResponse as string
		expectedResponse = set.setTestExpectedDataError(expectedMessage, expectedError);

		/** When a GET method for ID '1' is requested **/
		responseMessage = cGet.sendGetMothodForJsonResponse(testID, expectedError);

		/** Then the message returned is "ID 1 not initialized." **/
		// Asserting that result is as expected
		Assert.assertEquals(responseMessage, expectedResponse);
	}

	@Test(testName = "003 - Validate that when the value submitted is not on Base64 the message \"Data in body not Base64 formatted.\" will be displayed", description = "Will send a GET method to /diff with a non base 64 body returned message: Data in body not Base64 formatted.")
	public void validatePostDataNotInBase64Response() throws Exception {

		String testID;
		String side = "left";
		int expectedError = 415;
		String expectedMessage = "Value in request body must be in JSON format.";
		String apiBody = "';]";
		String response;
		String expectedResponse;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 003 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		// get a new id to send on Get method
		testID = newData.getNewID();

		// get the expectedResponse as string
		expectedResponse = set.setTestExpectedDataError(expectedMessage, expectedError);

		/**
		 * When a POST method adding ID 'new' SIDE 'left' VALUE ';]' is submitted not
		 * using base 64 format
		 **/
		response = cPost.sendPostMothodForJsonResponse(testID, side, apiBody, expectedError);

		/** Then the message returned is "Data in body not Base64 formatted." **/
		// Asserting that result is as expected
		Assert.assertEquals(response, expectedResponse);

	}

	@Test(testName = "004 - Validate that when just Left side has values submitted the message Right side contains no value. will be displayed", description = "Will send a GET method to the id with just left side with elementand validate the reurned message: Right side contains no value.")
	public void validateGetNullRight() throws Exception {

		// declare test variables
		String testID;
		String side = "left";
		int expectedCode = 200;
		String apiBody = "\"abcd1234\"";
		String expectedDetail = "Right side contains no value.";
		String expectedType = "DIFFERENT_LENGTH";
		String responseBody;
		String expetectedResult;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();
		ApiControlerGet cGet = new ApiControlerGet();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 004 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTestExpectedData(expectedDetail, expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/** Given the GET method for the new ID returns empty **/
		// set the test URL to test
		cGet.sendGetMethodExpectingEmpty(testID);

		/**
		 * Given that a POST method adding ID new SIDE left VALUE abcd1234 is submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBody, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		Assert.assertEquals(responseBody, expetectedResult);
	}

	@Test(testName = "005 - Validate that when just Right side has values submitted the message Left side contains no value. will be displayed", description = "Will send a GET method to the id with just right side with value and validate the reurned message: Left side contains no value.")
	public void validateGetNullLeft() throws Exception {

		// declare test variables
		String testID;
		String side = "right";
		String apiBodySide = "\"abcd1234\"";
		String expectedDetail = "Left side contains no value.";
		String expectedType = "DIFFERENT_LENGTH";
		String expetectedResult;
		String responseBody;
		int expectedCode = 200;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();
		ApiControlerGet cGet = new ApiControlerGet();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 005 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTestExpectedData(expectedDetail, expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/** Given the GET method for the new ID returns empty **/
		// set the test URL to test
		cGet.sendGetMethodExpectingEmpty(testID);

		/**
		 * Given that a POST method adding ID new SIDE left VALUE abcd1234 is submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBodySide, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		Assert.assertEquals(responseBody, expetectedResult);

	}

	@Test(testName = "006 - Validate that when a post request is submitted with an empty body the message \"Value in request body cannot be empty.\" will be returned", description = "Will send a POST method to with an empty body and validate the returned message. Error code expected is 400. ")
	public void validatePostEmptyBody400message() throws Exception {

		String testID;
		String side = "right";
		int expectedError = 400;
		String expectedMessage = "Value in request body cannot be empty.";
		String apiBody = ""; // empty as test given
		String expetectedResult;
		String responseBody;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 006 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		// get a new id to send on Get method
		testID = newData.getNewID();
		responseBody = set.setTestExpectedDataError(expectedMessage, expectedError);

		/**
		 * When a POST method adding ID 'new' SIDE 'left' VALUE '' is submitted not
		 * using base 64 format
		 **/

		// get the expectedResponse as string
		expetectedResult = cPost.sendPostMothodForJsonResponse(testID, side, apiBody, expectedError);

		/**
		 * Then the ErrorMessage returned in Json is: 'Value in request body cannot be
		 * empty.'
		 **/
		// Asserting that result is as expected
		Assert.assertEquals(expetectedResult, responseBody);

	}

	@Test(testName = "007 - Validate that when a post request is submitted with a differ value than left/right the message 'This side is not supported, please use either 'left' or 'right'.' will be returned", description = "Will send a POST method with an side parameter as up and validate the returned message. Error code expected is 501. ")
	public void validatePostSideNotSupported501message() throws Exception {

		String testID;
		String side = "up";
		String expectedMessage = "This side is not supported, please use either 'left' or 'right'.";
		String apiBody = "\"abcd1234\"";
		String expectedResponse;
		String expectedResult;
		int expectedCode = 501;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();
		SetData set = new SetData();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 007 from Feature: ErrorMessages.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		expectedResponse = set.setTestExpectedDataError(expectedMessage, expectedCode);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * When a POST method adding ID 'new' SIDE 'up' VALUE 'abcd1234' is submitted
		 **/

		expectedResult = cPost.sendPostMothodForJsonResponse(testID, side, apiBody, expectedCode);

		/**
		 * Then the ErrorMessage returned in Json is: 'This side is not supported,
		 * please use either 'left' or 'right'.'
		 **/
		// Asserting that result is as expected
		Assert.assertEquals(expectedResult, expectedResponse);

	}

	// @AfterTest
	public void closeConections() {
		// TODO: add a method to close any open conection between tests;
	}

}
