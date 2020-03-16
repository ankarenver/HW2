package hw.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver newDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
