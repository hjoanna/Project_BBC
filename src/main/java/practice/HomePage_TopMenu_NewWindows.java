package practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.Base;

public class HomePage_TopMenu_NewWindows extends Base{
		
		/* Summary: Purpose of the TC is to check if all links from the main menu open in separate tabs and have correct titles
		 * Step 1: Open all links from the main menu in separate tabs and check if number of open links in new tabs is the same as number of links in main menu
		 * Expected result: Number of open links in new tabs is the same as number of links in main menu
		 * Step 2: Check if titles of open tabs are as expected 
		 * Expected result: Titles of open tabs are as expected
		 */

	
	@BeforeTest

	public void initialize() throws IOException, InterruptedException {
		log = createLogger();
		driver = initializeDriver();
		driver.get(prop.getProperty("urlHome"));
		Base.acceptCookies(driver);
	}
	
	
	
	@BeforeMethod
	
	public void nameBefore(Method method) {
	    log.info("Test name: " + method.getName());       
	}
	
	@Parameters({
		"tabName1",
		"tabName2",
		"tabName3",
		"tabName4",
		"tabName5",
		"tabName6",
		"tabName7",
		"tabName8",
		"tabName9",
		"tabName10",
		"tabName11",
		"tabName12"
		})
	
	@Test
	
	public void mainMenuTabs(String tabName1, String tabName2, String tabName3, String tabName4, String tabName5,
			String tabName6, String tabName7, String tabName8, String tabName9, String tabName10,
			String tabName11, String tabName12) throws AWTException, InterruptedException, IOException{
		
		driver.manage().window().maximize();

		HomePage hp = new HomePage(driver);
		Actions a = new Actions(driver);
		Robot robot = new Robot();
		int expCount = hp.getTopMenuAll().size()-1;
		List<String> actTitles = new ArrayList<String>();
		List<String> expTitles = Arrays.asList(tabName1, tabName2, tabName3, tabName4, tabName5, tabName6 ,tabName7,
				tabName8, tabName9, tabName10, tabName11, tabName12);  

		// open all visible links
		List<WebElement> topMenuVisible = hp.getTopMenuVisibleAll();
		for (int i = 0; i < topMenuVisible.size() - 1; i++) {
			a.contextClick(topMenuVisible.get(i)).perform();
			Base.newTab(robot);
		}
		// 'More' click
		hp.getTopMenuMore().click();
		
		// open links that are visible only after 'More' click
		int siblings = hp.getTopMenuVisibleSiblings().size();
		a.contextClick(hp.getTopMenuFirstVisible()).perform();
		Base.newTab(robot);
		for (int i = 0; i < siblings; i++){
			a.contextClick(hp.getTopMenuVisibleSiblings().get(i)).perform();
			Base.newTab(robot);
		}
	
		// go through all new open tabs, take the titles and count
		Set<String> wh = driver.getWindowHandles();
		Iterator<String> it = wh.iterator();
		int actCount = 0;
		driver.switchTo().window(it.next());
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			actTitles.add(driver.getTitle());
			actCount ++;
		}

		Collections.sort(expTitles);
		Collections.sort(actTitles);
		
		Assert.assertEquals(actCount, expCount);
		log.info("Number of open links in new tabs is the same as number of links in main menu");
		Assert.assertEquals(actTitles, expTitles);
		log.info("Titles of open tabs are as expected");

	}

	@AfterTest
	
	public void teardown(){
		driver.quit();
		driver = null;
	}

}
