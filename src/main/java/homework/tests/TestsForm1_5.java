package homework.tests;

import homework.utilities.BrowserUnit;
import homework.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestsForm1_5 {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        BrowserUnit.wait(2);
        driver.findElement(By.linkText("Registration Form")).click();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter “wrong_dob” into date of birth input
     * box.
     * Step 4. Verify that warning message is displayed:
     * “The date of birth is not valid”
     */
    @Test
    public void Test1(){
        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        String actual = driver.findElement(By.xpath("//small[@class='help-block' and @data-bv-for=\"birthday\"][2]")).getText();
        String expected = "The date of birth is not valid";
        Assert.assertEquals(actual,expected);

    }

    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Verify that following options for
     * programming languages are displayed: c++, java,
     * JavaScript
     */
    @Test
    public void Test2(){
        List<WebElement> allEle = driver.findElements(By.xpath("//div[@class ='form-check form-check-inline']/label"));
        System.out.println(allEle.size());
        Assert.assertEquals(allEle.get(0).getText(),"C++");
        Assert.assertEquals(allEle.get(1).getText(),"Java");
        Assert.assertEquals(allEle.get(2).getText(),"JavaScript");
    }

    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into first
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “first name must be more than 2 and less than 64
     * characters long”
     */
    @Test
    public void Test3(){
        driver.findElement(By.name("firstname")).sendKeys("a");
        String  actual =driver.findElement(By.xpath("//small[@data-bv-for=\"firstname\"][2]")).getText();
        String expected = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual,expected);
    }


    /**
     * Step 1. Go to https://practicecybertekschool.
     * herokuapp.com
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into last
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “The last name must be more than 2 and less than
     * 64 characters long”
     */
    @Test
    public void Test4(){
        driver.findElement(By.name("lastname")).sendKeys("a");
        String  actual =driver.findElement(By.xpath("//small[@data-bv-for=\"lastname\"][2]")).getText();
        String expected ="The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual,expected);

    }


    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter any valid first name.
     * Step 4. Enter any valid last name.
     * Step 5. Enter any valid user name.
     * Step 6. Enter any valid password.
     * Step 7. Enter any valid phone number.
     * Step 8. Select gender.
     * Step 9. Enter any valid date of birth.
     * Step 10. Select any department.
     * Step 11. Enter any job title.
     * Step 12. Select java as a programming language.
     * Step 13. Click Sign up.
     * Step 14. Verify that following success message is
     * displayed: “You've successfully completed
     * registration!”
     * Note: for using dropdown, please refer to the
     * documentation: https://selenium.dev/selenium/
     * docs/api/java/org/openqa/selenium/support/ui/
     * Select.html or, please watch short video about dropdowns
     * that is posted on canvas.
     */
    @Test
    public void Test5(){

        // first name
        driver.findElement(By.name("firstname")).sendKeys("SDET");
        // last name
        driver.findElement(By.name("lastname")).sendKeys("Engineer");
        // username
        driver.findElement(By.name("username")).sendKeys("hardFr");
        //  email
        driver.findElement(By.name("email")).sendKeys("sadkjahkjsd@gmail.com");
        // password
        driver.findElement(By.name("password")).sendKeys("superpassword");
        // phone
        driver.findElement(By.name("phone")).sendKeys("456-789-4444");
        // radio button
        driver.findElements(By.name("gender")).get(2).click();   // clicking the 3rd radio button
        // birth day
        driver.findElement(By.name("birthday")).sendKeys("10/5/2006");
        // department
        new Select(driver.findElement(By.name("department"))).getOptions().get(1).click();
        // jobs
        new Select(driver.findElement(By.name("job_title"))).getOptions().get(3).click();
        // languages
        driver.findElement(By.id("inlineCheckbox2")).click();
        // submit
        driver.findElement(By.id("wooden_spoon")).click();

        String actualResult = driver.findElement(By.xpath("//p")).getText();
        String expected = "You've successfully completed registration!";

        Assert.assertEquals(actualResult,expected);
    }


}
