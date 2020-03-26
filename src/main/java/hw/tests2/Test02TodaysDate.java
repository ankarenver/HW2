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

import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test02TodaysDate {
    private WebDriver driver;
    private By yearBy = By.id("year");
    private By monthBy = By.id("month");
    private By dayBy = By.id("day");
    private int random;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/dropdown");
    }

    @Test
    public void todayDateTest(){
        Select year = new Select(driver.findElement(yearBy));
        Select month = new Select(driver.findElement(monthBy));
        Select day = new Select(driver.findElement(dayBy));
        String actualDate = (year.getFirstSelectedOption().
                getText()+month.getFirstSelectedOption().
                getText()+day.getFirstSelectedOption().getText());
        String expected = BrowserUnit.currentDateReturner();
        System.out.println("actualDate = " + actualDate);
        System.out.println("expected = " + expected);
        Assert.assertEquals(actualDate,expected);
    }


    @Test
    public void yearsMonthsDaysTest(){
        Select yearDropdown = new Select(driver.findElement(yearBy));
        List<WebElement> years = yearDropdown.getOptions();
        random = BrowserUnit.getRandom(years.size());
        yearDropdown.selectByIndex(random);
        Select monthDropdown = new Select(driver.findElement(monthBy));
        List<WebElement> months = monthDropdown.getOptions();
        for (WebElement each :months){
            monthDropdown.selectByVisibleText(each.getText());

            Select dayDropdown = new Select(driver.findElement(dayBy));
            List<WebElement> days = dayDropdown.getOptions();
            int actualDays = YearMonth.of(Integer.parseInt(years.get(random).getText()),months.indexOf(each)+1).lengthOfMonth();
//            System.out.println(actualDays);
            if (Year.isLeap(Integer.parseInt(years.get(random).getText()))&&(months.indexOf(each)==1)){
                Assert.assertEquals(29,actualDays);
            }else {
                Assert.assertEquals(days.size(),actualDays);
            }

        }
    }


    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }


}
