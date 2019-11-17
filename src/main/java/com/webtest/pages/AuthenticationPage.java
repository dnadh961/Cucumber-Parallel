package com.webtest.pages;

import org.openqa.selenium.By;

import com.webtest.SUT;

public class AuthenticationPage extends BasePage {

	public By signUp_emailAddress = By.id("email_create");
	public By createAnAccount = By.id("SubmitCreate");
	public By title_mr = By.cssSelector("label[for='id_gender1']");
	public By title_mrs = By.cssSelector("label[for='id_gender2']");
	public By firstName = By.id("customer_firstname");
	public By lastName = By.id("customer_lastname");
	public By signUp_Password = By.id("passwd");
	public By day_DOB = By.id("days");
	public By month_DOB = By.id("months");
	public By year_DOB = By.id("years");
	public By company = By.id("company");
	public By address1 = By.id("address1");
	public By address2 = By.id("address2");
	public By city = By.id("city");
	public By state = By.id("id_state");
	public By zipcode = By.id("postcode");
	public By additional_Info = By.id("other");
	public By home_Phone = By.id("phone");
	public By mobile = By.id("phone_mobile");
	public By addressAlias = By.id("alias");
	public By register = By.id("submitAccount");
	public By login_EmailID = By.id("email");
	public By login_Password = By.id("passwd");
	public By login_Submit = By.id("SubmitLogin");

	public AuthenticationPage(SUT sut) {
		super(sut);
	}

	public void CreateAnAccount(String emailAddress)
	{
		findElement(signUp_emailAddress).sendKeys(emailAddress);
		findElement(createAnAccount).click();
	}
	
	public void fillPersonalInfo(String title, String fname, String lName, String pwd, String d_dob, String m_dob, String y_dob)
	{
		if(title.equalsIgnoreCase("mr"))
		{
			findElement(title_mr).click();
		}
		else
		{
			findElement(title_mrs).click();
		}		
		findElement(firstName).sendKeys(fname);
		findElement(lastName).sendKeys(lName);
		findElement(signUp_Password).sendKeys(pwd);		
		findSelect(day_DOB).selectByValue(d_dob);
		findSelect(month_DOB).selectByValue(m_dob);
		findSelect(year_DOB).selectByValue(y_dob);
	}
	
	
	public void fillAddress(String company,String add1,String add2,String city,String state,String zip,String addtl_info,String homePhone,String mobile,String alias)
	{
		findElement(this.company).sendKeys(company);
		findElement(address1).sendKeys(add1);
		findElement(address2).sendKeys(add2);
		findElement(this.city).sendKeys(city);
		findSelect(this.state).selectByVisibleText(state);
		findElement(zipcode).sendKeys(zip);
		findElement(additional_Info).sendKeys(addtl_info);
		findElement(home_Phone).sendKeys(homePhone);
		findElement(this.mobile).sendKeys(mobile);
		findElement(addressAlias).sendKeys(alias);	
	}
	
	public MyAccountPage register()
	{
		findElement(register).click();
		return new MyAccountPage(sut);
	}
	
	public MyAccountPage login(String email, String password)
	{
		findElement(login_EmailID).sendKeys(email);
		findElement(login_Password).sendKeys(password);
		findElement(login_Submit).click();
		return new MyAccountPage(sut);
	}
	
	

}
