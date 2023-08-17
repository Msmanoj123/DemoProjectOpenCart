package Step;

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
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pom.pom;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenCartStep {
	public WebDriver driver;
	public WebDriverWait wait;
	public pom p;
	@Given("user is open the browser and pass url {string}")
	public void user_is_open_the_browser_and_pass_url(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.addArguments("--remote-allow-origins=*");
//		browserOptions.setPlatformName("Windows 11");
//		browserOptions.setBrowserVersion("114.0");
//		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
//		ltOptions.put("username", "manoj.ms");
//		ltOptions.put("accessKey", "ZSld2ErhAhqH59UNfjfAC4A1jBBLq1sJu6YIVR1bkKgwWW8KB7");
//		ltOptions.put("project", "DemoProjectOpenCart");
//		ltOptions.put("selenium_version", "4.4.0");
//		ltOptions.put("w3c", true);
//		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new ChromeDriver(browserOptions);
		driver.get(url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofMinutes(2));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().deleteAllCookies();
		 p = new pom(driver);
	}

	@When("user is click on login page")
	public void user_is_click_on_login_page() {
		wait.until(ExpectedConditions.elementToBeClickable(p.myaccount)).click();
		p.login.click();
	}

	@When("^Pass Uname (.*) And Password (.*)$")
	public void pass_uname_manojms_gmail_com_and_password_msmanoj(String email,String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(p.email)).sendKeys(email);
		p.password.sendKeys(password);
	}

	@When("click on login button")
	public void click_on_login_button() {
		p.submit.click();
	}

	@Then("validate user is myaccount page")
	public void validate_user_is_myaccount_page() throws WebDriverException, IOException {
		if(driver.getCurrentUrl().equals("https://awesomeqa.com/ui/index.php?route=account/account"))
		{
			System.out.println("test pass");
			wait.until(ExpectedConditions.elementToBeClickable(p.myaccount)).click();
			p.logout.click();
			driver.quit();
		}
		else
		{
			FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("C:\\Users\\manoj.ms\\eclipse-workspace\\DemoProjectOpenCart\\screenshot\\opencartvaliddata"+System.currentTimeMillis()+".png"));
			driver.quit();
		}
	}
	@Then("check proper error msg is displayed")
	public void check_proper_error_msg_is_displayed() throws WebDriverException, IOException {
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(p.error)).isDisplayed())
		{
			System.out.println("test pass");
			driver.quit();
		}
		else
		{
			FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("C:\\Users\\manoj.ms\\eclipse-workspace\\DemoProjectOpenCart\\screenshot\\opencartinvaliddata"+System.currentTimeMillis()+".png"));
			driver.quit();

		}
	}
	@When("login with {string} and {string}")
	public void login_with_and(String UN, String PWD) {
		wait.until(ExpectedConditions.elementToBeClickable(p.myaccount)).click();
		p.login.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(p.email)).sendKeys(UN);
		p.password.sendKeys(PWD);
		p.submit.click();
	}

	@When("Add {string} item to cart")
	public void add_item_to_cart(String pname) {
		wait.until(ExpectedConditions.elementToBeClickable(p.category)).click();
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".caption>h4>a")));
		for(WebElement prod:products)
		{
			if(prod.getText().equalsIgnoreCase(pname))
			{
				prod.click();
				wait.until(ExpectedConditions.elementToBeClickable(p.addcart)).click();
				break;
			}
		}
	}
	@Then("valid the {string} item is present in cart")
	public void valid_the_item_is_present_in_cart(String pname) {
		p.shoppingcart.click();
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".table-responsive>.table.table-bordered>tbody>tr>td:nth-child(2)>a")));
		for(WebElement prod:products)
		{
			if(prod.getText().equalsIgnoreCase(pname))
			{
				System.out.println("correct item is added");
				break;
			}
		}
		driver.quit();
	}
}