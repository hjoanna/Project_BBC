package practice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.TV_Schedule;
import resources.Base;

public class TV_Schedule_regionAnotherDay extends Base {

	/*
	 * Summary: Purpose of the TC is to check if schedule for a chosen region is displayed for another day correct and navigation back to 'TODAY' schedule is also correct 
	 * Step 1: Go to the link 'See other regional BBC World News variations' and click 
	 * Expected result: Web page is going to the bottom, where you can choose regions for appropriate schedule  
	 * Step 2: Click a region and check if you are redirected to the region schedule eg. for Africa you should be redirected to 'Africa Schedule' page 
	 * Expected result: Region link is clicked and you are redirected to region schedule which is visible on the top of the page in the schema: 'region name + Schedule' 
	 * Step 3: Click on another forward day schedule for the region and then navigate back to 'TODAY' schedule - check if navigation back is working correct 
	 * Expected result: Navigation back is working correct - after choosing another forward day schedule and going back, 'TODAY' schedule is visible
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

	@Parameters({
		"indexRegion",
		"indexDay"
	})
	
	@Test
	public void regionsNavigation(int indexRegion, int indexDay) throws IOException, InterruptedException {
		
		// checking if indexRegion and indexDay are in range
		boolean range = false;
		int maxRange = 7;
		
		if ((indexRegion > 0) && (indexRegion <= maxRange)){
			range = true;
			if ((indexDay > 0) && (indexDay <= maxRange)){
				range = true;
			} else {
				range = false;
				log.info("Given index day is incorrect. It should be between 1 and " + maxRange);
			}
		} else {
			range = false;
			log.info("Given index region is incorrect. It should be between 1 and " + maxRange);
		}
		Assert.assertTrue(range);

		

		TV_Schedule tv = new TV_Schedule(driver);
		List<WebElement> regions = tv.getRegions();
		
		//Step 1: Go to the link 'See other regional BBC World News variations' and click 
		tv.getOtherRegionsLink().click();
		
		//Step 2: Click a region and check if you are redirected to the region schedule eg. for Africa you should be redirected to 'Africa Schedule' page 
		String regionName = regions.get(indexRegion-1).getText();	
		Assert.assertEquals("The region you have chosen is already selected so the test can't continue", "a", regions.get(indexRegion-1).findElement(By.className("box-link")).getTagName());		
		regions.get(indexRegion-1).click();
		Assert.assertEquals(regionName + " Schedule", tv.getRegionScheduleTitle().getText());
		log.info("Successfully open the link for: " + tv.getRegionScheduleTitle().getText());
		
		//Step 3: Click on another forward day schedule for the region and then navigate back to 'TODAY' schedule - check if navigation back is working correct 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class, 'page--current')]/following-sibling::li[" + indexDay + "]")).click();
		driver.navigate().back();
		Assert.assertEquals("TODAY", driver.findElement(By.cssSelector("[class*='page--current'] .date-list__item-line1")).getText());
		log.info("Successfully present schedule for " + indexDay + ". day after today and navigate back to today's schedule");
	}	

	@AfterTest

	public void teardown() {
		driver.close();
		driver = null;
	}

}
