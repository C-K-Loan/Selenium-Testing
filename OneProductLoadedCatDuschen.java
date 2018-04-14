
package com.x24.frontend.tests;
 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
 
public class OneProductLoadedCatDuschen extends BaseCBTTestCase {
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
//                            String productListCounter =  driver.findElement(By.cssSelector("#product-ul > li > div.productBoxSec-mbl.productBoxTopSellerSp-mbl > a.productBoxHoverSec-mbl.clickOutLink")).getAttribute("productListCounter");
//                            String productListCount =  driver.findElement(By.cssSelector("#product-ul > li > div.productBoxSec-mbl.productBoxTopSellerSp-mbl > a.productBoxHoverSec-mbl.clickOutLink")).getAttribute("productListCount");
//                            String dataClickCat =driver.findElement(By.cssSelector("#product-ul > li > div.productBoxSec-mbl.productBoxTopSellerSp-mbl > a.productBoxHoverSec-mbl.clickOutLink")).getAttribute("data-clickcat");
//                            String dataAdvertiser = driver.findElement(By.cssSelector("#product-ul > li > div.productBoxSec-mbl.productBoxTopSellerSp-mbl > a.productBoxHoverSec-mbl.clickOutLink")).getAttribute("data-advertiser");
//                            
//                            
                            
                        	 driver.get(baseUrl + "/");
                   			
                        	 
                        	 //get the attributes 
                        	 String productListCounter = driver.findElement(By.xpath("//*[@id=\"product-ul\"]/li[1]")).getAttribute("data-product-list-counter");
                             String productListCount = driver.findElement(By.xpath("//*[@id=\"product-ul\"]/li[1]")).getAttribute("data-product-list-count");
                             String dataClickCat = driver.findElement(By.xpath("//*[@id=\"product-ul\"]/li[1]/div/a")).getAttribute("data-clickcat");
                             String dataAdvertiser = driver.findElement(By.xpath("//*[@id=\"product-ul\"]/li[1]/div/a")).getAttribute("data-advertiser");

                        	   
                        	//Print out values
                            System.out.println("data Advertiser is : " +  dataAdvertiser);
                            System.out.println("product list Count is : " +  productListCount);
                            System.out.println("product list Counter is : " +  productListCounter);
                            System.out.println("DataClickcat is : " +  dataClickCat);
                            
                            
                            //Check if attributes are Set
                            assertNotNull(dataAdvertiser);
                            assertNotNull(dataClickCat);;
                            
                            
                            


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