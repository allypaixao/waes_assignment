package alessandra.assignment.waesAssignment.pageobjects;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import alessandra.assignment.waesAssignment.support.Environment;
import alessandra.assignment.waesAssignment.support.PageObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiControlerGet extends PageObject {

	JSONObject expetectedResult = new JSONObject();
	RequestSpecBuilder getBuilder = new RequestSpecBuilder();
	RequestSpecification requestGetSpec;
	URL getUrl;

	public void sendGetMethodExpectingEmpty(String testID) throws MalformedURLException {

		context.report.info("Will send a get method expecting to recieve 404 code for not implemented code");
		int statusCode;
		getUrl = new URL(Environment.APP_URL + "/" + testID);
		System.out.println("GET Url: " + getUrl);

		requestGetSpec = getBuilder.build();

		statusCode = given().spec(requestGetSpec).when().get(getUrl).getStatusCode();

		if (!(statusCode != 404 || statusCode != 204)) {
			context.report.fail("Id was not empty or other code was returned");
			throw new RuntimeException(" HTTP error code : " + statusCode);
		}
		context.report.pass("Expected code has return: " + statusCode);

	}

	public int sendGetMethodReturnsCode(String testID) throws MalformedURLException {
		context.report.info("Will send a get method expecting to returns the status code");
		int statusCode;
		getUrl = new URL(Environment.APP_URL + "/" + testID);
		System.out.println("GET Url: " + getUrl);

		requestGetSpec = getBuilder.build();

		statusCode = given().spec(requestGetSpec).when().get(getUrl).getStatusCode();

		context.report.pass("Expected code has return: " + statusCode);
		return statusCode;
	}

	public int sendGetMethodToAPIPathReturnsCode() throws MalformedURLException {
		context.report.info("Will send a get method to DIFF expecting to returns the status code");
		int statusCode;

		getUrl = new URL(Environment.APP_URL);
		System.out.println("GET Url: " + getUrl);

		requestGetSpec = getBuilder.build();

		statusCode = given().spec(requestGetSpec).when().get(getUrl).getStatusCode();

		report.pass("Expected code has return: " + statusCode);
		return statusCode;
	}

	public String sendGetMothodForJsonResponse(String testID, int expectedCode) throws MalformedURLException {

		context.report.info("Will send an get method expecting to recieve a Json Response");

		int statusCode;
		getUrl = new URL(Environment.APP_URL + "/" + testID);

		requestGetSpec = getBuilder.build();

		statusCode = given().spec(requestGetSpec).when().get(getUrl).getStatusCode();
		if (statusCode != expectedCode) {
			context.report.fail("Code expected not returned.");
			throw new RuntimeException(" HTTP error code : " + statusCode);
		}

		// Making post request
		Response response = given().spec(requestGetSpec).when().get(getUrl);
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());

		context.report.pass("Response returned: " + JSONResponseBody.toString());

		return JSONResponseBody.toString();
	}

	@Override
	public void validateAppLoaded() {
		// TODO Auto-generated method stub

	}
}
