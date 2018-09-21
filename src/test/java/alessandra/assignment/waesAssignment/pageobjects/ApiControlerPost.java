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

public class ApiControlerPost extends PageObject {

	JSONObject expetectedResult = new JSONObject();
	RequestSpecBuilder postBuilder = new RequestSpecBuilder();
	RequestSpecification requestPostSpec;
	URL getUrl;

	public int sendPostReturnStatusCode(String testID, String side, String body) throws MalformedURLException {

		context.report.info("Will send post method expecting to recieve a StatusCode");
		int statusCode;

		getUrl = new URL(Environment.APP_URL + "/" + testID + "/" + side);
		System.out.println("\nGET Url: " + getUrl);

		requestPostSpec = postBuilder.build();

		// Setting API's body
		postBuilder.setBody(body);

		// Setting content type as application/json
		postBuilder.setContentType("application/json; charset=UTF-8");

		requestPostSpec = postBuilder.build();

		statusCode = given().spec(requestPostSpec).when().post(getUrl).getStatusCode();

		context.report.pass("Status Code Returned: " + statusCode);

		return statusCode;
	}

	public String sendPostMothodForJsonResponse(String testID, String side, String apiBody, int expectedError)
			throws MalformedURLException {

		context.report.info("Will send a post method expecting to recieve a Json Response");

		int statusCode;

		getUrl = new URL(Environment.APP_URL + "/" + testID + "/" + side);
		System.out.println("\nGET Url: " + getUrl);

		requestPostSpec = postBuilder.build();

		// Setting API's body
		postBuilder.setBody(apiBody);

		// Setting content type as application/json
		postBuilder.setContentType("application/json; charset=UTF-8");

		requestPostSpec = postBuilder.build();

		statusCode = given().spec(requestPostSpec).when().post(getUrl).getStatusCode();

		if (statusCode != expectedError) {
			context.report.fail("\nHTTP error code : " + statusCode);
			throw new RuntimeException(" \nHTTP error code : " + statusCode);
		}

		// return response body as string
		Response response = given().spec(requestPostSpec).when().post(getUrl);
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());

		context.report.pass(" Json Response Returned: " + JSONResponseBody.toString());

		return JSONResponseBody.toString();

	}

	public void sendPostMethodWithIdAndSide(String testID, String side, String apiBody, int expectedError)
			throws MalformedURLException {

		context.report
				.info("Will send a post method with the parameters: \nSide: " + side + "\nId: " + testID + "\nBody:"
				+ apiBody);

		int statusCode;

		getUrl = new URL(Environment.APP_URL + "/" + testID + "/" + side);
		System.out.println("\nGET Url: " + getUrl);

		requestPostSpec = postBuilder.build();

		// Setting API's body
		postBuilder.setBody(apiBody);

		// Setting content type as application/json
		postBuilder.setContentType("application/json; charset=UTF-8");

		requestPostSpec = postBuilder.build();

		statusCode = given().spec(requestPostSpec).when().post(getUrl).getStatusCode();

		if (statusCode != expectedError) {
			context.report.fail("The post was not subimitted. Code returned: " + statusCode);
			throw new RuntimeException(" HTTP error code : " + statusCode);
		}
		context.report.pass("Post method submitted. Code Returned: " + statusCode);
	}

	@Override
	public void validateAppLoaded() {
		// TODO Auto-generated method stub

	}
}
