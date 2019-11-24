package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Weather {
	
	public WebDriver driver;
	
	public Weather(WebDriver driver){
		this.driver = driver;
	}
	

	
	By selectedLanguage = By.cssSelector("[class*='language--selected']");
	By listLanguages = By.cssSelector("[class*='popup--language'] ul > li");
	By listLanguagesClose = By.cssSelector(".wr-o-popup.wr-o-popup--language .wr-o-popup__close");
	By searchCity = By.id("ls-c-search__input-label");
	By tempCelsius = By.cssSelector(".wr-value--temperature.gel-trafalgar > span[class*='temperature--c'] > span");
	By tempList = By.xpath("//*[@data-item-type='temperature']");
	By tempListFahrenheit = By.cssSelector("[class*='popup__item--f']");
	By tempFahrenheit = By.cssSelector(".wr-value--temperature.gel-trafalgar > span[class*='temperature--f'] > span");
	By windMph = By.xpath("//*[@id='site-container']/div[6]/div/div/div[2]/div[3]/span[3]/span/span[@class='wr-value--windspeed wr-value--windspeed--mph']");
	By windList = By.xpath("//*[@data-item-type='windspeed']");	
	By windListKph = By.cssSelector("[class*='popup__item--kph']");
	By windKph = By.xpath("//*[@id='site-container']/div[6]/div/div/div[2]/div[3]/span[3]/span/span[@class='wr-value--windspeed wr-value--windspeed--kph']");


	
	public WebElement getSelectedLanguage() {
		return driver.findElement(selectedLanguage);
	}

	public List<WebElement> getListLanguages() {
		return driver.findElements(listLanguages);
	}
	
	public WebElement getListLanguagesClose() {
		return driver.findElement(listLanguagesClose);
	}
	
	public WebElement getSearchCity() {
		return driver.findElement(searchCity);
	}
	
	public WebElement getTempCelsius() {
		return driver.findElement(tempCelsius);
	}
	
	public WebElement getTempList() {
		return driver.findElement(tempList);
	}
	
	public WebElement getTempListFahrenheit() {
		return driver.findElement(tempListFahrenheit);
	}
	
	public WebElement getTempFahrenheit() {
		return driver.findElement(tempFahrenheit);
	}
	
	public WebElement getWindMph() {
		return driver.findElement(windMph);
	}
	
	public WebElement getWindList() {
		return driver.findElement(windList);
	}
	
	public WebElement getWindListKph() {
		return driver.findElement(windListKph);
	}
	
	public WebElement getWindKph() {
		return driver.findElement(windKph);
	}
}
