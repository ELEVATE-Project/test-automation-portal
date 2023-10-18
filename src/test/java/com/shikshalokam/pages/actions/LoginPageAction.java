package com.shikshalokam.pages.actions;

import com.aventstack.extentreports.Status;
import com.shikshalokam.client.ShikshaLokamClient;
import com.shikshalokam.pages.objects.LoginPageObjects;
import com.shikshalokam.utils.logger.Logger;
import com.shikshalokam.utils.prop.PropUtlis;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LoginPageAction {


    LoginPageObjects loginPageObjects;
    
    String appUrl;
    public String getEnvironmentValue() throws Exception {
    	return appUrl = PropUtlis.readConfig("webAppConfig", "appUrl");
    }
   
    public LoginPageAction() throws Exception {
        loginPageObjects = PageFactory.initElements(ShikshaLokamClient.get().driver(), LoginPageObjects.class);
    }


    // enter user name to field.
    public void enterUserName1(String userName) throws Exception {
         ShikshaLokamClient.get().gestures().sendValueToTextBox(loginPageObjects.emailfield, userName);
         ShikshaLokamClient.get().report().log(Status.INFO, "Entered user name is : " + userName );
     }
  
    // enter password to field.
    public void enterPassword1(String password) throws Exception {
    	ShikshaLokamClient.get().gestures().sendValueToTextBox(loginPageObjects.pwdfield, password);
        ShikshaLokamClient.get().report().log(Status.INFO, "Entered password is : " + password );
    }

    // click on Login Buttom
    public void clickOnLoginButton1() throws Exception {
    	  if(getEnvironmentValue().contains("dev")) {
        ShikshaLokamClient.get().gestures().click(loginPageObjects.loginbtn);
        ShikshaLokamClient.get().report().log(Status.INFO, "clicked on the login button ");
        
    	  }else{
    		  Assert.assertTrue(ShikshaLokamClient.get().gestures().isElementPresent(loginPageObjects.loginbtn),"Login button is present");
    		  Logger.logAndReportPass("Login button is present");
    	  }}

    //scroll till the element is visibie
     public void scrollToDownloadnow() throws Exception {
	     ShikshaLokamClient.get().gestures().scrollToElement(loginPageObjects.downloadnow);
	     System.out.println(loginPageObjects.downloadnow);
		 String text = loginPageObjects.downloadnow.getText();
		 System.out.println(text);			   
	     ShikshaLokamClient.get().report().log(Status.INFO, "Text on the Element : " +text);
	    }  
     
	 public void verifyDownloadText(String Buttontxt) throws Exception {
    	ShikshaLokamClient.get().gestures().isDisplayed(loginPageObjects.downloadnow);
    	String actualButtonTxt = ShikshaLokamClient.get().gestures().getText(loginPageObjects.downloadnow);
    	ShikshaLokamClient.get().getAsserts().assertEquals(Buttontxt.trim(), actualButtonTxt.trim(),
    	    	"Download text is not Matching..");
    	 ShikshaLokamClient.get().report().log(Status.INFO, "Download text  matched and displayed succesfully.");
    	}
	 
	 public void verifyvalidationtext(String validationmsgtxt) throws Exception {
	    	ShikshaLokamClient.get().gestures().isDisplayed(loginPageObjects.validationmsg);
	    	String actualButtonTxt = ShikshaLokamClient.get().gestures().getText(loginPageObjects.validationmsg);
	    	ShikshaLokamClient.get().getAsserts().assertEquals(validationmsgtxt.trim(), actualButtonTxt.trim(),
	    	"validation Text is not Matching..");
	    	Logger.logAndReportPass("validation Text is Matching... matched and displayed succesfully.");
	    	}
	 

}
