package com.webtest.pages;

import org.openqa.selenium.By;

import com.webtest.SUT;

public class MyAccountPage extends BasePage{
	
	public By heading= By.cssSelector("h1");
	public By userName= By.className("account");
	public By accountInfo= By.className("info-account");	
	public By signOut= By.className("logout");
	public By women_store_menu = By.linkText("Women");
	
	public MyAccountPage(SUT sut) {
		super(sut);
	}
	
	public String getUserName()
	{
		return findElement(userName).getText();
	}
	
	public String getAccountInfo()
	{
		return findElement(accountInfo).getText();
	}
	
	public String getHeader()
	{
		return findElement(heading).getText();
	}
	
	public boolean verifySignOutLink()
	{
		return findElement(signOut).isDisplayed();
	}
	
	public StorePage openWomenStore()
	{
		findElement(women_store_menu).click();
		return new StorePage(sut);
	}
	
}
