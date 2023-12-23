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

public class CreatingAccountAndSignInAndAddItems extends parameters {

	@BeforeTest
	public void SetUp() {
		TheSetUpOfTheWebsite();
	}

	@Test(priority = 1)
	public void SignIn() {
		SignInProcess();
	}

	@Test(priority = 2)
	public void LogIn() {
		LogInProcess();
	}

	@Test(priority = 3)
	public void AddingItems() throws InterruptedException {
		AddingThreeItemsFromTheWomenSctionAndAssertation();

	}
}
