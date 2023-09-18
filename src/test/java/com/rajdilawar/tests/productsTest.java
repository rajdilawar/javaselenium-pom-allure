package com.rajdilawar.tests;

import com.rajdilawar.base.InitClass;
import com.rajdilawar.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class productsTest {
    InitClass initClass = null;
    WebDriver driver = null;

    @BeforeMethod
    public void setUpMethod() throws IOException {
        initClass = new InitClass();
        driver = initClass.getWebdriver();
        driver.get(initClass.getConfiguration().baseSauceDemoUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

    }

    @Test(priority = 1)
    @Description("Test to Buy cheapest product")
    @Severity(SeverityLevel.NORMAL)
    public void buyCheapestArticle() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.assertPageTitle();
        double lowestValue = inventoryPage.addToCartLowestValueArticles();
        inventoryPage.navigateToCheckOut();
        CartPage cartPage = new CartPage(driver);
        cartPage.assertPageTitle();
        cartPage.assertLowestValueInCart(lowestValue);
        cartPage.navigateToCheckoutStepOnePage();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.assertPageTitle();
        checkoutStepOnePage.fillYourInformationForm();
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutStepTwoPage.assertPageTitle();
        checkoutStepTwoPage.finishCheckoutProcess();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        checkoutCompletePage.assertPageTitle();
        checkoutCompletePage.assertCheckoutCompletion();
    }

    @Test(priority = 2)
    @Description("Test to filter product in ascending order")
    @Severity(SeverityLevel.NORMAL)
    public void filterProducts() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.assertPageTitle();
        inventoryPage.checkPrizeValueInAscendingOrder();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

