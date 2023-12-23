package MagnetAddingItems;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class parameters {
	WebDriver driver = new ChromeDriver();
	String WebSite = "https://magento.softwaretestingboard.com/";
	String TheFirstName="Whatever";
	String TheLastName="Whatever";
	String EamilAddress="whatever@gmail.com";
	String PasswordCode="RandomPassword";
	Random rand = new Random();
	
	@BeforeTest
	public void TheSetUpOfTheWebsite() {
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}
	@Test(priority = 1)
	public void SignInProcess() {
		WebElement CreateAccount = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[3]/a"));
		CreateAccount.click();
		WebElement FirtsName = driver.findElement(By.id("firstname"));
		FirtsName.sendKeys(TheFirstName);
		WebElement LastName = driver.findElement(By.id("lastname"));
		LastName.sendKeys(TheLastName);
		WebElement Eamil = driver.findElement(By.name("email"));
		Eamil.sendKeys(EamilAddress);
		WebElement Password = driver.findElement(By.id("password"));
		Password.sendKeys(PasswordCode);
		WebElement ConfirmPassword = driver.findElement(By.name("password_confirmation"));
		ConfirmPassword.sendKeys(PasswordCode);
		WebElement CreateButton = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button"));
		CreateButton.click();
		WebElement TheLogo = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/a/img"));
		TheLogo.click();
	}
	@Test(priority = 2)
	public void LogInProcess() {
		WebElement LogIn = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
		LogIn.click();
		WebElement Eamil = driver.findElement(By.name("login[username]"));
		Eamil.sendKeys(EamilAddress);
		WebElement Password = driver.findElement(By.name("login[password]"));
		Password.sendKeys(PasswordCode);
		WebElement LogInButton = driver.findElement(By.xpath("//*[@id=\"send2\"]/span"));
		LogInButton.click();
	}
	@Test(priority = 3)
	public void AddingThreeItemsFromTheWomenSctionAndAssertation() throws InterruptedException {
	WebElement WomenSection = driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]/span[2]"));
	WomenSection.click();
	WebElement Tees = driver
			.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[2]/div[2]/div/ul[1]/li[3]/a"));
	Tees.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)");
	List<WebElement> TheItems = driver.findElements(By
			.xpath("//ol[@class=\"products list items product-items\"]/li[@class=\"item product product-item\"]"));
	for (int i = 0; i < 3; i++) {
		WebElement Item = TheItems.get(i);
		Item.click();
		List<WebElement> Sizes = driver
				.findElements(By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div/div"));
		int RandomSize = rand.nextInt(Sizes.size());

		WebElement ChooseTheSize = Sizes.get(RandomSize);
		ChooseTheSize.click();
		List<WebElement> Colors = driver
				.findElements(By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div"));
		int RandomColors = rand.nextInt(Colors.size());

		WebElement Color = Colors.get(RandomColors);
		Color.click();
		WebElement AddCart = driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span"));
		AddCart.click();
		Thread.sleep(3000);
		driver.navigate().back();
		System.out.println(i);
		TheItems = driver.findElements(By.xpath(
				"//ol[@class=\"products list items product-items\"]/li[@class=\"item product product-item\"]"));

	}
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	js1.executeScript("window.scrollBy(0,-500)");
	WebElement CartCounter = driver.findElement(By.xpath("//span[@class=\"counter-number\"]"));
	int NumberOfItems = Integer.parseInt(CartCounter.getText());
	Assert.assertEquals(3, NumberOfItems);

}

}
