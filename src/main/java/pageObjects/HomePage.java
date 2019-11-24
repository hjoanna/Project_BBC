package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	

	
	By topMenuAll = By.cssSelector("#orb-nav-links > ul > li");
	By topMenuVisibleAll = By.cssSelector("#orb-nav-links [class*='orb']:not([class*='hide'])");
	By topMenuMore = By.id("orb-nav-more");
	By topMenuFirstVisible = By.xpath("//*[contains(@class,'visible')]");
	By topMenuVisibleSiblings = By.xpath("//*[contains(@class,'visible')]/following-sibling::li");

	
	public List<WebElement> getTopMenuAll() {
		return driver.findElements(topMenuAll);
	}
	
	public List<WebElement> getTopMenuVisibleAll() {
		return driver.findElements(topMenuVisibleAll);
	}
	
	public WebElement getTopMenuMore() {
		return driver.findElement(topMenuMore);
	}
	
	public WebElement getTopMenuFirstVisible() {
		return driver.findElement(topMenuFirstVisible);
	}
	
	public List<WebElement> getTopMenuVisibleSiblings() {
		return driver.findElements(topMenuVisibleSiblings);
	}

}
