package hw.tests;

import hw.utilities.BrowserUnit;
import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestsForm6 {

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tempmailaddress.com/");
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void test(){
        String email = driver.findElement(By.id("email")).getText();
        BrowserUnit.wait(1);
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        BrowserUnit.wait(1);
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        BrowserUnit.wait(1);
        driver.findElement(By.name("full_name")).sendKeys("essnver");
        BrowserUnit.wait(1);
        driver.findElement(By.name("email")).sendKeys(email, Keys.ENTER);
        BrowserUnit.wait(1);
        String actual = driver.findElement(By.tagName("h3")).getText();
        BrowserUnit.wait(1);
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual,expected);
        driver.navigate().to("https://www.tempmailaddress.com/");
        BrowserUnit.wait(5);
        String expected1 = "do-not-reply@practice.cybertekschool.com";
        driver.findElement(By.xpath("//tbody[@id='schranka']//tr[@data-href='2'][1]//td[1]")).click();
        BrowserUnit.wait(1);
        String actual1 =driver.findElement(By.id("odesilatel")).getText();
        BrowserUnit.wait(1);
        Assert.assertEquals(actual1,expected1);
        String expected2 = "Thanks for subscribing to practice.cybertekschool.com!";
        String actual2 = driver.findElement(By.id("predmet")).getText();
        BrowserUnit.wait(1);
        Assert.assertEquals(actual2,expected2);
    }
}
