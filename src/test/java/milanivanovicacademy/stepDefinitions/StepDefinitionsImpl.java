package milanivanovicacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import milanivanovicacademy.TestComponents.BaseTest;
import milanivanovicacademy.pageobjects.CartPage;
import milanivanovicacademy.pageobjects.CheckoutPage;
import milanivanovicacademy.pageobjects.ConfirmationPage;
import milanivanovicacademy.pageobjects.LandingPage;
import milanivanovicacademy.pageobjects.ProductCatalogue;

public class StepDefinitionsImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecomerce Page")
	public void I_landed_on_Ecomerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue= landingPage.loginApplication(username, password);
	}
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
CartPage cartPage =productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage =  checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmMess = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMess.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string}. message is displayed")
	public void message_is_displayed(String string){
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMess());
		driver.close();
	}
}
