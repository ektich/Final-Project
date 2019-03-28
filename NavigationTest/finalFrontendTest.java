package NavigationTest;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;


public class finalFrontendTest {
	WebDriver driver;
	Wait<WebDriver> wait;
	String url="http://localhost:8081/html/index.html";
	
	@BeforeClass
	public void setupDriver() throws Exception {
		System.out.println("Test started at: "+LocalDateTime.now());
		System.out.println("For URL: "+url);
		System.out.println();
		System.setProperty("webdriver.chrome.driver","D:\\Workspace\\cs265\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait( driver, 5 );
		driver.get( url );
	}
	
	@AfterClass
	public void shutdown() {
		driver.quit();
	}
	
	
	//test add project
	@Test(timeOut=60000)
	public void test_Lecturer1_usecases1() {
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.partialLinkText("Add Projects")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ipTitle")));
		//test clean button
		driver.findElement(By.id("ipTitle")).sendKeys("this is usecases3");
		driver.findElement(By.id("clean_btn")).click();
		threadSleep(1000);
		driver.findElement(By.id("ipTitle")).sendKeys("this is usecases3");
		driver.findElement(By.id("ipLanguage")).sendKeys("this is usecases3");
		driver.findElement(By.id("ipSupervisor")).sendKeys(username);
		Select select1 = new Select(driver.findElement(By.id("ipDegree")));
		select1.getOptions().get(1).click();
		Select select2 = new Select(driver.findElement(By.id("ipLevel1")));
		select2.getOptions().get(1).click();
		Select select3 = new Select(driver.findElement(By.id("ipLevel2")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select3.getOptions().get(1), "data-id"));
		select3.getOptions().get(1).click();
		Select select4 = new Select(driver.findElement(By.id("ipLevel3")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select4.getOptions().get(1), "data-id"));
		select4.getOptions().get(1).click();
		driver.findElement(By.id("ipDraft")).click();
		driver.findElement(By.id("ipReference")).sendKeys("this is usecases3");
		driver.findElement(By.id("ipDescription")).sendKeys("this is usecases3");
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		threadSleep(2000);
	}
	
	//test edit project
	@Test(timeOut=60000)
	public void test_Lecturer1_usecases2() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("draft")));
		driver.findElement(By.id("draft"))
			.findElement(By.tagName("div"))
			.findElement(By.tagName("div"))
			.findElement(By.partialLinkText("Edit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ipDraft")));
		threadSleep(500);
		driver.findElement(By.id("ipTitle")).clear();
		threadSleep(500);
		driver.findElement(By.id("ipTitle")).sendKeys("this is edit test");
		threadSleep(500);
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		threadSleep(2000);
	}
	
	//test delete project
	@Test(timeOut=60000)
	public void test_Lecturer1_usecases3() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("draft"))
			.findElement(By.tagName("div"))
			.findElement(By.tagName("div"))
			.findElement(By.partialLinkText("Delete")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		threadSleep(2000);
		wait.until(ExpectedConditions.titleIs("Index Page"));
		logoutFunction();
	}
	
	//test student submit interest
	@Test(timeOut=60000)
	public void test_usecases_1() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
			
		wait.until(ExpectedConditions.titleIs("Index Page"));
		Select select2 = new Select(driver.findElement(By.id("ipLevel1")));
		select2.getOptions().get(1).click();
		Select select3 = new Select(driver.findElement(By.id("ipLevel2")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select3.getOptions().get(1), "data-id"));
		select3.getOptions().get(1).click();
		Select select4 = new Select(driver.findElement(By.id("ipLevel3")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select4.getOptions().get(1), "data-id"));
		select4.getOptions().get(1).click();
		driver.findElement(By.id("ipbtn")).click();
		threadSleep(1000);
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElement(By.id("cleanBtn")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElements(By.className("project-id")).get(2)
			.findElement(By.tagName("a")).click();
		wait.until(ExpectedConditions.titleIs("Project Page"));
		threadSleep(500);
		Select select = new Select(driver.findElement(By.id("tendency")));
		select.getOptions().get(1).click();
		threadSleep(500);
		driver.findElement(By.id("submit")).click();
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("index")));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		assertEquals(driver.findElement(By.id("table-tbody"))
			.findElements(By.tagName("tr")).get(3)
			.findElements(By.tagName("td")).get(1).getText(), "Pending");
		logoutFunction();
	}
		
	//test lecture designate candidate
	@Test(timeOut=60000)
	public void test_usecases_2() {
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitted")));
		driver.findElements(By.partialLinkText("Details")).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Designate")));
		driver.findElement(By.partialLinkText("Designate")).click();
		threadSleep(500);
		driver.findElement(By.id("table2")).findElement(By.partialLinkText("Save")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		logoutFunction();
		threadSleep(2000);
	}
		
	//test query result
	@Test(timeOut=60000)
	public void test_usecases_3() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
			
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		assertEquals(driver.findElement(By.id("table-tbody"))
			.findElements(By.tagName("tr")).get(3)
			.findElements(By.tagName("td")).get(1).getText(), "Accepted");
		threadSleep(2000);
		logoutFunction();
	}
		
	//test designate and cancel candidate
	@Test(timeOut=60000)
	public void test_usecases_4() {
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Details")));
		driver.findElements(By.partialLinkText("Details")).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Cancel")));
		driver.findElement(By.partialLinkText("Cancel")).click();
		threadSleep(500);
		driver.findElement(By.id("table2")).findElement(By.partialLinkText("Save")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(500);
		logoutFunction();
	}
		
	//test student query result after cancel
	@Test(timeOut=60000)
	public void test_usecases_5() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
					
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		assertEquals(driver.findElement(By.id("table-tbody"))
			.findElements(By.tagName("tr")).get(3)
			.findElements(By.tagName("td")).get(1).getText(), "Pending");
		threadSleep(2000);
		logoutFunction();
	}

	
	public void threadSleep(int time) {
		try {
			Thread.sleep(time);
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
		}
	}
	
	public void loginFunction(String username, String password) {
		wait.until(ExpectedConditions.titleIs("Index Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alogin")));
		driver.findElement(By.id("alogin")).click();
		wait.until(ExpectedConditions.titleIs("Login Page"));
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));
		assertEquals(driver.findElement(By.id("welcome")).getText(), "Welcome: " + username );
	}
	
	public void logoutFunction() {
		wait.until(ExpectedConditions.titleIs("Index Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alogout")));
		driver.findElement(By.id("alogout")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
	}
	
	
	/*
	
	//finished
	@Test(timeOut=60000)
	public void test_1Student_usercases1() {
		//test student submit interest
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.titleIs("Index Page"));
		Select select2 = new Select(driver.findElement(By.id("ipLevel1")));
		select2.getOptions().get(1).click();
		Select select3 = new Select(driver.findElement(By.id("ipLevel2")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select3.getOptions().get(1), "data-id"));
		select3.getOptions().get(1).click();
		Select select4 = new Select(driver.findElement(By.id("ipLevel3")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select4.getOptions().get(1), "data-id"));
		select4.getOptions().get(1).click();
		driver.findElement(By.id("ipbtn")).click();
		threadSleep(500);
		driver.findElement(By.id("cleanBtn")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElements(By.className("project-id")).get(2)
			.findElement(By.tagName("a")).click();
		wait.until(ExpectedConditions.titleIs("Project Page"));
		threadSleep(500);
		Select select = new Select(driver.findElement(By.id("tendency")));
		select.getOptions().get(1).click();
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("index")));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));	
		logoutFunction();
	}
	
	@Test(timeOut=60000)
	public void test_2Lecture2_usecases1() {
		//test lecture designate candidate
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("submitted")));
		driver.findElements(By.partialLinkText("Details")).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Designate")));
		driver.findElement(By.partialLinkText("Designate")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		threadSleep(2000);
	}
	
	@Test(timeOut=60000)
	public void test_3Lecture2_usecases2() {
		//test designate and cancel candidate
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Details")));
		driver.findElement(By.partialLinkText("Details")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Cancel")));
		driver.findElement(By.partialLinkText("Cancel")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(500);
		logoutFunction();
	}
	
	@Test(timeOut=60000)
	public void test_4Student_usecases2() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElement(By.partialLinkText("Accepted"));
		threadSleep(2000);
		logoutFunction();
	}
	
	
	
	//test student submit interest
	@Test(timeOut=60000)
	public void test_usecases_1() {
		
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.titleIs("Index Page"));
		Select select2 = new Select(driver.findElement(By.id("ipLevel1")));
		select2.getOptions().get(1).click();
		Select select3 = new Select(driver.findElement(By.id("ipLevel2")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select3.getOptions().get(1), "data-id"));
		select3.getOptions().get(1).click();
		Select select4 = new Select(driver.findElement(By.id("ipLevel3")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(select4.getOptions().get(1), "data-id"));
		select4.getOptions().get(1).click();
		driver.findElement(By.id("ipbtn")).click();
		threadSleep(1000);
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElement(By.id("cleanBtn")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		driver.findElements(By.className("project-id")).get(2)
			.findElement(By.tagName("a")).click();
		wait.until(ExpectedConditions.titleIs("Project Page"));
		threadSleep(500);
		Select select = new Select(driver.findElement(By.id("tendency")));
		select.getOptions().get(1).click();
		threadSleep(500);
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		threadSleep(500);
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("index")));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		
		assertEquals(driver.findElement(By.id("table-tbody"))
				.findElements(By.tagName("tr")).get(3)
				.findElements(By.tagName("td")).get(1).getText(), "Pending");
		
		logoutFunction();
	}
	
	//test lecture designate candidate
	@Test(timeOut=60000)
	public void test_usecases_2() {
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitted")));
		driver.findElements(By.partialLinkText("Details")).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Designate")));
		driver.findElement(By.partialLinkText("Designate")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		logoutFunction();
		threadSleep(2000);
		
	}
	
	//test query result
	@Test(timeOut=60000)
	public void test_usecases_3() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		assertEquals(driver.findElement(By.id("table-tbody"))
				.findElements(By.tagName("tr")).get(3)
				.findElements(By.tagName("td")).get(1).getText(), "Accepted");
		threadSleep(2000);
		logoutFunction();
	}
	
	//test designate and cancel candidate
	@Test(timeOut=60000)
	public void test_usecases_4() {
		String username = "Lecture1";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amanagement")));
		driver.findElement(By.id("amanagement")).click();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Details")));
		driver.findElements(By.partialLinkText("Details")).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Cancel")));
		driver.findElement(By.partialLinkText("Cancel")).click();
		threadSleep(500);
		driver.switchTo().alert().accept();
		threadSleep(500);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.titleIs("Management Page"));
		driver.findElement(By.id("index")).click();
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(500);
		logoutFunction();
	}
	
	//test student query result after cancel
	@Test(timeOut=60000)
	public void test_usecases_5() {
		String username = "18000000";
		String password = "123";
		loginFunction(username, password);
		
		wait.until(ExpectedConditions.titleIs("Index Page"));
		threadSleep(2000);
		assertEquals(driver.findElement(By.id("table-tbody"))
				.findElements(By.tagName("tr")).get(3)
				.findElements(By.tagName("td")).get(1).getText(), "Pending");
		threadSleep(2000);
		logoutFunction();
	}
	
	*/
	
	/*
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Edit")));
	driver.findElement(By.partialLinkText("Edit")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ipDraft")));
	driver.findElement(By.id("ipDraft")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
	driver.findElement(By.id("submit")).click();
	threadSleep(500);
	driver.switchTo().alert().accept();
	wait.until(ExpectedConditions.titleIs("Management Page"));
	threadSleep(2000);
	*/
}