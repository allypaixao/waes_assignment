package alessandra.assignment.waesAssignment.support;

import org.json.JSONObject;

public class SetData {

	JSONObject expetectedResult = new JSONObject();

	public String setTestExpectedData(String expectedDetail, String expectedType) {

		expetectedResult.put("detail", expectedDetail);
		expetectedResult.put("type", expectedType);
		System.out.print("Expected Response: \n" + expetectedResult);

		return expetectedResult.toString();

	}

	public String setTestExpectedDataError(String expectedMessage, int expectedCode) {

		expetectedResult.put("errorMessage", expectedMessage);
		expetectedResult.put("errorCode", expectedCode);
		System.out.print("Expected Response: \n" + expetectedResult);

		return expetectedResult.toString();

	}

	public String setTypeExpectedData(String expectedType) {
		expetectedResult.put("type", expectedType);
		return expetectedResult.toString();
	}

}
