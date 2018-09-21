package alessandra.assignment.waesAssignment.support;

import java.net.HttpURLConnection;
import java.net.URL;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationControler extends PageObject {

	// Useful things to have in the PageObject
	// public TestContext context;
	// public WebDriver driver;
	public ExtentTest report;

	// Default timeout
	public int timeout;

	public void startApp() {
		// TODO implements running API application from test project

	}

	public void validateApplicationIsOn() throws Exception {
		context.report.info("Validate that the application is running.");

		URL url = new URL(Environment.APP_URL);

		try {
			// opens the connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			conn.disconnect();
			// report.pass("Application is running.");
		} catch (Exception exp) {
			context.report.fail("Application has not started or connection was not possible");
			throw new RuntimeException(exp);
		}
		context.report.pass("Connection Ok");

	}

	public void closeApp() {
		// TODO TODO implements close API application from test project

	}

	@Override
	public void validateAppLoaded() {
		// TODO Auto-generated method stub

	}

}
