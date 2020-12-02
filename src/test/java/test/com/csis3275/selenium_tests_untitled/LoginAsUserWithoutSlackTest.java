package test.com.csis3275.selenium_tests_untitled;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csis3275.config_untitled.SpringBootApplicationInitializer_Untitled;
import com.csis3275.dao_untitled.TicketManagementDAOImpl_jpa_66;

import test.com.csis3275.test_config_untitled.TestDatabaseConfig_jpa_66;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Dec 1, 2020
 * LoginAsUserWithoutSlackTest.java
 * test.com.csis3275.selenium_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationInitializer_Untitled.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginAsUserWithoutSlackTest {
	
	private final int port = 8080;	
	private String rootUrl;
	
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	
	  @Before
	  public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
		driver = new ChromeDriver();
		this.rootUrl = "http://localhost:" + port;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
	  }
	  
	  @After
	  public void tearDown() {
	    driver.quit();
	  }
	  
	  @Test
	  public void loginAsUserWithoutSlack() {
	    driver.get(rootUrl);
	    driver.manage().window().setSize(new Dimension(1936, 1056));
	    driver.findElement(By.cssSelector(".uk-button")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.id("password")).sendKeys("a");
	    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
	    assertEquals(driver.findElement(By.xpath("//p[contains(.,\'Slack profile not found in workspace! Please join the Slack workspace with your UHDA email and try again.\')]")).getText(), "Slack profile not found in workspace! Please join the Slack workspace with your UHDA email and try again.");
	    {
	      WebElement element = driver.findElement(By.id("slack_override"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//a[@uk-tooltip=\'Not connected with Slack\']"));
	      assert(elements.size() > 0);
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	  }
	  
}
