package com.example.automation; 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.assertEquals; 
public class LoginAutomationTest { 
    @Test 
    public void testLogin() { 
        // Set up the WebDriver 
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pooja\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); 
        WebDriver driver = new ChromeDriver(); 
 
        try { 
            // Navigate to the login page 
            driver.get("https://www.saucedemo.com/"); 
 
            // Locate the username and password fields 
            WebElement usernameField = driver.findElement(By.id("user-name")); 
            WebElement passwordField = driver.findElement(By.id("password")); 
            WebElement loginButton = driver.findElement(By.id("login-button")); 
 
            // Perform login 
            usernameField.sendKeys("testUser"); 
            passwordField.sendKeys("testPassword"); 
            loginButton.click(); 
 
            // Validate successful login 
            String expectedTitle = "Swag Labs"; 
            String actualTitle = driver.getTitle(); 
            assertEquals(expectedTitle, actualTitle); 
 
        } finally { 
            // Close the browser 
            driver.quit(); 
        } 
} 
}
