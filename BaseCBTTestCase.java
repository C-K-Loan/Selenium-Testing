package com.x24.frontend.tests;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLEncoder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class BaseCBTTestCase {

	static String username = System.getenv("CBT_USERNAME");
	static String authkey = System.getenv("CBT_APIKEY");
	static String browser = System.getenv("CBT_BROWSER");
	static String operatingSystem = System.getenv("CBT_OPERATING_SYSTEM");
	static String resolution = System.getenv("CBT_RESOLUTION");
	static String buildName = System.getenv("CBT_BUILD_NAME");
	static String buildNumber = System.getenv("CBT_BUILD_NUMBER");

	static RemoteWebDriver driver;

	String testScore;

	@BeforeClass
	public static void runBeforeClass() {
		
		System.out.println("Init test.");

		if (username == null)
			throw new IllegalArgumentException("username is missing");
		if (authkey == null)
			throw new IllegalArgumentException("authkey is missing");
		if (browser == null)
			throw new IllegalArgumentException("browser is missing");
		if (operatingSystem == null)
			throw new IllegalArgumentException("operatingSystem is missing");
		if (resolution == null)
			throw new IllegalArgumentException("resolution is missing");
		// if (buildName == null)
		// throw new IllegalArgumentException("buildName is missing");
		// if (buildNumber == null)
		// throw new IllegalArgumentException("buildNumber is missing");

		// System.out.println(username);
		// System.out.println(authkey.substring(0, 2));
		// System.out.println(browser);
		// System.out.println(operatingSystem);
		// System.out.println(resolution);
		// System.out.println(buildName);
		// System.out.println(buildNumber);
		//

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("name", buildName);
		caps.setCapability("build", buildNumber);
		caps.setCapability("browser_api_name", browser);
		caps.setCapability("os_api_name", operatingSystem);
		caps.setCapability("screen_resolution", resolution);
		caps.setCapability("record_video", "true");
		caps.setCapability("record_network", "true");

		System.out.println("caps: " + caps);

		try {
			String uris = "http://" + URLEncoder.encode(username, "utf-8") + ":" + authkey
					+ "@hub.crossbrowsertesting.com:80/wd/hub";

			URI uri = URI.create(uris);

			driver = new RemoteWebDriver(uri.toURL(), caps);

			System.out.println("Session ID: " + driver.getSessionId());

		} catch (UnsupportedEncodingException | MalformedURLException e) {

			throw new IllegalStateException(e);
		}
	}

	@Before
	public void initScoreBeforeTest() {
		System.out.println("Init score");
		testScore = "";
	}

	@After
	public void setScoreAfterTest() {
		// here we make an api call to actually send the score
		try {
			System.out.println("Setting score");
			setScore(driver.getSessionId().toString(), testScore);
		} catch (UnirestException e) {
			throw new IllegalStateException(e);
		}
	}

	@AfterClass
	public static void runAfterClass() {

		System.out.println("Quiting driver");
		driver.quit();
	}

	static JsonNode setScore(String seleniumTestId, String score) throws UnirestException {
		// Mark a Selenium test as Pass/Fail
		HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId).field("action", "set_score")
				.field("score", score).asJson();
		return response.getBody();
	}

	String takeSnapshot(String seleniumTestId) throws UnirestException {
		/*
		 * Takes a snapshot of the screen for the specified test. The output of
		 * this function can be used as a parameter for setDescription()
		 */
		HttpResponse<JsonNode> response = Unirest
				.post("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId).asJson();
		// grab out the snapshot "hash" from the response
		String snapshotHash = (String) response.getBody().getObject().get("hash");
		System.out.println("Snapshot hash: " + snapshotHash);
		return snapshotHash;
	}

	JsonNode setDescription(String seleniumTestId, String snapshotHash, String description) throws UnirestException {
		/*
		 * sets the description for the given seleniemTestId and snapshotHash
		 */
		HttpResponse<JsonNode> response = Unirest
				.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots/{snapshotHash}")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId)
				.routeParam("snapshotHash", snapshotHash).field("description", description).asJson();
		return response.getBody();
	}

}
