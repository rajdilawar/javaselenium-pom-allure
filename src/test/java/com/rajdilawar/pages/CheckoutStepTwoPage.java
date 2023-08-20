package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends GenericMethodClass{
    private WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By pageTitle = By.cssSelector(".title");
    By finishButton = By.cssSelector("button.btn.btn_action");


    public void assertPageTitle() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(pageTitle, "Checkout: Your Information", 5);
    }

    public void finishCheckoutProcess() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.waitAndClick(finishButton, 5);
    }
}
