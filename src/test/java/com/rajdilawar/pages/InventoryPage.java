package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InventoryPage extends GenericMethodClass {
    private WebDriver driver;
    private Properties configuration;

    public InventoryPage(WebDriver driver) {
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

    By pageTitle = By.cssSelector(".title");
    By prizeValues = By.cssSelector("div.inventory_item_price");
    By addToCartButtons = By.cssSelector("button.btn_inventory");
    By shoppingCartButton = By.cssSelector("span.shopping_cart_badge");
    By filterDropdown = By.cssSelector("select.product_sort_container");
    By filterOptions = By.cssSelector("option[value='lohi']");
    By openTheMenu = By.cssSelector("#react-burger-menu-btn");
    By logOutLink = By.xpath("//a[contains(text(),'Logout')]");


    public void assertPageTitle() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.assertText(pageTitle, "Products", 5);
    }
    public double addToCartLowestValueArticles() {
        // Find all elements with the class 'inventory_item_price'
        List<WebElement> containerElements = driver.findElements(By.cssSelector("div.inventory_item_description"));

        // Initialize variables to track lowest price and its button
        double lowestValue = Double.MAX_VALUE;
        WebElement lowestButton = null;

        // Iterate through each container
        for (WebElement container : containerElements) {
            WebElement priceElement = container.findElement(prizeValues);

            String priceText = priceElement.getText().replaceAll("[^0-9.]", ""); // Extract numeric part
            if (!priceText.isEmpty()) {
                double priceValue = Double.parseDouble(priceText);
                if (priceValue < lowestValue) {
                    lowestValue = priceValue;
                    lowestButton = container.findElement(addToCartButtons);
                }
            }
        }
        // Click the button with the lowest price
        if (lowestButton != null) {
            lowestButton.click();
            System.out.println("Clicked the button with the lowest price: $" + lowestValue);
        } else {
            System.out.println("No item found with a valid price and button.");
        }
        return lowestValue;
    }

    public void checkPrizeValueInAscendingOrder() {
        Select dropdown = new Select(driver.findElement(filterDropdown));
        dropdown.selectByVisibleText("Price (low to high)");
        List<WebElement> containerElements = driver.findElements(By.cssSelector("div.inventory_item_description"));
        List<Double> prices = new ArrayList<>();

        // Iterate through each container
        boolean isAscending = false;
        for (WebElement container : containerElements) {
            WebElement priceElement = container.findElement(prizeValues);
            String priceText = priceElement.getText().replaceAll("[^0-9.]", ""); // Extract numeric part
            double priceValue = Double.parseDouble(priceText);
            prices.add(priceValue);

            // Verify if the prices are in ascending order
            isAscending = true;
            for (int i = 1; i < prices.size(); i++) {
                if (prices.get(i) < prices.get(i - 1)) {
                    isAscending = false;
                    break;
                }
            }
        }
        if (isAscending) {
            System.out.println("Prices are in ascending order.");
        } else {
            System.out.println("Prices are not in ascending order.");
        }
    }

    public void navigateToCheckOut() {
        GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
        genericMethodClass.waitAndClick(shoppingCartButton, 5);
    }

    public void logout() {
            GenericMethodClass genericMethodClass = new GenericMethodClass(driver);
            genericMethodClass.waitAndClick(openTheMenu, 5);
            genericMethodClass.waitAndClick(logOutLink, 5);
    }
}
