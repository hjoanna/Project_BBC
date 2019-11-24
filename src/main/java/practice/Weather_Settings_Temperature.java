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

public class Weather_Settings_Temperature extends Base{
	
		
		/* Summary: Purpose of the TC is to check if Celsius calculation to Fahrenheit and back is correct
		 * Step 1: Type any city name and search
		 * Expected result: Entered city is selected with its temperature and wind speed 
		 * Step 2: Next to 'Temperature' click on Celsius and from the list select Fahrenheit - check if temperature is calculated from Celsius to Fahrenheit correct
		 * Expected result: Fahrenheit on the list is selected and temperature is calculated from Celsius to Fahrenheit
		 * Step 3: Next to 'Temperature' click on Fahrenheit and from the list select Celsius - - check if temperature is calculated from Fahrenheit to Celsius correct
		 * Expected result: Celsius on the list is selected and temperature is calculated from Fahrenheit to Celsius
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
	
	public void temperatureList(String cityName) throws IOException, InterruptedException{

		Weather wr = new Weather(driver);
		
		//Step 1: Type any city name and search 
		wr.getSearchCity().sendKeys(cityName);
		Thread.sleep(2000);
		wr.getSearchCity().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		//Step 2: Next to 'Temperature' click on Celsius and from the list select Fahrenheit - check if temperature is calculated from Celsius to Fahrenheit correct
		
		//get temperature in Celsius
		String celsiusName = wr.getTempCelsius().getText();
		int celsiusNameLength = celsiusName.length();
		double celsiusValue = Double.parseDouble(celsiusName.substring(0, celsiusNameLength-1));
		double celsiusToFahrenheitValueRound = Math.round(((9 * celsiusValue) / 5) + 32);
	
		//change on the list Celsius to Fahrenheit
		wr.getTempList().click();
		wr.getTempListFahrenheit().click();
		
		//get temperature in Fahrenheit
		String fahrenheitName = wr.getTempFahrenheit().getText();
		int fahrenheitNameLength = fahrenheitName.length();
		double fahrenheitValue = Double.parseDouble(fahrenheitName.substring(0, fahrenheitNameLength-1));
		
		Assert.assertEquals("Celsius temperature is not correctly calculated to Fahrenheit", celsiusToFahrenheitValueRound, fahrenheitValue);
		log.info("Temperature is calculated from Celsius to Fahrenheit correct");
		
		/* Step 3: Next to 'Temperature' click on Fahrenheit and from the list select Celsius
		 * Expected result: Celsius on the list is selected and temperature is calculated from Fahrenheit to Celsius
		 * Actual result: it's not possible to click and select Celsius as it's already selected, next to Temperature label it's still visible 'Fahrenheit'.
		 * It's possible only to change temperature scale from Celsius to Fahrenheit but not back to Celsius. 
		 */
		
	}	
	
	@AfterTest
	
	public void teardown(){
		driver.close();
		driver = null;
	}

}
