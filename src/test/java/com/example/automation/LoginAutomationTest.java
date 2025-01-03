package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set up WebDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pooja\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Set ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run tests in headless mode (no UI)
        options.addArguments("--disable-gpu"); // Disable GPU acceleration
        options.addArguments("--window-size=1920,1080"); // Set screen size

        WebDriver driver = new ChromeDriver(options);

        try {
            // Initialize the LoginPage object
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to the login page
            loginPage.openLoginPage("https://the-internet.herokuapp.com/login");

            // Perform login with valid credentials
            loginPage.setUsername("tomsmith");
            loginPage.setPassword("SuperSecretPassword!");
            loginPage.clickLoginButton();

            // Validate successful login
            String successMessage = loginPage.getSuccessMessage();
            assertTrue(successMessage.contains("You logged into a secure area!"),
                    "Login should be successful and a success message should be displayed.");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
