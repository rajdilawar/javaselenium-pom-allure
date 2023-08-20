package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginPage extends GenericMethodClass {
    private WebDriver driver;
    private Properties configuration;


    public LoginPage(WebDriver driver) {
        // calling the constructor of the superclass GenericMethodClass by passing the driver parameter to it
        super(driver);
        this.driver = driver;
        configuration = new Properties();
        try (InputStream input = this.getClass().getResourceAsStream("/config.properties")) {
            configuration.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //locators
    By userNameInputField = By.id("user-name");
    By passwordInputField = By.id("password");
    By loginBtn = By.id("login-button");
    By errorMessage = By.cssSelector("h3[data-test='error']");

    public void login() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.typeText(userNameInputField, configuration.getProperty("validUserName"));
        genericMethodClass.typeText(passwordInputField, configuration.getProperty("validPassword"));
        genericMethodClass.waitAndClick(loginBtn, 5);
    }
    public void invalidLogin() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.typeText(userNameInputField, configuration.getProperty("lockedUser"));
        genericMethodClass.typeText(passwordInputField, configuration.getProperty("inValidPassword"));
        genericMethodClass.waitAndClick(loginBtn, 5);
        genericMethodClass.assertText(errorMessage, "Epic sadface: Username and password do not match any user in this service", 5);
    }

    public void blockedUserLogin() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.typeText(userNameInputField, configuration.getProperty("lockedUser"));
        genericMethodClass.typeText(passwordInputField, configuration.getProperty("validPassword"));
        genericMethodClass.waitAndClick(loginBtn, 5);
        genericMethodClass.assertText(errorMessage, "Epic sadface: Sorry, this user has been locked out.", 5);
    }

}
