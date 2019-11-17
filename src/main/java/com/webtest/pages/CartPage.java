package com.webtest.pages;

import org.openqa.selenium.By;

import com.webtest.SUT;

public class CartPage extends BasePage{
	
	public By proceedToCheckout = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
	public By processAddress_Checkout = By.name("processAddress");
	public By acceptTerms = By.id("uniform-cgv");
	public By processCarrier_Checkout = By.name("processCarrier");
	public By payByWire = By.className("bankwire");
	public By confirmOrder = By.xpath("//*[@id='cart_navigation']/button");
	public By orderConfirmationHeader = By.cssSelector("h1");
	public By lastStepsDone = By.xpath("//li[@class='step_done step_done_last four']");
	public By currentStepDone = By.xpath("//li[@id='step_end' and @class='step_current last']");
	public By orderCompleteText = By.xpath("//*[@class='cheque-indent']/strong");
	
	public CartPage(SUT sut) {
		super(sut);
	}
	
	public void proceedToCheckout()
	{
		findElement(proceedToCheckout).click();
	}
	
	public void processAddressAndCheckout()
	{
		findElement(processAddress_Checkout).click();
	}
	
	public void processCarrierAndCheckout()
	{
		findElement(acceptTerms).click();
		findElement(processCarrier_Checkout).click();
	}
	
	public void payByWire()
	{
		findElement(payByWire).click();
	}
	
	public void confirmOrder()
	{
		findElement(confirmOrder).click();
	}
	
	public boolean verifyHeader(String headerText)
	{
		return findElement(orderConfirmationHeader).getText().trim().equalsIgnoreCase(headerText);
	}
	
	public boolean verifyStepDetailsBar()
	{
		return (verifyElement(lastStepsDone)&&verifyElement(currentStepDone));
	}
	
	public boolean verifyorderCompleteText(String orderCompleteText)
	{
		return findElement(this.orderCompleteText).getText().trim().equalsIgnoreCase(orderCompleteText);
	}
}
