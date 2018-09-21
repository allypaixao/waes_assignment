package alessandra.assignment.waesAssignment.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import alessandra.assignment.waesAssignment.pageobjects.ApiControlerGet;
import alessandra.assignment.waesAssignment.pageobjects.ApiControlerPost;
import alessandra.assignment.waesAssignment.support.ApplicationControler;
import alessandra.assignment.waesAssignment.support.RandomDataGenerator;
import alessandra.assignment.waesAssignment.support.SetData;
import alessandra.assignment.waesAssignment.support.TestClass;
import alessandra.assignment.waesAssignment.support.TestContext;
import alessandra.assignment.waesAssignment.support.factory.ContextFactory;
import alessandra.assignment.waesAssignment.support.listeners.NgReportListener;

@TestClass("EndToEndTest")
@Listeners(NgReportListener.class)
public class EndToEndTest {

	@Test(testName = "001 - Validate that when equal values are added the return will be EQUAL", description = "Will send two post methods with left and right values anda validate the json returned to EQUAL")
	public static void validateEqualReturnForLeftRigthPost() throws Exception {

		// declare test variables
		String testID;
		String side = "left";
		String sideTwo = "right";
		int expectedCode = 200;
		String apiBody = "\"abcd1234\"";
		String apiBodyTwo = "\"abcd1234\"";
		String expectedType = "EQUAL";
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
		context.report.info("Running scenario 001 from Feature: EndToEnd.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTypeExpectedData(expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBody, expectedCode);

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, sideTwo, apiBodyTwo, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		/** Then the Json returns EQUAL message **/

		Assert.assertEquals(responseBody, expetectedResult, "\nReponse Body: " + responseBody);
	}

	@Test(testName = "002 - Validate that when values with different length are added the return will be DIFFERENT_LENGTH", description = "Will send two post methods with left and right values anda validate the json returned is DIFFERENT_LENGTH")
	public static void validateDiffLengthReturnForLeftRigthPost() throws Exception {

		// declare test variables
		String testID;
		String side = "left";
		String sideTwo = "right";
		int expectedCode = 200;
		String apiBody = "\"abcd1234\"";
		String apiBodyTwo = "\"ab12\"";
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
		context.report.info("Running scenario 002 from Feature: EndToEnd.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTypeExpectedData(expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBody, expectedCode);

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, sideTwo, apiBodyTwo, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		/** Then the Json returns EQUAL message **/

		Assert.assertEquals(responseBody, expetectedResult, "\nReponse Body: " + responseBody);
	}

	@Test(testName = "003 - Validate that when values with different Chars are added the return will be DIFFERENT_CHARS", description = "Will send two post methods with left and right values anda validate the json returned to DIFFERENT_CHARS")
	public static void validateDiffCharsReturnForLeftRigthPost() throws Exception {

		// declare test variables
		String testID;
		String side = "left";
		String sideTwo = "right";
		int expectedCode = 200;
		String apiBody = "\"abcd1234\"";
		String apiBodyTwo = "\"abrd1284\"";
		String expectedType = "DIFFERENT_CHARS";
		String expectedDetail = "Values are different on char(s) [2] [6].";
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
		context.report.info("Running scenario 003 from Feature: EndToEnd.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTestExpectedData(expectedDetail, expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBody, expectedCode);

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, sideTwo, apiBodyTwo, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		/** Then the Json returns EQUAL message **/

		Assert.assertEquals(responseBody, expetectedResult, "\nReponse Body: " + responseBody);
	}

	@Test(testName = "004 - Validate that when values with different Chars and length are added the return will be DIFFERENT_LENGTH", description = "Will send two post methods with left and right values anda validate the json returned to DIFFERENT_LENGTH")
	public static void validateDiffCharsAndLengthReturnForLeftRigthPost() throws Exception {

		// declare test variables
		String testID;
		String side = "left";
		String sideTwo = "right";
		int expectedCode = 200;
		String apiBody = "\"abcd1234\"";
		String apiBodyTwo = "\"te34\"";
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
		context.report.info("Running scenario 004 from Feature: EndToEnd.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();
		expetectedResult = set.setTypeExpectedData(expectedType);

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, side, apiBody, expectedCode);

		/**
		 * And a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		// will build a new method with post
		cPost.sendPostMethodWithIdAndSide(testID, sideTwo, apiBodyTwo, expectedCode);

		/** When the GET method for ID new is submitted **/
		// set the test URL to test
		responseBody = cGet.sendGetMothodForJsonResponse(testID, expectedCode);

		/** Then the Json returns EQUAL message **/

		Assert.assertEquals(responseBody, expetectedResult, "\nReponse Body: " + responseBody);
	}

}
