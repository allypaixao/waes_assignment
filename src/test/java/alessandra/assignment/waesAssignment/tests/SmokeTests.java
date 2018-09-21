package alessandra.assignment.waesAssignment.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import alessandra.assignment.waesAssignment.pageobjects.ApiControlerGet;
import alessandra.assignment.waesAssignment.pageobjects.ApiControlerPost;
import alessandra.assignment.waesAssignment.support.ApplicationControler;
import alessandra.assignment.waesAssignment.support.RandomDataGenerator;
import alessandra.assignment.waesAssignment.support.TestClass;
import alessandra.assignment.waesAssignment.support.TestContext;
import alessandra.assignment.waesAssignment.support.factory.ContextFactory;
import alessandra.assignment.waesAssignment.support.listeners.NgReportListener;

@TestClass("SmokeTests")
@Listeners(NgReportListener.class)
public class SmokeTests {

	@Test(priority = 0, testName = "001 - Validate that the GET method on /diff is returning 200 code", description = "Will send a GET method to /diff method and validate 200 code returned")
	public static void sendGetToBaseURLReturns200() throws Exception {

		int expectedCode = 200;
		int responseCode;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		ApiControlerGet cGet = new ApiControlerGet();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 001 from Feature: SmokeTests.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		/** When a GET method to /diff path is submitted **/
		responseCode = cGet.sendGetMethodToAPIPathReturnsCode();

		/** Then the return code is 200 **/
		// assert that the returned code is as expected
		Assert.assertEquals(responseCode, expectedCode);
	}

	@Test(priority = 0, testName = "002 - Validate that the GET method with new ID is returning 404 code", description = "Will send a GET method to /diff method and validate 404 code returned")
	public static void sendGetToNotAddedIDReturns404() throws Exception {

		String testID = "404";
		int expectedCode = 404;
		int responseCode;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		ApiControlerGet cGet = new ApiControlerGet();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 002 from Feature: SmokeTests.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		/** When the GET method for a new ID is submitted **/
		responseCode = cGet.sendGetMethodReturnsCode(testID);

		/** Then the return code is 404 **/
		// Asserting that the code is as expected
		Assert.assertEquals(responseCode, expectedCode);
	}

	@Test(priority = 0, testName = "003 - Validate that a POST method is returning 200 code for a correct data", description = "Will send a POST method with id, side and value, expecting a 200 code on response")
	public static void sendPostReturns200() throws Exception {
		String testID;
		String side = "left";
		String body = "\"abcd1234\"";
		int responseCode;
		int expectedCode = 200;

		// Initializing pages and supports
		ApplicationControler app = new ApplicationControler();
		RandomDataGenerator newData = new RandomDataGenerator();
		ApiControlerPost cPost = new ApiControlerPost();

		// Get the context from the factory
		TestContext context = ContextFactory.getContext();
		context.report.info("Running scenario 003 from Feature: SmokeTests.");

		/** Given that the application is running **/
		app.validateApplicationIsOn();

		// get a new id to send on Get method
		testID = newData.getNewID();

		/**
		 * When a POST method adding a new ID with SIDE left and VALUE abcd1234 is
		 * submitted
		 **/
		responseCode = cPost.sendPostReturnStatusCode(testID, side, body);

		/** Then the return code is 200 **/
		// Asserting that the code is as expected
		Assert.assertEquals(responseCode, expectedCode);
	}

}
