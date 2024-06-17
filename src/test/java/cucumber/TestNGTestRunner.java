package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="milanivanovicacademy.stepDefinitions",monochrome=true,tags = "@Regression" ,plugin= {"html:target/cucumber.html"})
/*znacenje koda: Putanja do Cucumber feature fajlova(@CucumberOptions(features="src/test/java/cucumber"), Paket gde se nalaze step definicije (glue="milanivanovicacademy.stepDefinitions"),
printuj rezultate u citljivom formatu (monochrome=true) zatim generisi izvestaj HTML plagina(plugin= {"html:target/cucumber.html"})
koristimo takodje "tags" kada hocemo da pokrenemo pojedinacne testove*/
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
//(extends AbstractTestNGCucumberTests)automatski vrši konfiguraciju potrebnu za pokretanje Cucumber testova sa TestNG-om. To uključuje automatsko učitavanje Cucumber opcija koje ste definisali putem @CucumberOptions anotacije, kao što su putanje do feature fajlova, paketi za step definicije, filteri tagova, plugini za izveštavanje i drugo.
	
	

}
