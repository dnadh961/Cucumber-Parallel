package com.webtest.fixtures;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.webtest.SUT;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {

	private Logger logger = LoggerFactory.getLogger(Hooks.class);
	protected SUT sut = null;
	protected WebDriver driver = null;

	@Inject
	public Hooks(SUT sut) {
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

	private void captureScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
				File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File dest = new File("target/" + scenario.getName() + ".png");
				FileUtils.copyFile(temp, dest);
			} catch (Exception e) {
				scenario.write("Unable to take screenshot<br/>");
			}
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("******************** In Tear Down *******************");
		captureScreenshot(scenario);

		try {
			handleUnexpectedAlert();
			// logout();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
