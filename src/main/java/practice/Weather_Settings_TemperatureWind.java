package practice;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.Weather;
import resources.Base;

public class Weather_Settings_TemperatureWind extends Base{
	
		
		/* Summary: Purpose of the TC is to check if temperature and wind speed is selected by default correct
		 * Step 1: Check if selected by default temperature is Celsius
		 * Expected result: Selected by default temperature is Celsius
		 * Step 2: Check if selected by default wind speed are Miles per hour
		 * Expected result: Selected by default wind speed are Miles per hour
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
	
	
	@Test
	
	public void tempWindDefault() throws IOException, InterruptedException{
		
		Weather wr = new Weather(driver);
		String expTempScale = "Celsius";
		String expWindSpeed = "Miles per hour";
		
		WebElement temperature = wr.getTempList();
		WebElement wind = wr.getWindList();
		
		//Step 1: Check if selected by default temperature is Celsius
		Assert.assertEquals(expTempScale, temperature.getText());
		log.info("Selected by default temperature is " + expTempScale);
		
		//Step 2: Check if selected by default wind speed are Miles per hour
		Assert.assertEquals(expWindSpeed, wind.getText());
		log.info("Selected by default wind speed are " + expWindSpeed);
		
	}	
	
	@AfterTest
	
	public void teardown(){
		driver.close();
		driver = null;
	}

}
