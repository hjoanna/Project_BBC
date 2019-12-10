package practice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.Weather;
import resources.Base;

public class Weather_Settings_Language extends Base {

	/*
	 * Summary: Purpose of the TC is to check if selected by default language is
	 * English, list of languages contains all required languages and if there
	 * are selected appropriate languages Step 1: Go to the bottom of the page
	 * and check if selected by default language is English Expected result:
	 * Selected by default language is English Step 2: Click on the field with
	 * languages list next to 'Language' Expected result: Pop up list with
	 * possible languages is presented Step 3: Check if all language names are
	 * the same as expected Expected result: All language names are the same as
	 * expected Step 4: Close the pop up language list Expected result: The pop
	 * up language list is closed Step 5: Click on the language list and select
	 * each language one by one starting from the bottom and check if clicked
	 * language is selected Expected result: The language which is clicked is
	 * visible as selected
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

	@Parameters({ "langName1", "langName2", "langName3", "langName4" })

	@Test

	public void languageList(String langName1, String langName2, String langName3, String langName4)
			throws IOException, InterruptedException {

		Weather wr = new Weather(driver);
		String expDefaultLang = langName1;

		// Step 1: Go to the bottom of the page and check if selected by default language is English
		WebElement selectedLanguage = wr.getSelectedLanguage();
		String actDefaultLang = selectedLanguage.getText();
		Assert.assertEquals(expDefaultLang, actDefaultLang);
		log.info("Selected by default language is " + expDefaultLang);

		List<String> actLanguage = new ArrayList<String>();
		List<String> expLanguage = Arrays.asList(langName1, langName2, langName3, langName4);

		// Step 2: Click on the field with languages list next to 'Language'
		selectedLanguage.click();
		List<WebElement> languages = wr.getListLanguages();

		// create list of visible languages (replace the one with foreign name)
		for (int i = 0; i < languages.size(); i++) {
			String e = languages.get(i).getText();
			if (e.startsWith("G") && e.endsWith("idhlig")) {
				e = e.replace(Character.toString(e.charAt(1)), "*");
			}
			actLanguage.add(e);
		}

		Collections.sort(actLanguage);
		Collections.sort(expLanguage);

		// Step 3: Check if all language names are the same as expected
		Assert.assertEquals(actLanguage, expLanguage);
		log.info("All language names are the same as expected");

		// Step 4: Close the pop up language list

		wr.getListLanguagesClose().click();

		// Step 5: Click on the language list and select each language one by one starting from the bottom and check if clicked language is selected
		for (int i = languages.size() - 1; i >= 0; i--) {
			driver.findElement(By.cssSelector("[class*='language--selected']")).click();
			Thread.sleep(2000);
			String expSelectedLanguage = wr.getListLanguages().get(i).getText();
			wr.getListLanguages().get(i).click();
			String actSelectedLanguage = driver.findElement(By.cssSelector("[class*='language--selected']")).getText();
			Assert.assertEquals(expSelectedLanguage, actSelectedLanguage);
		}
		log.info("The language which is clicked on the pop up list is selected");

	}

	@AfterTest

	public void teardown() {
		driver.close();
		driver = null;
	}

}
