package com.web.dpelos.e2e;

import java.time.Duration;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TestCase1 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        // wait = new WebDriverWait(driver, 10);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notificiations");

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // options.addArguments("--headless");
    }

    @Test
    public void SystemTest_mascotasDetail_MascotaName() {
        driver.get("http://localhost:4200/mascota");
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mascotaName")));
        // WebElement mascotaName = driver.findElement(By.id("mascotaName"));
        // assertEquals("Firulais", mascotaName.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
