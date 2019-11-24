package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TV_Schedule {
	
	public WebDriver driver;
		
	public TV_Schedule(WebDriver driver){
		this.driver = driver;
	}
	

	By regions = By.cssSelector("#outlets > ul > li");
	By otherRegionsLink = By.cssSelector(".grid-wrapper [class*='text--right']");
	By regionScheduleTitle = By.cssSelector(".beta");
	By todayDateName = By.cssSelector("[class*='page--current']");
	By todayName = By.cssSelector("[class*='page--current'] .date-list__item-line1");
	By dateName = By.cssSelector("[class*='page--current'] .date-list__item-line2");
	By tomorrowDate = By.cssSelector("[class*='page--current'] + li");
	By tomorrowLink = By.cssSelector(".tomorrow-bar");



	

	public List<WebElement> getRegions() {
		return driver.findElements(regions);
	}
	
	public WebElement getOtherRegionsLink(){
		return driver.findElement(otherRegionsLink);
	}
	
	public WebElement getRegionScheduleTitle(){
		return driver.findElement(regionScheduleTitle);
	}
	
	public WebElement getTodayName() {
		return driver.findElement(todayName);
	}

	public WebElement getDateName(){
		return driver.findElement(dateName);
	}
	
	public WebElement getTomorrowLink() {
		return driver.findElement(tomorrowLink);
	}
	
	public WebElement getTomorrowDate() {
		return driver.findElement(tomorrowDate);
	}
	
	public WebElement getTodayDateName() {
		return driver.findElement(todayDateName);
	}

}
