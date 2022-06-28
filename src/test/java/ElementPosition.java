import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementPosition {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationu.applitools.com/");
        System.out.println("Title: " + driver.getTitle());

    }

    @Test
    public void getPositionDimension() {
        WebElement logoTAU = driver.findElement(
                By.xpath("//div[@id='app']//header/a/img"));
        Rectangle rectTAU = logoTAU.getRect();
        System.out.println(
                "x: " + rectTAU.getX()
                + "\ny: " + rectTAU.getY()
                + "\nWidth: " + rectTAU.getWidth()
                + "\nHeight: " + rectTAU.getHeight());
    }
}
