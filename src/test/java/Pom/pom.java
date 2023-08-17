package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pom {
	public WebDriver driver;
	public pom(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public By myaccount = By.cssSelector(".list-inline>li:nth-child(2)>a");
	@FindBy(css=".dropdown-menu.dropdown-menu-right>li:nth-child(2)>a")
	public WebElement login;
	public By email = By.xpath("//input[@id='input-email']");
public By heading = By.cssSelector("#content>h2:nth-child(1)");
	@FindBy(id="input-password")
	public WebElement password;
	@FindBy(xpath="//input[@type='submit']")
	public WebElement submit;
	@FindBy(css=".dropdown-menu.dropdown-menu-right>li:nth-child(5)>a")
	public WebElement logout;
	public By error = By.cssSelector(".alert.alert-danger.alert-dismissible");
	public By category = By.cssSelector(".nav.navbar-nav>li:nth-child(7)>a");
	public By addcart = By.xpath("//button[.='Add to Cart']");
		@FindBy(xpath="//a[.='shopping cart']")
		public WebElement shoppingcart;
	//	@FindBy(css="//input[@id='input-email']")
	//	public WebElement email;

}
