package hw.tests2;

import hw.utilities.BrowserUnit;
import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test05ValidLinks {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/documentation/en/");
    }

    @Test
    public void test(){
        List<WebElement> allATags = driver.findElements(By.tagName("a"));
        System.out.println(allATags.size());
        List<String> allLinks = new ArrayList<>();
        BrowserUnit.wait(2);
        allATags.forEach(each-> allLinks.add(each.getAttribute("href")));
        System.out.println(allLinks);
        Assert.assertTrue(!allLinks.contains(null),"there is one invalid link");


    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
