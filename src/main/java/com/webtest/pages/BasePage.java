package com.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webtest.SUT;
import com.webtest.elmt.WebTestElmt;

public class BasePage {

	private Logger logger = LoggerFactory.getLogger(BasePage.class);
	protected SUT sut = null;
	protected WebDriver driver = null;

	public BasePage(SUT sut) {
		this.sut = sut;
		this.driver = sut.getDriver();
	}

	private void handleUnexpectedAlert() {
		try {
			driver.switchTo().frame("edr_l_first");
			driver.findElement(By.cssSelector("img[title='Close']")).click();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			// ignore exception
		}
	}

	

	public WebTestElmt findElement(By by) {
		logger.info("finding the element by logic : " + by.toString());
		handleUnexpectedAlert();
		sut.handleWaits().waitForElementPresence(by);
		sut.handleWaits().waitForElementVisibility(by);
		WebElement elmt = driver.findElement(by);
		WebTestElmt webTestElmt = new WebTestElmt(elmt);
		webTestElmt._setContext(sut);
		return webTestElmt;
	}
	
	public Select findSelect(By by) {
		logger.info("finding the element by logic : " + by.toString());
		handleUnexpectedAlert();
		sut.handleWaits().waitForElementPresence(by);
		WebElement elmt = driver.findElement(by);
		Select select = new Select(elmt);
		return select;
	}

	public boolean verifyElement(By by) {
		logger.info("verifying the element by logic : " + by.toString());
		boolean isPresent = true;
		try {
			driver.findElement(by);
		} catch (NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}
}
