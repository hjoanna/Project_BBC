package practice;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.TV_Schedule;
import resources.Base;

public class TV_Schedule_tomorrow extends Base {
		
		/* Summary: Purpose of the TC is to check if link 'Tomorrow' leads to the section with next day schedule
		 * Step 1: Scroll page till 'Tomorrow' link is visible and click on it 
		 * Expected result: 'Tomorrow' link is visible, clicked and leads to another page
		 * Step 2: Check if section which is now as 'TODAY' has next day date
		 * Expected result: Section which is now as 'TODAY' has next day date
		 */

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {
		log = createLogger();
		driver = initializeDriver();
		driver.get(prop.getProperty("urlTV"));
		Base.acceptCookies(driver);
	}
	
	
	@BeforeMethod
	
	public void nameBefore(Method method) {
	    log.info("Test name: " + method.getName());       
	}
	
	
	@Test
	
	public void tomorrowLink() throws InterruptedException, IOException{
		
		TV_Schedule tv = new TV_Schedule(driver);
		
		String expTomorrow = tv.getTomorrowDate().getText();
		
		//Step 1: Scroll page till 'Tomorrow' link is visible and click on it 
		WebElement tomorrow = tv.getTomorrowLink();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tomorrow);
		Thread.sleep(1000);
		tomorrow.click();		
		
		//Step 2: Check if section which is now as 'TODAY' has next day date
		String actTomorrow = tv.getTodayDateName().getText();
		Assert.assertEquals(expTomorrow, actTomorrow);
		log.info("Section which is now as 'TODAY' after 'Tomorrow' was clicked - has next day date");		
	}

	
	@AfterTest
	
	public void teardown(){
		driver.close();
		driver = null;
	}
	
}
