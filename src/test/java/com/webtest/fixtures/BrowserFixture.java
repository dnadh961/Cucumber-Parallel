package com.webtest.fixtures;

import com.google.inject.Inject;
import com.webtest.Browser;
import com.webtest.SUT;
import com.webtest.pages.WelcomePage;

import cucumber.api.java.en.Given;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class BrowserFixture extends Browser{
	
private WelcomePage welcomePage;
	
	public WelcomePage getWelcomePage() {
		return welcomePage;
	}

	@Inject
	public BrowserFixture(SUT sut) {
		super(sut);
	}

	@Given("^User opens automation practice home page$")
	public void user_opens_warehouse_home_page() {
		welcomePage = open();
	}
	
}
