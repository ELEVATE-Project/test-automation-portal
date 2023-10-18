package com.shikshalokam.test;

import org.testng.annotations.Test;
import com.shikshalokam.annotation.Author;
import com.shikshalokam.pages.actions.LoginPageAction;
import com.shikshalokam.utils.gSheet.TestData;
import com.shikshalokam.utils.prop.PropUtlis;

import java.util.Map;

public class LoginPageTest {


    Map<String, String> loginTestData;
    Map<String, String> text;

    public LoginPageAction getLoginPageActions() throws Exception {
        return new LoginPageAction();
    }
    
    String appUrl;
    public String getEnvironmentValue() throws Exception {
    	return appUrl = PropUtlis.readConfig("webAppConfig", "appUrl");
    }
   
    ////////////// MentordED /////////////////////////////////
    //DemoScript//
    @Test(description = "login ")
    @Author(name = "Shreejith")
    public void loginMentorED() throws Exception {
        loginTestData = TestData.getFullGoogleSheetDataAsMapString("LoginTestData!A:B");
       text = TestData.getFullGoogleSheetDataAsMapString("Text!A:B");
        getLoginPageActions().enterUserName1(loginTestData.get("userName"));
        getLoginPageActions().enterPassword1(loginTestData.get("password"));  
        //getLoginPageActions().verifyvalidationtext(text.get("validationmsg"));
         getLoginPageActions().clickOnLoginButton1();
         Thread.sleep(10000);
     
          if(getEnvironmentValue().contains("dev")) {
        Thread.sleep(5000);
        getLoginPageActions().scrollToDownloadnow();
        getLoginPageActions().verifyDownloadText(text.get("ButtonText"));  
     
        }  
    }}
  
