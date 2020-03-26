package hw.tests2;

import hw.utilities.BrowserUnit;
import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test01Days {

    private WebDriver driver;
    private int random;
    int count;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");

    }

    @Test
    public void daysTest(){
        List<WebElement> checkboxes = driver.findElements(By.xpath("//td[@align='left']//span"));
        List<WebElement> checkboxes1 = driver.findElements(By.xpath("//td[@align='left']//input"));

        do {
            boolean isture = true;
            while (isture) {
                random = BrowserUnit.randomReturner();
                if (checkboxes1.get(random).isEnabled()) {
                    checkboxes.get(random).click();
                    System.out.println(checkboxes.get(random).getText());
                    if (checkboxes.get(random).getText().equals("Friday")){
                        count++;
                    }
                    checkboxes.get(random).click();
                    isture = false;
                }
            }
        }while (count!=3);
    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
