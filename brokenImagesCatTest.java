
package com.x24.frontend.tests;
 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
 
public class brokenImagesCatTest extends BaseCBTTestCase {
  private String baseUrl; 
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
 
  @Before
  public void setUp() throws Exception {
    baseUrl = "https://www.moebel24.de/cat/duschen";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
 
  @Test
  public void testOneProductLoadedCatDuschen() throws Exception {
                           // we wrap the test in a try catch loop so we can log assert failures in
                           // our system
                           try {    
                        	 driver.get(baseUrl + "/");
                   			
                        	 
                        	 //get the attributes 
   
                        	 String imgAlt = driver.findElement(By.xpath("//*[@id='product-ul']/li[1]/div/div[2]/img")).getAttribute("alt");
                        	 String productListCount = driver.findElement(By.xpath("//*[@id=\"product-ul\"]/li[3]")).getAttribute("data-product-list-count");
                        	 int productNO =  Integer.parseInt(productListCount);
                        	 
                        	 
                        	 
                        	 
                        	 
                        	//Print out values
                            System.out.println("Alt 0  is " + imgAlt + " and product no is " + productNO);
                            //Check if attributes are Set
                            assertNotNull(imgAlt);

                            //check if any imgALt description is null -> if yes -> missing image and we throw ae 
                            for (int i = 1 ; i < productNO ; i++){
                            	imgAlt = driver.findElement(By.xpath("//*[@id='product-ul']/li["+i+"]/div/div[2]/img")).getAttribute("alt");
                            	System.out.println("IMG ALT NR " + i +" is " + imgAlt);
                            	assertNotNull(imgAlt);
                            }


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