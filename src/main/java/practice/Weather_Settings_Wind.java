package practice;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.Weather;
import resources.Base;

public class Weather_Settings_Wind extends Base{
		
		/* Summary: Purpose of the TC is to check if Miles per hour calculation to Kilometres and back is correct
		 * Step 1: Type any city name and search
		 * Expected result: Entered city is selected with its temperature and wind speed 
		 * Step 2: Next to 'Wind speed' click on Miles per hour and from the list select Kilometres - check if wind speed is calculated from Miles per hour to Kilometres correct
		 * Expected result: Kilometres on the list are selected and wind speed is calculated from Miles per hour to Kilometres
		 * Step 3: Next to 'Wind speed' click on Kilometres and from the list select Miles per hour - check if wind speed is calculated from Kilometres to Miles per hour correct
		 * Expected result: Miles per hour on the list are selected and wind speed is calculated from Kilometres to Miles per hour
		 */		

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {
		log = createLogger();
		driver = initializeDriver();
		driver.get(prop.getProperty("urlWeather"));
		Base.acceptCookies(driver);
	}
	
	
	@BeforeMethod
	
	public void nameBefore(Method method) {
	    log.info("Test name: " + method.getName());       
	}
	
	@Parameters({
		"cityName"
	})
	
	@Test
	
	public void windSpeedList(String cityName) throws IOException, InterruptedException{
		
		Weather wr = new Weather(driver);
		
		//Step 1: Type any city name and search 
		wr.getSearchCity().sendKeys(cityName);
		Thread.sleep(2000);
		wr.getSearchCity().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		
		// Step 2: Next to 'Wind speed' click on Miles per hour and from the list select Kilometres - check if wind speed is calculated from Miles per hour to Kilometres correctly
		
		//get wind speed in miles
		String milesName = wr.getWindMph().getText();
		int milesNameLength = milesName.length();
		double milesValue = Double.parseDouble(milesName.substring(0, milesNameLength-15));
		double milesToKilometresValueRound = Math.round(milesValue * 1.609344);
	
		//change Miles to Kilometres
		wr.getWindList().click();
		wr.getWindListKph().click();
		
		//get wind speed in Kilometres
		String kilometresName = wr.getWindKph().getText();
		int kilometresNameLength = kilometresName.length();
		double kilometresValue = Double.parseDouble(kilometresName.substring(0, kilometresNameLength-20));
		
		// for some values calculation is not correct (?!)
		Assert.assertEquals("Wind speed in Miles per hour is not correctly calculated to Kilometres", milesToKilometresValueRound, kilometresValue);
		log.info("Wind speed is calculated from Kilometres to Miles per hour correct");
		
		/* Step 3: Next to 'Wind speed' click on Kilometres and from the list select Miles per hour
		 * Expected result: Miles per hour on the list are selected and wind speed is calculated from Kilometres to Miles per hour
		 * Actual result: it's not possible to click and select 'Miles per hour' as it's already selected, next to 'Wind speed' label it's still visible 'Kilometres per hour'
		 * It's possible only to change wind speed from Miles per hour to Kilometres per hour but not back to Miles per hour.
		 */
		
	}	
	
	@AfterTest
	
	public void teardown(){
		driver.close();
		driver = null;
	}

}
