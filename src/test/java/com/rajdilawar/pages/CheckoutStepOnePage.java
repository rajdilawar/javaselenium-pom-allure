package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends GenericMethodClass{
    private WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By pageTitle = By.cssSelector(".title");
    By firstName = By.cssSelector("input[data-test='firstName']");
    By lastName = By.cssSelector("input[data-test='lastName']");
    By zipCode = By.cssSelector("input[placeholder='Zip/Postal Code']");
    By continueButton = By.cssSelector("input[type='submit']");


    public void assertPageTitle() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(pageTitle, "Checkout: Your Information", 5);
    }

    public void fillYourInformationForm() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.typeText(firstName, "raj");
        genericMethodClass.typeText(lastName, "singh");
        genericMethodClass.typeText(zipCode, "10789");
        genericMethodClass.waitAndClick(continueButton, 5);
    }
}
