package com.webtest.fixtures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.webtest.SUT;
import com.webtest.pages.CartPage;
import com.webtest.pages.StorePage;

import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class StorePageFixture {

	private Logger logger = LoggerFactory.getLogger(StorePageFixture.class);
	private CartPage cartPage;

	public CartPage getCartPage() {
		return cartPage;
	}

	private StorePage storePage;
	private SUT sut;

	@Inject
	public StorePageFixture(SUT sut, MyAccountPageFixture aPageFixture) {
		this.sut = sut;
		storePage = aPageFixture.getStorePage();
	}

	@When("adds \"([^\"]*)\" to cart")
	public void adds_to_cart(String productName) {
		storePage.openProduct(productName);
		storePage.addToCart();
	}

	@When("proceeds to Checkout from product page")
	public void proceeds_to_Checkout_from_product_page() {
		cartPage = storePage.proceedToCheckout();
	}
}
