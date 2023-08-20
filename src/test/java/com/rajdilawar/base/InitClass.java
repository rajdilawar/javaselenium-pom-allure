package com.rajdilawar.base;
import com.rajdilawar.properties.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InitClass {
    public WebDriver driver;
    Configuration configuration;

    public InitClass() throws IOException {
        System.out.println("Initialize ");
        initializationMethod();
    }

    public void initializationMethod() throws IOException {
        this.configuration = new Configuration();
        String browserName = configuration.browserName();
        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = new ChromeDriver(options);
            System.out.println("Initializing Chrome driver");
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
            System.out.println("Initializing Firefox driver");
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();
            System.out.println("Initializing edge driver");
        } else if (browserName.equals("chrome-remote")) {
            String remote_url_chrome = "http://chrome:4444/wd/hub"; // Use service name "chrome"
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL(remote_url_chrome), options);
            System.out.println("Initializing remote Chrome driver");
        } else if (browserName.equals("firefox-remote")) {
            String remote_url_firefox = "http://firefox:4444/wd/hub"; // Use service name "firefox"
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(remote_url_firefox), options);
            System.out.println("Initializing remote Firefox driver");
        }
        // Managing driver
        driver.manage().deleteAllCookies();
        // Implicit wait for 10 seconds usually good for page to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Return configuration object
    public Configuration getConfiguration() {
        return this.configuration;
    }

    public WebDriver getWebdriver() {
        return driver;
    }
}
