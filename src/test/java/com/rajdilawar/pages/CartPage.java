package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends GenericMethodClass {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By pageTitle = By.cssSelector(".title");
    By checkOutButton = By.cssSelector("button.btn.btn_action");
    By cartPriceValues = By.cssSelector("div.inventory_item_price");

    public void assertPageTitle() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(pageTitle, "Your Cart", 5);
    }

    public void navigateToCheckoutStepOnePage() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.waitAndClick(checkOutButton, 5);
    }

    public void assertLowestValueInCart(Double lowestValue) {
        System.out.println("my lowest " + lowestValue);
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(cartPriceValues, lowestValue.toString(), 5);
    }

}