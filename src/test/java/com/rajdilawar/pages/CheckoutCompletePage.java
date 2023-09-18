package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends GenericMethodClass {
    private WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    By pageTitle = By.cssSelector(".title");
    By checkoutCompleteMessage = By.cssSelector("h2.complete-header");

    public void assertPageTitle() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(pageTitle, "Checkout: Complete!", 5);
    }

    public void assertCheckoutCompletion() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(checkoutCompleteMessage, "Thank you for your order!", 5);
    }
}
