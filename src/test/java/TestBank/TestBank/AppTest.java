package TestBank.TestBank;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends Testdata {

	@BeforeTest
	public void mySetUp() {
		setUp();
	}

	@Test(priority = 1)
	public void addCustomer() throws InterruptedException {

		WebElement bankMangerLogin = driver.findElement(By.xpath("//button[@ng-click='manager()']"));
		bankMangerLogin.click();

		// AddCustomer
		WebElement addCustomer = driver.findElement(By.xpath("//button[@ng-click='addCust()']"));
		addCustomer.click();

		// full customer details

		WebElement firstNameInput = driver.findElement(By.xpath("//input[@ng-model='fName']"));

		firstNameInput.sendKeys(firstName[randomFirstName]);

		WebElement lastNameInput = driver.findElement(By.xpath("//input[@ng-model='lName']"));
		lastNameInput.sendKeys(lastName[randomLastName]);

		WebElement postCodeInput = driver.findElement(By.xpath("//input[@ng-model='postCd']"));

		postCodeInput.sendKeys(PostCode[randomPostCode]);

		WebElement addCustomerButton = driver.findElement(By.cssSelector(".btn.btn-default"));
		addCustomerButton.click();
		driver.switchTo().alert().accept();

		// open account for customer that added randomly
		WebElement openAccountButton = driver.findElement(By.xpath("//button[@ng-class='btnClass2']"));
		openAccountButton.click();

		List<WebElement> customerList = driver.findElements(By.id("userSelect"));

		Select customerDropDown = new Select(customerList.get(0));

		List<WebElement> dropdownOption = customerDropDown.getOptions();

		customerDropDown.selectByIndex(dropdownOption.size() - 1);

		// select the currency to be randomly selected
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement currencyElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("currency")));

		Select currencyDropDown = new Select(currencyElement);
		List<WebElement> currencyOptions = currencyDropDown.getOptions();

		Random rand = new Random();
		int randomIndex = rand.nextInt(currencyOptions.size());

		currencyDropDown.selectByIndex(randomIndex);

		WebElement process = driver.findElement(By.xpath("//button[@type='submit']"));
		process.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

		// go to the customers and delete that customer create one assertion that the
		// customer is deleted

		WebElement customerbtn = driver.findElement(By.xpath("//button[@ng-class='btnClass3']"));

		customerbtn.click();

		WebElement customerSearch = driver.findElement(By.xpath("//input[@placeholder='Search Customer']"));
		customerSearch.sendKeys(firstName[randomFirstName]);

		WebElement deleteBtn = driver.findElement(By.xpath("//button[@ng-click='deleteCust(cust)']"));
		deleteBtn.click();

		// add the customer again
		WebElement addCustomerBtnAgain = driver.findElement(By.xpath("//button[@ng-class='btnClass1']"));
		addCustomerBtnAgain.click();

		WebElement firstNameAgain = driver.findElement(By.xpath("//input[@ng-model='fName']"));

		firstNameAgain.sendKeys(firstName[randomFirstName]);

		WebElement lastNameAgain = driver.findElement(By.xpath("//input[@ng-model='lName']"));
		lastNameAgain.sendKeys(lastName[randomLastName]);

		WebElement postCodeAgain = driver.findElement(By.xpath("//input[@ng-model='postCd']"));

		postCodeAgain.sendKeys(PostCode[randomPostCode]);

		WebElement addCustomerButtonAgain = driver.findElement(By.cssSelector(".btn.btn-default"));
		addCustomerButtonAgain.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

		// Verify that the customer is added again
		WebElement customerbtnAgain = driver.findElement(By.xpath("//button[@ng-class='btnClass3']"));

		customerbtnAgain.click();

		WebElement customerTable = driver.findElement(By.cssSelector(".table tbody"));
		Assert.assertTrue(customerTable.getText().contains(firstName[randomFirstName]));

	}

	@AfterTest
	public void afterTest() {

	}

}
