import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowManagment {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab() {
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("http://automationpractice.com/index.php?controller=prices-drop");
    }

    @Test
    public void testNewWindow() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title:" + newWindow.getTitle());
    }

    @Test
    public void testWorkingInBothWindowsTab() {
        // Automaticly open & switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());
        // Work in the new window or tab
        driver.findElement(By.id("email_create")).sendKeys("selenium4@demo.com");
        driver.findElement(By.id("SubmitCreate")).click();

        // Get the window id handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

        // Switch & work in the main window
       driver.switchTo().window(mainFirstWindow);
       driver.findElement(By.id("search_query_top")).sendKeys("Shirt");
       driver.findElement(By.name("submit_search")).click();
       System.out.println("Title: "+driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
