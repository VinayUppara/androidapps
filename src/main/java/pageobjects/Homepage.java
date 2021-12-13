package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.BaseClass;

public class Homepage  {


	public Homepage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[.='LOGIN']")
	public WebElement login;


	@FindBy(xpath="//a[@class='shopping_cart_link']")
	public WebElement cart;


	@FindBy(xpath="//button[text()='Add to cart']")
	public List<WebElement> addToCart;

	@FindBy(xpath="//button[text()='Remove']")
	public List<WebElement> removeFromCart;
	
	
	@FindBy(css=".product_sort_container")
	public WebElement filter;
	
	@FindBy(css="div.inventory_item_price")
	public List<WebElement> prices;
	
	
	@FindBy(xpath="//ul[@class='social']/li")
	public List<WebElement> socialLinks;
	
	

}
