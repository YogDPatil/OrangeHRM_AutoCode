package com.ui.stepdefination;

import org.testng.annotations.Listeners;

import com.ui.pages.LoginPage;
import com.ui.tests.TestBase;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Listeners(com.listners.UiListners.class)
public final class LoginPageStepDef extends StepDefTestBase{

	@Given("User is on login page")
	public void user_is_on_login_pahe() {

		getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

	}

	@When("User enters cred {string} and {string} and click on sign in button")
	public void user_enters_cred_and_click_on_sign_in_button(String id, String pass) {
		getLoginPageObject().enterCredAndLogin(id, pass);
	}

	@Then("Validate user navigate to {string} page")
	public void validate_user_navigate_to_page(String string) {
	}
}
