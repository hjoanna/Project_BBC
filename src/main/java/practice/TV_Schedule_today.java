package practice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.TV_Schedule;
import resources.Base;

public class TV_Schedule_today extends Base {

	/*
	 * Summary: Purpose of the TC is to check if the title and date presented in 'TODAY' section is correct 
	 * Step 1: Go to 'TODAY' section and check if the title is correct 
	 * Expected result: 'TODAY' section has correct title 'TODAY' 
	 * Step 2: Go to 'TODAY' section and check if the date presented is the same as current date 
	 * Expected result: The date presented is the same as current date
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

	public void todaySchedule() throws IOException, InterruptedException {
		
		TV_Schedule tv = new TV_Schedule(driver);

		// Step 1: Go to 'TODAY' section and check if the title is correct
		String actDay = tv.getTodayName().getText();
		String expDay = "TODAY";
		Assert.assertEquals(expDay, actDay);
		log.info("'TODAY' is presented correct");
		
		// Step 2: Go to 'TODAY' section and check if the date presented is the same as current date
		String actDate = tv.getDateName().getText();
		Date date = new Date();
		System.out.println(date);
		String dateTable[] = date.toString().split(" ");
		if (dateTable[2].startsWith("0")) {
			dateTable[2] = String.valueOf(dateTable[2].charAt(1));
		}
		dateTable[1] = dateTable[1].toUpperCase();
		String expDate = dateTable[2] + " " + dateTable[1];
		Assert.assertEquals(expDate, actDate);
		log.info("The date presented is the same as current date");

	}

	@AfterTest

	public void teardown() {
		driver.close();
		driver = null;
	}

}
