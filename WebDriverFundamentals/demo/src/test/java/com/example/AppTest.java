package com.example;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

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

    @Before
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testBasicMethods() {
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);

        assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");
    }

    @Test
    public void testSessionID() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        assertThat(sessionId).isNotNull();        
    }

    @Test
    public void testByTagName() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        WebElement textArea = driver.findElement(By.tagName("textarea"));
        assertThat(textArea.getDomAttribute("rows")).isEqualTo("3");
    }

    @Test
    public void testByHtmlAttributes() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        WebElement texByName = driver.findElement(By.name("my-text"));
        assertThat(texByName.isEnabled()).isTrue();

        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomProperty("type")).isEqualTo("text");

        assertThat(textById.getAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textById.getDomAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textById.getDomProperty("myprop")).isNull();

        List<WebElement> byClassName = driver.findElements(By.className("form-control"));
        assertThat(byClassName.size()).isPositive();
        assertThat(byClassName.get(0).getAttribute("name")).isEqualTo("my-text");
    }

    @Test
    public void testByXPathBasic() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        WebElement hidden = driver.findElement(By.xpath("//input[@type='hidden']"));
        assertThat(hidden.isDisplayed()).isFalse();
    }
    
    @Test
    public void testByXPathAdvanced() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        WebElement radio1 = driver.findElement(By.xpath("//*[@type='radio' and @checked]"));
        assertThat(radio1.getAttribute("id")).isEqualTo("my-radio-1");
        assertThat(radio1.isSelected()).isTrue();
        
        WebElement radio2 = driver.findElement(By.xpath("//*[@type='radio' and not(@checked)]"));
        assertThat(radio2.getAttribute("id")).isEqualTo("my-radio-2");
        assertThat(radio2.isSelected()).isFalse();
    }

    @Test
    public void testByChained() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        List<WebElement> rowsInForm = driver.findElements(new ByChained(By.tagName("form"), By.className("row")));
        assertThat(rowsInForm.size()).isEqualTo(1);
    }

    @Test
    public void testRelativeLocators() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        
        WebElement link = driver.findElement(By.linkText("Return to index"));
        RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement readOnly = driver.findElement(relativeBy.above(link));
        assertThat(readOnly.getAttribute("name")).isEqualTo("my-readonly");
        
    }
}
