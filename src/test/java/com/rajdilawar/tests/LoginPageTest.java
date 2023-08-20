package com.rajdilawar.tests;

import com.rajdilawar.base.InitClass;
import com.rajdilawar.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginPageTest {
    InitClass initClass = null;
    WebDriver driver = null;

    @BeforeMethod
    public void setUpMethod() throws IOException {
        initClass = new InitClass();
        driver = initClass.getWebdriver();
        driver.get(initClass.getConfiguration().baseSauceDemoUrl());
    }

    @Test(priority = 1)
    @Description("Test to verify Login with valid credentials")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.assertPageTitle();
        inventoryPage.logout();
    }

    @Test(priority = 2)
    @Description("Test to verify failed login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithInValidCredentials() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.invalidLogin();
    }

    @Test(priority = 3)
    @Description("Test to verify failed login with blocked user")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithBlockedUser() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.blockedUserLogin();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
}
