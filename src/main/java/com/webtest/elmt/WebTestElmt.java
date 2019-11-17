package com.webtest.elmt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.webtest.SUT;
import com.webtest.WaitHandler;

public class WebTestElmt implements WebElement{
	
	private WebElement elmt;
	protected SUT sut;
	protected WebDriver driver;
	private WaitHandler waitHandler;
	
	public WebTestElmt(WebElement elmt) {
		this.elmt = elmt;
	}
	
	public void _setContext(SUT sut){
		this.sut = sut;
		waitHandler = this.sut.handleWaits();
		driver = this.sut.getDriver();
	}

	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return elmt.getScreenshotAs(arg0);
	}

	public void clear() {
		elmt.clear();
	}

	public void click() {
		click(true);
	}
	
	public void click(boolean wait) {
		//waitHandler.waitForElementClickable(elmt);
		elmt.click();
		if(wait){
			waitHandler.waitForPageLoaded();
			waitHandler.waitForLoadingMask();
		}
		//code to handle unexpected dialog
		closeDialog();
	}
	
	private void closeDialog(){
		try{
			driver.switchTo().frame("edr_l_first");
			driver.findElement(By.cssSelector("img[title='Close']")).click();
			driver.switchTo().defaultContent();
		} catch(Exception e){
			//ignore exception
		}
	}
	
	public void click(int seconds) {
		elmt.click();
		waitHandler.sleep(seconds);
		waitHandler.waitForPageLoaded();
		waitHandler.waitForLoadingMask();
	}
	
	public WebElement findElement(By by) {
		closeDialog();
		return elmt.findElement(by);
	}

	public List<WebElement> findElements(By by) {
		return elmt.findElements(by);
	}

	public String getAttribute(String attr) {
		return elmt.getAttribute(attr);
	}

	public String getCssValue(String css) {
		return elmt.getCssValue(css);
	}

	public Point getLocation() {
		return elmt.getLocation();
	}

	public Rectangle getRect() {
		return elmt.getRect();
	}

	public Dimension getSize() {
		return elmt.getSize();
	}

	public String getTagName() {		
		return elmt.getTagName();
	}

	public String getText() {		
		return elmt.getText();
	}

	public boolean isDisplayed() {		
		return elmt.isDisplayed();
	}

	public boolean isEnabled() {		
		return elmt.isEnabled();
	}

	public boolean isSelected() {		
		return elmt.isSelected();
	}

	public void sendKeys(CharSequence... keys) {
		elmt.sendKeys(keys);	  
	}

	public void submit() {
		elmt.submit();
	}
	
	public WebElement getWebElmt() {
        return elmt;
    }

}
