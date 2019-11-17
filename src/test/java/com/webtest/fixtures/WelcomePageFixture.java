package com.webtest.fixtures;

import com.google.inject.Inject;
import com.webtest.SUT;
import com.webtest.pages.AuthenticationPage;
import com.webtest.pages.WelcomePage;

import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class WelcomePageFixture{
	
	private WelcomePage welcomePage;
	private AuthenticationPage authenticationPage;
	private SUT sut;
	
	@Inject
	public WelcomePageFixture(SUT sut, BrowserFixture browserFixture) {
		this.sut = sut;
		welcomePage = browserFixture.getWelcomePage();
	}

	public AuthenticationPage getAuthenticationPage() {
		return authenticationPage;
	}
	
	@When("^user clicks on sign button$")
	public void user_clicks_on_sign_button() throws Throwable {
		authenticationPage = welcomePage.signIn();
	}


	
}
