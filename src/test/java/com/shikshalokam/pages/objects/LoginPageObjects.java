package com.shikshalokam.pages.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects {

    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailfield;
  
    @FindBy(xpath = "//input[@type='password']")
    public WebElement pwdfield;
  
    @FindBy(xpath = "//button//span[.=' Login ']")
    public WebElement loginbtn;
    
    @FindBy(xpath = "//img[@class='footer-mobile-logo']")
    public WebElement mobileLogo;
     
    @FindBy(xpath = "//button//span[.='Download now']")
    public WebElement downloadnow;
    
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-auth[1]/div[1]/div[2]/div[2]/div[1]/app-login[1]/div[1]/div[1]/div[2]/div[1]/app-dynamic-form[1]/div[1]/form[1]/div[1]/mat-form-field[1]/div[1]/div[2]/div[1]/mat-error[1]/div[1]")
    public WebElement validationmsg;
}

