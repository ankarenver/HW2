package hw.tests2;

import hw.utilities.BrowserUnit;
import hw.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test06Cart {

    private WebDriver driver;
    private int random ;


    @BeforeMethod
    public void setup(){
        driver = DriverFactory.newDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon",Keys.ENTER);
    }

    /** done
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click search
     * 4. remember the name and the price of a random result
     * 5. click on that random result
     * 6. verify default quantity of items is 1
     * 7. verify that the name and the price is the same as the one from step 5
     * 8. verify button "Add to Cart" is visible
     */
    @Test
    public void test_cart(){
        List<WebElement> allItems = driver.findElements(By.xpath("(//div[@class='a-section a-spacing-medium'])"));
        random = BrowserUnit.getRandom(allItems.size());
        //4. remember the name and the price of a random result
        String xpath_forItems= "(//div[@class='a-section a-spacing-medium'])["+random+"]";
        String xpath_forItems_price = "(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-price-whole']";
        String xpath_forItems_price2 = "(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-price-fraction']";
        String xpath_forItems_name ="(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-size-base-plus a-color-base a-text-normal']";
        String priceOfRandomItem = driver.findElement(By.xpath(xpath_forItems_price)).getText()+"."+driver.findElement(By.xpath(xpath_forItems_price2)).getText();
        String nameOfRandomItem = driver.findElement(By.xpath(xpath_forItems_name)).getText();
//        System.out.println(priceOfRandomItem);
//        System.out.println(nameOfRandomItem);
        //5. click on that random result
        WebElement theRandomItem = driver.findElement(By.xpath(xpath_forItems));
        theRandomItem.click();
        //6. verify default quantity of items is 1
        String quantity = driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']//span[@class='a-dropdown-prompt']")).getText();
//        System.out.println(quantity);
        Assert.assertEquals(quantity,"1");
        // 7. verify that the name and the price is the same as the one from step 5
        String priceOfRandomItemInOwnPage = driver.findElement(By.xpath("//span[contains(@id,'priceblock')]")).getText();
        String nameOfRandomItemInOwnPage = driver.findElement(By.id("productTitle")).getText();
//        System.out.println(priceOfRandomItemInOwnPage);
//        System.out.println(nameOfRandomItemInOwnPage);
        Assert.assertTrue(priceOfRandomItemInOwnPage.contains(priceOfRandomItem));
        Assert.assertEquals(nameOfRandomItem,nameOfRandomItemInOwnPage);
        // 8. verify button "Add to Cart" is visible
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Add to Cart']")).isDisplayed());
    }


    /** done
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click search
     * 4. remember name first result that has prime label
     * 5. select Prime checkbox on the left
     * 6. verify that name first result that has prime label is same as step 4
     * 7. check the last checkbox under Brand on the left
     * 8. verify that name first result that has prime label is different
     */
    @Test
    public void test_prime(){
        //4. remember name first result that has prime label
        String xpath_for_theFirst_prime_item_name = "(//i[@aria-label=\"Amazon Prime\"])[1]//ancestor::div//h2";
        String firstPrimeItemsName = driver.findElement(By.xpath(xpath_for_theFirst_prime_item_name)).getText();
        //5. select Prime checkbox on the left
        driver.findElement(By.xpath("//li[@aria-label=\"Prime Eligible\"]//i[@class='a-icon a-icon-checkbox']")).click();
        //6. verify that name first result that has prime label is same as step 4
        String fistNameAfterSelectPrime = driver.findElement(By.xpath("(//div//h2)[1]")).getText();
        Assert.assertEquals(firstPrimeItemsName,fistNameAfterSelectPrime);
        //7. check the last checkbox under Brand on the left
        List<WebElement> allCheckBoxesInBrand = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li//label"));
        allCheckBoxesInBrand.get(allCheckBoxesInBrand.size()-1).click();
        BrowserUnit.wait(2);
        String afterClickingBrandFirstItem = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText();
        //8. verify that name first result that has prime label is different
        Assert.assertNotEquals(afterClickingBrandFirstItem, fistNameAfterSelectPrime);

    }

    /** done
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. remember all Brand names on the left
     * 4. select Prime checkbox on the left
     * 5. verify that same Brand names are still displayed
     */
    @Test
    public void test_moreSpoons(){

        //3. remember all Brand names on the left
        List<WebElement> allNamesInBrand = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> allNamesBeforeClickingPrime = new ArrayList<>();
        allNamesInBrand.forEach(each-> allNamesBeforeClickingPrime.add(each.getText()));
        System.out.println(allNamesBeforeClickingPrime);

        //4. select Prime checkbox on the left
        driver.findElement(By.xpath("//li[@aria-label=\"Prime Eligible\"]//i[@class='a-icon a-icon-checkbox']")).click();

        //5. verify that same Brand names are still displayed
        List<WebElement> allNamesInBrandAfter = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> allNamesAfterClickingPrime = new ArrayList<>();
        allNamesInBrandAfter.forEach(each-> allNamesAfterClickingPrime.add(each.getText()));
        System.out.println(allNamesAfterClickingPrime);
        Assert.assertEquals(allNamesBeforeClickingPrime,allNamesAfterClickingPrime);
    }


    /** done
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click on Price option Under $25 on the left
     * 4. verify that all results are cheaper than $25
     */
    @Test
    public void test_cheapSpoons(){

        //3. click on Price option Under $25 on the left
        driver.findElement(By.xpath("(//div[@id='priceRefinements']//span[@class='a-size-base a-color-base'])[1]")).click();

        //4. verify that all results are cheaper than $25
        BrowserUnit.wait(10);
        List<WebElement> allPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        System.out.println(allPrice.size());
        for (WebElement each:allPrice) {
            System.out.println(each.getText());
            int price = Integer.parseInt(each.getText());
            Assert.assertTrue(price<25);
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
