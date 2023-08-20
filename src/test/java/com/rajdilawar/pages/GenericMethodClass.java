package com.rajdilawar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericMethodClass {
    private WebDriver driver;
    public GenericMethodClass(WebDriver driver) { this.driver = driver; }
    public void scrollToTheElement(WebElement element) {
        // Scroll to the element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitAndClick(By element, long waitTimeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        WebElement clickableElement = driver.findElement(element);
        clickableElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }

    public void typeText(By element, String text) {
        WebElement inputField = driver.findElement(element);
        inputField.click();
        inputField.sendKeys(text);
    }

    public boolean assertText(By element, String expectedText, long waitTimeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        WebElement targetElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
        String actualText = targetElement.getText().trim();
        return actualText.equals(expectedText);
    }
}
