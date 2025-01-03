package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    // Constructor to initialize WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the login page
    public void openLoginPage(String url) {
        driver.get(url);
    }

    // Enter username
    public void setUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
    }

    // Enter password
    public void setPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    // Click the login button
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }

    // Get the success message for validation
    public String getSuccessMessage() {
        WebElement messageElement = driver.findElement(By.id("flash"));
        return messageElement.getText();
    }
}
