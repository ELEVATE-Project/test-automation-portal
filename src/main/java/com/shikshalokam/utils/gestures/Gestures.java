package com.shikshalokam.utils.gestures;

import com.aventstack.extentreports.Status;
import com.shikshalokam.client.ShikshaLokamClient;
import com.shikshalokam.report.ShikshaLokamReport;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;


public class Gestures {
    public WebDriver driver;

    public Gestures(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void gestures() {
        debugLogger().info("Gestures");
    }

    public void sendValueToTextBox(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {

        return element.getText();
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    
    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }
    
   

    public boolean click(WebElement element) throws Exception {
        String identifyBy = "xpath";
        boolean bElementFound = false;
        try {
            if (identifyBy.equalsIgnoreCase("xpath")) {
                bElementFound = element.isDisplayed();
                element.click();
            } else if (identifyBy.equalsIgnoreCase("id")) {
                bElementFound = this.driver.findElement(By.id(element.getAttribute("ID"))).isDisplayed();
                element.click();
            } else {
                ShikshaLokamClient.get().logger().info("None of the Element Selected Please check the Element Once: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShikshaLokamClient.get().logger()
                    .error("None of the Element Selected Please check the Element Once: " + element);
            Assert.assertTrue(false, "***Method: click: Exception occured for locator= " + e.getMessage());
        }
        ShikshaLokamClient.get().logger().info("Method: click for locator = " + element + " status = " + bElementFound);

        return bElementFound;
    }

    public boolean type(WebElement element, String value) throws Exception {
        String identifyBy = "xpath";
        boolean bElementFound = false;
        try {
            if (identifyBy.equalsIgnoreCase("xpath")) {
                bElementFound = element.isDisplayed();
                element.sendKeys(value);
            } else if (identifyBy.equalsIgnoreCase("id")) {
                bElementFound = this.driver.findElement(By.id(element.getAttribute("ID"))).isDisplayed();
                element.sendKeys(value);
            } else {
                ShikshaLokamClient.get().logger().info("None of the Element Selected Please check the Element Once: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShikshaLokamClient.get().logger()
                    .error("None of the Element Selected Please check the Element Once: " + element);
            Assert.assertTrue(false, "***Method: type: Exception occured for locator= " + e.getMessage());
        }
        ShikshaLokamClient.get().logger().info("Method: type for locator = " + element + " status = " + bElementFound);
        return bElementFound;
    }

    public void clear(WebElement element) {
        try {
            waitTillTheElementIsVisibleAndClickable(element);
            element.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    public boolean type(WebElement element) throws IOException {
        String identifyBy = "xpath";
        boolean bElementFound = false;
        waitTillTheElementIsVisibleAndClickable(element);
        try {
            if (identifyBy.equalsIgnoreCase("xpath")) {
                bElementFound = element.isDisplayed();
            } else if (identifyBy.equalsIgnoreCase("id")) {
                bElementFound = this.driver.findElement(By.id(element.getAttribute("ID"))).isDisplayed();
            } else {
                ShikshaLokamClient.get().logger().info("None of the Element Selected Please check the Element Once: ");
            }
        } catch (Exception e) {
            ShikshaLokamClient.get().logger().error("Method: assertIfElementExists :: Exception occured for locator = "
                    + element + " exception is = " + e.getMessage());
            Assert.assertTrue(false,
                    "***Method: assertIfElementExists: Exception occured for locator= " + e.getMessage());
        }
        ShikshaLokamClient.get().logger()
                .info("Method: assertIfElementExists for locator = " + element + " status = " + bElementFound);
        return bElementFound;
    }

    public void clearValue(WebElement element) {
        element.clear();
    }
    

    public boolean assertIfElementExistsAndClick(WebDriver mDriver, String identifyBy, WebElement locator)
            throws Exception {
        boolean bElementFound = false;
        try {
            waitTillTheElementIsVisibleAndClickable(locator);
            if (identifyBy.equalsIgnoreCase("xpath")) {
                bElementFound = locator.isDisplayed();
                locator.click();
            } else if (identifyBy.equalsIgnoreCase("id")) {
                bElementFound = mDriver.findElement(By.id(locator.getAttribute("ID"))).isDisplayed();
                mDriver.findElement(By.id(locator.getAttribute("ID"))).click();
            } else {
                ShikshaLokamClient.get().logger().info("None of the Element Selected Please check the Element Once: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShikshaLokamReport.getExtentTest().log(Status.FAIL,
                    "Method: assertIfElementExists :: Exception occured for locator = " + locator + " exception is = "
                            + e.getMessage());
            Assert.assertTrue(false,
                    "***Method: assertIfElementExists: Exception occured for locator= " + e.getMessage());
        }
        ShikshaLokamClient.get().logger()
                .info("Method: assertIfElementExists for locator = " + locator + " status = " + bElementFound);
        return bElementFound;
    }

    // Method to Wait till the Element is Visible and Clickable
    public void waitTillTheElementIsVisibleAndClickable(WebElement element) {
        try {
        	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
           // WebDriverWait wait = new WebDriverWait(driver, 30);     //30
            wait.until(ExpectedConditions.visibilityOf(element));
            wait = new WebDriverWait(driver, Duration.ofSeconds(25));                     //25
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            debugLogger().error(e.getLocalizedMessage());
        }
    }


    // New

    public boolean isElementPresent(WebElement locator) {
        try {
            waitForElementToAppear(locator);
            if (locator.isDisplayed())
                System.out.println("Element present on screen ***********" + locator);
            return true;
        } catch (Exception e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        }
    }
    //to verify there is no element present
	public boolean verifyNoelement(WebElement element) throws Exception {
        try
        {
           if(element.isDisplayed())
        	   
           {
           return false;
           }
           return false;
        }
        catch(Exception e)
        {
        	
            return true;
        }
	}
	
	public boolean verifyNoelement1(WebElement locator)  {
        try
        {
           if(locator.isDisplayed())
         return false;                                                          
        }
        catch(Exception e) {
        }
         return true;
        }
		
	
	
	
	  public boolean isElementnotPresent(WebElement locator) {
	        try {
	            if (locator.isDisplayed())
	                System.out.println("Element present on screen ***********" + locator);
	            return false;
	        } catch (Exception e) {
	            System.out.println("Element not present on screen **************" + locator);
	            return true;
	        }
	    }
	/*private boolean isPresent(WebElement element) {
		try {
		    element;
		} catch (NoSuchElementException e) {
		    return false;
		}
		return true;
		}
*/
	
    public WebElement waitForElementToAppear(WebElement id) {
        WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }


    public void waitAndClickElementisVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void tabKeyAndWait() throws AWTException, InterruptedException {
    	Robot r = new Robot();
        r.keyPress(KeyEvent.VK_TAB);  
         
        //Release Enter
         r.keyRelease(KeyEvent.VK_TAB);
         Thread.sleep(2000);
    }
    
    public void moveToElement(WebElement element) throws Exception {
    	Thread.sleep(5000);
    	Actions a = new Actions(driver);
    	a.moveToElement(element).perform();
    	/**a.moveByOffset(10,20).perform();
    	a.click().perform();
    	//move to an element
    	//IWebElement l = driver.FindElement(By.name("txtnam"));
    	a.moveToElement(element).perform(); **/
    }
    
    //******new
    public void PressEsc() throws Exception {
    	
    	Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ESCAPE);  
         
        //Release Escape
         r.keyRelease(KeyEvent.VK_ESCAPE);
         Thread.sleep(2000);
         
       
}
    
 public void selectAllandDelete() throws Exception {
    	
    	Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);  
        r.keyPress(KeyEvent.VK_A); 
        //Release Escape
         r.keyRelease(KeyEvent.VK_CONTROL);
         r.keyRelease(KeyEvent.VK_A);
         
         Thread.sleep(2000);
         r.keyPress(KeyEvent.VK_BACK_SPACE); 
         r.keyRelease(KeyEvent.VK_BACK_SPACE);
       
}

 
	

	public void noelement(WebElement element, String string) {
		Assert.assertNull(element);
		
	}
	  public void getuserdir() {
		 System.getProperty("user.dir");
	    }
	  
	  
	   public void scrollToElement(WebElement element) throws Exception {
		   JavascriptExecutor js = (JavascriptExecutor) ShikshaLokamClient.get().driver();
		   js.executeScript("arguments[0].scrollIntoView(true);", element);	
	   }
	   
	   public void scrollToElementAndClick(WebElement element) throws Exception {
		   JavascriptExecutor js = (JavascriptExecutor) ShikshaLokamClient.get().driver();
		   js.executeScript("arguments[0].scrollIntoView(true);", element);
		   Thread.sleep(10000);
		   element.click();  	
	   }
	   
	   public void refreshpage() throws Exception {
			ShikshaLokamClient.get().driver().navigate().refresh();
			ShikshaLokamClient.get().logger().info("Page refreshed");
	   }
	   
	   public void browserBackButton() throws Exception {
			ShikshaLokamClient.get().driver().navigate().back();
	ShikshaLokamClient.get().logger().info("clicked on Browser Back Button");	 }
	
	}

		
	
    

