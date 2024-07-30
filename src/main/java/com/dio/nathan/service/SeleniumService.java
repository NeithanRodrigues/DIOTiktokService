package com.dio.nathan.service;

import com.dio.nathan.model.Video;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class SeleniumService {
    private static final Logger log = LogManager.getLogger(SeleniumService.class);
    private WebDriver driver;

    public void setup() {
        log.info("Initializing Selenium Driver");

        // Set up ChromeOptions to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }

    public Video scrapLink(String url) {
        log.info("Starting scrap link");

        if (driver == null) {
            throw new IllegalStateException("No webdriver available");
        }
        driver.get(url);

        WebElement titleElement = driver.findElement(By.cssSelector(
                ".css-j2a19r-SpanText.efbd9f0"
        ));
        WebElement authorElement = driver.findElement(By.cssSelector(
                ".css-1c7urt-SpanUniqueId.evv7pft1"
        ));

        String title = titleElement.getText();
        String author = authorElement.getText();
        log.info("Data Collected Successfully");

        driver.quit();
        log.info("Driver Closed Successfully");

        return new Video(title, author, url);
    }

    @PreDestroy
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
