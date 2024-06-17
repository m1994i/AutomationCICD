package milanivanovicacademy.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import milanivanovicacademy.TestComponents.BaseTest;
import milanivanovicacademy.pageobjects.CartPage;
import milanivanovicacademy.pageobjects.CheckoutPage;
import milanivanovicacademy.pageobjects.ConfirmationPage;
import milanivanovicacademy.pageobjects.LandingPage;
import milanivanovicacademy.pageobjects.OrderPage;
import milanivanovicacademy.pageobjects.ProductCatalogue;



public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	@Test(dataProvider= "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String>input) throws IOException {
		
		
		
		
		
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage =  checkoutPage.submitOrder();
		
		String confirmMess = confirmationPage.getConfirmationMessage();//Uzimanje teksta THANKYOU FOR THE ORDER.
		Assert.assertTrue(confirmMess.equalsIgnoreCase("THANKYOU FOR THE ORDER."));//Postavlja se tvrdnja da li je promenljiva confirmMess jednaka stringu "THANKYOU FOR THE ORDER.", bez obzira na velika i mala slova.
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderHistoryTest() {
		//ZARA COAT 3
		ProductCatalogue productCatalogue =landingPage.loginApplication("milanivanovic@gmail.com", "Milanivanovic-1994");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrdertDisplay(productName));
	}
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";//Vraca informaciju gde je screenshot sacuvan
	}
	
	//Extent Reports-
	
	
	@DataProvider //JSOn
	public Object[][] getData() throws IOException{
	
		List<HashMap<String, String>>data= getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\milanivanovicacademy\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)} };
		
		//@DataProvider
		//public Object[][]getData(){
		//return new Object[][]{{"milanivanovic@gmail.com","Milanivanovic-1994","product", "ZARA COAT 3" },{"ivanovic994@gmail.com","Ivanovic-1994","ADIDAS ORIGINAL"}}	
		
		/*	HashMap<String, String> map= new HashMap<String, String>();
		map.put("email", "milanivanovic@gmail.com");
		map.put("password","Milanivanovic-1994");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("email", "ivanovic994@gmail.com");
		map1.put("password","Ivanovic-1994");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
	}

}
