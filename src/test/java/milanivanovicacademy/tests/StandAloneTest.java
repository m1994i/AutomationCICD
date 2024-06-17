package milanivanovicacademy.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import milanivanovicacademy.pageobjects.LandingPage;

public class StandAloneTest {
	public static void main(String[]args) {
		/*Opis zadatka: Potrebno je ulogovati se, uneti, username, password, 
		kliknuti na log in, zatim odabrati proizvod jaknu ZARA COAT 3 
		ali odabrati na taj nacin da se od svih proizvoda na stranici oznaci 
		samo jakna ZARA COAT 3. Nakon toga kliknuti na korpu, nakon toga kliknuti na "checkout".
		 SLedece je da se u polje "Select Country" unese zemlja "india", 
		 nakon sto se pojave rezultati pretrage klikne se na "india" a zatim na "Place Order".
		  Nakon toga se uzima tekst "THANKYOU FOR THE ORDER." i utvrdjuje se da li je on zaista prikazan na ekranu */
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//MIlan Ivanovic
		
		
		driver.findElement(By.id("userEmail")).sendKeys("milanivanovic@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Milanivanovic-1994");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));//Kreirana lista products od 3 proizvoda (jakna, patike i telefon)
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //sacekaj dok se ne pojavi tekst za dodavanje u korpu
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));//ceka se pojavljivanje tekst da je proizvod uspesno dodat u korpu
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();//klikne se na korpu
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));//pravi se lista webelemenata proizvoda koji su u korpi
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));//
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();//izvrsava se klik na "Checkout"
		
		Actions a = new Actions(driver); //pravi se objekat "a" zbog akcije koja sledi u sledecem kodu
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();//Koristi se objekat "a" za unos u polje pretrage Zemlje "India"
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));//nakon unosa kljucne reci "india" ceka se rezultat pretrage
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();//klikne se na "india"
		driver.findElement(By.cssSelector(".action__submit")).click();//klikne se na "Place Order"
		String confirmMess = driver.findElement(By.cssSelector(".hero-primary")).getText();//Uzimanje teksta THANKYOU FOR THE ORDER.
		Assert.assertTrue(confirmMess.equalsIgnoreCase("THANKYOU FOR THE ORDER."));//Postavlja se tvrdnja da li je promenljiva confirmMess jednaka stringu "THANKYOU FOR THE ORDER.", bez obzira na velika i mala slova.
		driver.close();
	}

}
