package com.webtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.webtest.util.GlobalConstants;

public class WaitHandler {

	private WebDriver driver;
	private Wait<WebDriver> wait;

	public WaitHandler(SUT sut) {
		this.driver = sut.getDriver();
		wait = new WebDriverWait(driver, GlobalConstants.GLOBAL_TIME_OUT);
	}

	public boolean waitForElementPresence(By by) {
		boolean Presence = wait.until(ExpectedConditions.presenceOfElementLocated(by)) != null;
		return Presence;
	}
	
	public void waitForElementPresent(WebElement element) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}
	
	 /*public void waitForElementPresent(WebElement element) {
	        wait.until(CustomExpectedConditions.presenceOfElement(element));
	 }*/
	
	public void waitForElementClickable(WebElement elmt) {
		wait.until(ExpectedConditions.elementToBeClickable(elmt));
	}

	public void waitForElementVisibility(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForLoadingMask(){
		waitForElementNotVisible(By.xpath("//div[@class='maskOnFinders']"));
	}

	public void waitForElementClickable(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void waitForPageLoaded() {
		wait.until(checkPageLoadStatus());
	}
	
	public boolean waitForElementNotVisible(By locator) {
		boolean NotVisible = true;
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}catch(Exception e) {
			NotVisible = false;
		}
		return NotVisible;
	}
	
	public void waitTillTitleContains(String text) {
		wait.until(ExpectedConditions.titleContains(text));
	}
	
	public void waitForScrollIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void waitForWindowPresence(Integer requiredWindowNo){
		wait.until(checkWindowPresence(requiredWindowNo.toString()));
	}
	
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			//Ignore the exception
		}
	}
	
	
	
	private Function<WebDriver, Boolean> checkPageLoadStatus() {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").toString().equals("complete");
			}
		};
	}
	
	private Function<WebDriver, Boolean> checkWindowPresence(
			final String requireWindowCount) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				int actualCount = driver.getWindowHandles().size();
				int requiredWindowNo = 0;
				try{
					requiredWindowNo = Integer.parseInt(requireWindowCount);
				}catch(NumberFormatException e){
					//ignore exception
				}
				
				if(requiredWindowNo <= actualCount){
					return true;
				}else{
					return false;
				}
				
			}
		};
	}
	
	public static ExpectedCondition<Boolean> presenceOfElement(
            final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try{
                    element.getTagName();
                }catch(NoSuchElementException e){
                    return false;
                }
                return true;
            }

            @Override
            public String toString() {
                return "presence of element located by: " + element;
            }
        };
    }
}
