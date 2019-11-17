package com.webtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class ScriptExecutor {
	
	private SUT sut;
	private WebDriver driver = null;
	private JavascriptExecutor jsExecutor = null;

	public ScriptExecutor(SUT sut) {
		this.sut = sut;
		driver = this.sut.getDriver();
		jsExecutor = (JavascriptExecutor)driver;
	}
	
	public void click(WebElement elmt){		
		click(elmt, true);
	}
	
	public void click(WebElement elmt, boolean wait){
		try {
			sut.handleWaits().waitForElementClickable(elmt);
			jsExecutor.executeScript("arguments[0].click();", elmt);
			if(wait){
				sut.handleWaits().waitForPageLoaded();
				sut.handleWaits().waitForLoadingMask();
			}
		}catch(TimeoutException TOE) {
			sut.handleWaits().waitForScrollIntoView(driver, elmt);
			click(elmt,wait);
		}
		
	}
	
	public void click(By by){
		sut.handleWaits().waitForElementClickable(by);
		click(driver.findElement(by));
	}
	
	public void clear(WebElement elmt){
		jsExecutor.executeScript("arguments[0].clear();", elmt);
	}
	
	public void clear(By by){
		clear(driver.findElement(by));
	}
	
	public void clickElement(WebElement elmt) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(elmt).click().build().perform();
			sut.handleWaits().waitForPageLoaded();
			sut.handleWaits().waitForLoadingMask();
		}catch(MoveTargetOutOfBoundsException OBE) {
			
			click(elmt);
		}
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
		
}
