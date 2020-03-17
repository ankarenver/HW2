package hw.tests;

import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestsForm7 {
    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. And click on “File Upload".
     * Step 3. Upload any file with .txt extension from your
     * computer.
     * Step 4. Click “Upload” button.
     * Step 5. Verify that subject is: “File Uploaded!”
     * Step 6. Verify that uploaded file name is displayed.
     * Note: use element.sendKeys(“/file/path”) with
     * specifying path to the file for uploading. Run this
     * method against “Choose File” button.
     */
    public WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
    @Test
    public void test(){
        driver.findElement(By.linkText("File Upload")).click();
        String filePath = "C:\\Users\\Msi\\Desktop\\xfile.txt.txt";
        driver.findElement(By.xpath("//input[@name='file']")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();
        String actual1 = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(actual1,"File Uploaded!");
        WebElement e = driver.findElement(By.id("uploaded-files"));
        Assert.assertTrue(e.isDisplayed());


    }




}
