package milanivanovicacademy.tests;
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;
import milanivanovicacademy.TestComponents.Retry;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import milanivanovicacademy.TestComponents.BaseTest;
import milanivanovicacademy.pageobjects.CartPage;
import milanivanovicacademy.pageobjects.CheckoutPage;
import milanivanovicacademy.pageobjects.ConfirmationPage;
import milanivanovicacademy.pageobjects.LandingPage;
import milanivanovicacademy.pageobjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest{
	
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		
		
		landingPage.loginApplication("milanivanovic@gmail.com", "Milanivanovic-19943");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMess());
		
		
	}
	@Test(retryAnalyzer=Retry.class)
	public void ProductErrorValidation() throws IOException {
		
		
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue =landingPage.loginApplication("milanivanovic@gmail.com", "Milanivanovic-1994");
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}

}
