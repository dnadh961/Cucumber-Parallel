package com.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtest.SUT;
import com.webtest.elmt.WebTestElmt;

public class StorePage extends BasePage {

	public By product = (By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li"));
	public By addToCart = By.name("Submit");
	public By proceedToCheckout = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");

	public StorePage(SUT sut) {
		super(sut);
	}

	public void openProduct(String productName) {
		findElement(By.xpath("//a[@title='" + productName + "']/ancestor::li")).click();
		sut.handleWaits().sleep(2);
		if (verifyElement(By.xpath("//a[@title='" + productName + "']/ancestor::li//a[@title='View']"))) {
			findElement(By.xpath("//a[@title='" + productName + "']/ancestor::li//a[@title='View']")).click();
		}
	}

	public void addToCart() {
		findElement(addToCart).click();
	}

	public CartPage proceedToCheckout() {
		findElement(proceedToCheckout);
		sut.handleWaits().waitForElementClickable(proceedToCheckout);
		findElement(proceedToCheckout).click();
		return new CartPage(sut);
	}

}
