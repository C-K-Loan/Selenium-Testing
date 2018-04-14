package com.x24.frontend.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HomepageAvailable extends BaseCBTTestCase {
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    baseUrl = "https://www.moebel24.de";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMoebel24() throws Exception {
		// we wrap the test in a try catch loop so we can log assert failures in
		// our system
		try {
			driver.get(baseUrl + "/");
			
			String title = driver.getTitle();
			assertNotNull(title);
			assertTrue(title.length() > 0);
  
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

  @After
  public void tearDown() throws Exception {
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
