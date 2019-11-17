package com.webtest.fixtures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.webtest.SUT;
import com.webtest.pages.MyAccountPage;
import com.webtest.pages.StorePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import junit.framework.Assert;

@ScenarioScoped
public class MyAccountPageFixture{
	
	private Logger logger = LoggerFactory.getLogger(MyAccountPageFixture.class);
	private MyAccountPage myAccountPage;
	private StorePage storePage;
	public StorePage getStorePage() {
		return storePage;
	}

	private SUT sut;
	
	@Inject
	public MyAccountPageFixture(SUT sut, AuthenticationPageFixture aPageFixture) {
		this.sut = sut;
		myAccountPage = aPageFixture.getMyAccountPage();
	}

	@Then("^\"([^\"]*)\" page should be displayed with message as \"([^\"]*)\"$")
	public void page_should_be_displayed_with_message_as(String header, String accountInfo) throws Throwable {
		
		Assert.assertEquals(header, myAccountPage.getHeader());
		Assert.assertTrue(myAccountPage.getAccountInfo().contains(accountInfo));
	}

	@Then("^the url of the page should be redirected to \"([^\"]*)\"$")
	public void the_url_of_the_page_should_be_redirected_to(String url) throws Throwable {
	    Assert.assertTrue(sut.getDriver().getCurrentUrl().contains(url));
	}

	@Then("^a logout button should be visible on page$")
	public void a_logout_button_should_be_visible_on_page() throws Throwable {
		Assert.assertTrue(myAccountPage.verifySignOutLink());
	}

	@Then("^the user name should be displayed as \"([^\"]*)\"$")
	public void the_user_name_should_be_displayed_as(String username) throws Throwable {
		Assert.assertEquals(username, myAccountPage.getUserName());
	}

	@When("Navigates to women store")
	public void navigates_to_women_store() {
	    storePage = myAccountPage.openWomenStore();
	}


	
}
