package hw.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestsForm8 extends TestsForm7{
    @Test
    public void test8(){
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        String actual = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(actual,"You selected: United States of America");
    }
}
