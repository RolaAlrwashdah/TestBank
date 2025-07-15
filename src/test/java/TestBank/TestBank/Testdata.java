package TestBank.TestBank;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testdata {
	
	
	WebDriver driver = new ChromeDriver();

	String Website = "https://globalsqa.com/angularJs-protractor/BankingProject/#/login";
   
	Random rand = new Random();
	String[] firstName = { "Rola", "lena", "renad", "omar" };
	String[] lastName = { "Ahmad", "sleem", "Khaled", "jad" };
	String[] PostCode = { "123", "321", "78", "99" };

	int randomFirstName = rand.nextInt(firstName.length);
	int randomLastName = rand.nextInt(lastName.length);
	int randomPostCode = rand.nextInt(PostCode.length);
	
	
	public void setUp() {
		
		driver.get(Website);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		
		
		
		
	}

}
