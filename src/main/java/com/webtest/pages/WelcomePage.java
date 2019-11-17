package com.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.webtest.SUT;

public class WelcomePage extends BasePage{
	
	public By signIn = By.className("login");
	
	public WelcomePage(SUT sut) {
		super(sut);
	}
	
	public AuthenticationPage signIn() {
		findElement(signIn).click();
		return new AuthenticationPage(sut);
	}
	
}
