package com.x24.frontend.tests;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CBTExampleTestCase extends BaseCBTTestCase {

	@Test
	public void testCBTExample() throws Exception {

		// we wrap the test in a try catch loop so we can log assert failures in
		// our system
		try {
			// load the page url
			System.out.println("Loading Url");
			driver.get("http://crossbrowsertesting.github.io/selenium_example_page.html");
			// maximize the window - DESKTOPS ONLY
			System.out.println("Maximizing window");
			driver.manage().window().maximize();
			// Check the page title (try changing to make the assertion fail!)
			assertEquals(driver.getTitle(), "Selenium Test Example Page");
			// if we get to this point, then all the assertions have passed
			// that means that we can set the score to pass in our system
			testScore = "pass";
		} catch (AssertionError ae) {
			// if we have an assertion error, take a snapshot of where the test
			// fails
			// and set the score to "fail"
			String snapshotHash = takeSnapshot(driver.getSessionId().toString());
			setDescription(driver.getSessionId().toString(), snapshotHash, ae.toString());
			testScore = "fail";
		}
	}

}