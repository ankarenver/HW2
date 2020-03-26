package hw.tests2;

import hw.utilities.BrowserUnit;
import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test03DepartmentsSort {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }


    @Test
    public void test1(){
        Assert.assertEquals(driver.findElement(By.className("nav-search-label")).getText(),"All");
        Select allDropdown = new Select(driver.findElement(By.id("searchDropdownBox")));
        List<WebElement> allElements = allDropdown.getOptions();
        List<String> allElementsInString = new ArrayList<>();
        allElements.forEach(each-> allElementsInString.add(each.getText().trim()));

        char current =65 ;
        char temp =65;
        for (String each : allElementsInString){
            current = each.charAt(0);
            if(current<temp){
                Assert.assertTrue(true,"not alphabetic");
                break;
            }
            temp = current;
        }
    }

    /**
     * 1. go to https://www.amazon.com/gp/site-directory
     * 2. verify that every main department name (indicated by blue rectangles in the picture) is
     * present in the All departments dropdown (indicated by green rectangle in the picture)
     */
    @Test
    public void test_mainDepartment(){
        driver.navigate().to("https://www.amazon.com/gp/site-directory");


    }


    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
