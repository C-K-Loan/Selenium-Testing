
package com.x24.frontend.tests;
 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
 
public class URLupdatedByFilter extends BaseCBTTestCase {
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
 
  @Before
  public void setUp() throws Exception {
    baseUrl = "https://www.moebel24.de/cat/duschen";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
   
  
  //todo : set price Filter and see if URL is properly Updated  (should contain "|priceRange|0-75|")
  //https://www.moebel24.de/cat/ = 25 chars
  @Test
  public void testURLupdatedByFilter() throws Exception {
                           // we wrap the test in a try catch loop so we can log assert failures in
                           // our system
                           try {    
                        	   	driver.get(baseUrl + "/");
                        	   	String filterURLValue = "|priceRange|0-75|";// the filter value that should be in the URL
                        	    driver.findElement(By.xpath("//div[@id='root|priceRange']/div/a")).click(); // click the Price Filter Button
                        	    driver.findElement(By.xpath("//li[@id='root|priceRange|0-75']/div/label/span")).click(); //click by How much to Filter
                        	    Thread.sleep(5000);
                        	    driver.findElement(By.cssSelector("#PreisModalMob > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")).click(); // confirm Selection
							    String url = driver.getCurrentUrl();
							    Thread.sleep(5000);
							 //  System.out.println(url + "THE URL IS   , TS IS FIlTER TEST ");
							    assertTrue(url.toLowerCase().contains(filterURLValue.toLowerCase()));// Assert that the URL is properly Updated, else throw exeption
							    
							    

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