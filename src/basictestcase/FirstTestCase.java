package basictestcase;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTestCase {
static WebDriver driver = new ChromeDriver();;
static String url = "https://elba-tech.com";
static HttpURLConnection huc =null;
	public static void main(String[] args) {
		startDriver(driver);
		scanAllLinks();
//		clickMenuLinks();

	}
	
	
	public static void startDriver(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		 driver.get(url);
		 driver.manage().window().maximize();
	}


public static void scanAllLinks(){
	List<WebElement> linkElements = driver.findElements(By.tagName("a"));
	for(WebElement item : linkElements) {
		 validLinks(item.getAttribute("href"));
        if(item.getAttribute("href") == null){
        	continue;
           }
        else {
	System.out.println(item.getAttribute("href"));
        }
	}

	
}

public static void validLinks(String URL) {
	try {
        huc = (HttpURLConnection)(new URL(url).openConnection());
        
        huc.setRequestMethod("HEAD");
        
        huc.connect();
        
      int respCode = huc.getResponseCode();
        
        if(respCode >= 400){
            System.out.println(url+" is a broken link");
        }
        else{
            System.out.println(url+" is a valid link");
        }
            
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static void clickMenuLinks() {
	WebElement cookie = driver.findElement(By.xpath("//body/div[3]/div[1]/div[1]/a[1]"));
	cookie.click();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='menu-menu-elba']")));
    List<WebElement> navLinks = driver.findElements(By.xpath("//ul[@id='menu-menu-elba']/li/a"));
    List<WebElement>  subLinks = driver.findElements(By.xpath("//ul[@id='menu-menu-elba']/li/div/div/ul/li/a"));
    List <String> links = new LinkedList();
    for(WebElement i:navLinks) {
    	links.add(i.getAttribute("href"));
    }
    
    for(WebElement i:subLinks) {
    	links.add(i.getAttribute("href"));
    }
    
    for (String i : links) {
    	
    
    		driver.navigate().to(i);
    		System.out.println(i+"is clicked");

    }

}
}
