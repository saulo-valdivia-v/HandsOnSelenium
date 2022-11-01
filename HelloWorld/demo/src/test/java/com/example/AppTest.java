package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }
}
