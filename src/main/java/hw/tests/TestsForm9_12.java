package hw.tests;

import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestsForm9_12 {
    public WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Status Codes")).click();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test(dataProvider = "testData")
    public void test(By a, By b , String str){
        driver.findElement(a).click();
        String actual =driver.findElement(b).getText();
        String expected = str;
        Assert.assertTrue(actual.contains(expected));

    }

    @DataProvider(name = "testData")
    public Object[][] testData(){

        return new Object[][]{{By.linkText("200"),By.tagName("p"),"This page returned a 200 status code"},
                              {By.linkText("301"),By.tagName("p"),"This page returned a 301 status code"},
                              {By.linkText("404"),By.tagName("p"),"This page returned a 404 status code"},
                              {By.linkText("500"),By.tagName("p"),"This page returned a 500 status code"}};
    }
}
