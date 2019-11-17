package com.webtest.fixtures;

import java.util.List;

import com.google.inject.Inject;
import com.webtest.SUT;
import com.webtest.pages.AuthenticationPage;
import com.webtest.pages.MyAccountPage;
import com.webtest.util.DataUtil;

import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class AuthenticationPageFixture {

	private AuthenticationPage authenticationPage;
	private MyAccountPage myAccountPage;
	private SUT sut;

	public MyAccountPage getMyAccountPage() {
		return myAccountPage;
	}

	@Inject
	public AuthenticationPageFixture(SUT sut, WelcomePageFixture wpfixture) {
		this.sut = sut;
		authenticationPage = wpfixture.getAuthenticationPage();
	}

	public AuthenticationPage getAuthenticationPage() {
		return authenticationPage;
	}

	@When("^logs in using already registered section with user id as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void logs_in_using_already_registered_section_with_user_id_as_and_password_as(String emailID,
			String password) throws Throwable {
		myAccountPage = authenticationPage.login(emailID, password);
	}

	@When("enters email id as \"([^\"]*)\" and clicks create an account")
	public void enters_email_id_as_and_clicks_create_an_account(String emailID) {
		String[] email = emailID.split("@");
		String randomID = DataUtil.getRandomNumberString(email[0]);
		try {
			authenticationPage.CreateAnAccount(randomID + "@"+email[1]);
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			aiobe.printStackTrace();
			throw new RuntimeException("Email ID provided is incorrect format");
		}
	}

	@When("fills all the personal information as below")
	public void fills_all_the_personal_information_as_below(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<List<String>> list = dataTable.asLists();
		List<String> inputs = list.get(1);

		authenticationPage.fillPersonalInfo(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4),
				inputs.get(5), inputs.get(6));

	}

	@When("fills all address details as below")
	public void fills_all_address_details_as_below(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<List<String>> list = dataTable.asLists();
		List<String> inputs = list.get(1);

		authenticationPage.fillAddress(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4),
				inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(8), inputs.get(9));
	}

	@When("clicks on register button")
	public void clicks_on_register_button() {
		myAccountPage = authenticationPage.register();
	}

}
