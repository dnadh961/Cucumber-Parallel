package com.webtest.fixtures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.google.inject.Inject;
import com.webtest.SUT;
import com.webtest.pages.CartPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class CartPageFixture{
	
	private Logger logger = LoggerFactory.getLogger(CartPageFixture.class);
	private CartPage cartPage;
	private SUT sut;
	
	@Inject
	public CartPageFixture(SUT sut, StorePageFixture aPageFixture) {
		this.sut = sut;
		cartPage = aPageFixture.getCartPage();
	}
	
	@When("proceeds to Checkout from cart page")
	public void proceeds_to_Checkout_from_cart_page() {
		cartPage.proceedToCheckout();
	}

	@When("proceeds to Checkout from address page")
	public void proceeds_to_Checkout_from_address_page() {
		cartPage.processAddressAndCheckout();
	}

	@When("agrees to terms of service and proceeds to Checkout from carrier page")
	public void proceeds_to_Checkout_from_carrier_page() {
		cartPage.processCarrierAndCheckout();
	}

	@When("pay by bank wire")
	public void pay_by_bank_wire() {
	    cartPage.payByWire();
	}

	@When("confirms the order")
	public void confirms_the_order() {
	    cartPage.confirmOrder();
	}

	@Then("\"([^\"]*)\" page should be shown with url \"([^\"]*)\"$")
	public void order_confirmation_page_should_be_shown_with_url(String orderConfirmationheader, String url) {
	    Assert.assertTrue(sut.getDriver().getCurrentUrl().contains(url));
	    Assert.assertTrue(cartPage.verifyHeader(orderConfirmationheader));
	}

	@Then("the text \"([^\"]*)\" should be showcased along with other order confirmation details")
	public void the_text_should_be_showcased_along_with_other_order_confirmation_details(String confirmationText) {
		Assert.assertTrue(cartPage.verifyStepDetailsBar());
		Assert.assertTrue(cartPage.verifyorderCompleteText(confirmationText));
	}
}
