package com.web.dpelos.e2e;

import java.time.Duration;
import java.util.Collection;

import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.repository.DrogaRepository;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.TratamientoRepository;
import com.web.dpelos.service.DrogaService;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UseTestCase2 {

        @Autowired
        private TratamientoRepository tratamientoRepository;
        @Autowired
        private DrogaRepository drogaRepository;
        @Autowired
        private DrogaService drogaService;

        private static final String BASE_URL = "http://localhost:4200/";
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
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                // options.addArguments("--headless");
        }

        // Method to safely interact with an element using retries
        private void safeClick(By locator) {
                for (int i = 0; i < 3; i++) { // Retry up to 3 times
                        try {
                                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                                element);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                                break; // Break the loop if the element is found and no exception is thrown
                        } catch (StaleElementReferenceException e) {
                                // Retry locating the element
                                System.out.println("StaleElementReferenceException caught. Retrying...");
                        }
                }
        }

        @Test
        public void SystemTest_UseCase2() {
                long initialNumberOfTratamientos = tratamientoRepository.count();
                long initialNumberOfSoldDrogas = drogaService.obtenerTotalUnidadesVendidas();
                Double initialGanancias = drogaService.obtenerTotalVentas();
                // Navigate to the login page
                driver.get(BASE_URL + "login");
                // Wait for the "veterinario-login" button to be present
                WebElement btnVeterinarioLogin = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("veterinario-login")));

                // Scroll to the "veterinario-login" button
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnVeterinarioLogin);
                // Use JavaScript to click the "veterinario-login" button
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnVeterinarioLogin);

                // Capture the fields of the login form
                WebElement inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                WebElement inputPasswordVet = driver.findElement(By.id("passwordVet"));

                // Assign correct information to the fields
                inputCedulaVet.sendKeys("741258963");
                inputPasswordVet.sendKeys("password505");
                // Get the login button and click it
                WebElement btnIniciarSesionVet = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnVeterinario")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnIniciarSesionVet);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIniciarSesionVet);

                // Wait for the "btnVerMascotas" button to be present and click it
                WebElement btnVerMascotas = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnVerMascotas")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnVerMascotas);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                                btnVerMascotas);

                // Wait for the "search-input" input to be present
                WebElement inputSearchMascota = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("search-input")));
                // Assign correct information to the search input
                inputSearchMascota.sendKeys("Firulais");

                // Wait for the "btnBuscarMascota" button to be present and click it
                WebElement btnSearchMascota = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchMascota")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                btnSearchMascota);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                                btnSearchMascota);

                // Wait for the "cardMascota" element to be present anc then clicking on it
                WebElement tajetaMascotaElement = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("cardMascota")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                tajetaMascotaElement);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                                tajetaMascotaElement);
                // Getting elements from the form for assignning a Tratamiento to a Mascota
                WebElement inputMotivoTratamiento = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("motivoTratamiento")));
                WebElement inputRecomendacionesGenerales = driver.findElement(By.id("recomendacionesGenerales"));
                inputMotivoTratamiento.sendKeys("Lele pancha");
                inputRecomendacionesGenerales.sendKeys("No se le de comida chatarra");
                // // Redirecting to add Medicamento
                // WebElement addMedicamentoTratamiento = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("irAMedicamentos")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // addMedicamentoTratamiento);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // addMedicamentoTratamiento);
                // // Getting elements from the form for assignning a Medicamento to a
                // Tratamiento
                // // Search for a Medicamento
                // WebElement inputSearchMedicamento = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("inputMedicamento")));
                // // Assign correct information to the search input
                // inputSearchMedicamento.sendKeys("Amitriptilina");
                // // Searching the Medicamento
                // WebElement btnSearchMedicamento = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchMedicamento")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // btnSearchMedicamento);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // btnSearchMedicamento);
                // // Select the Medicamento
                // WebElement btnAddMedicamento = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("addMedicamento")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // btnAddMedicamento);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // btnAddMedicamento);
                // // Redirecting to add Tratamiento to a Mascota
                // WebElement btnActualizarMedicamentos = wait
                // .until(ExpectedConditions
                // .presenceOfElementLocated(By.id("btnActualizarMedicamentos")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // btnActualizarMedicamentos);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // btnActualizarMedicamentos);
                // // Getting the button guardarTratamiento and clicking on it
                // WebElement btnGuardarTratamiento = wait
                // .until(ExpectedConditions
                // .presenceOfElementLocated(By.id("btnGuardarTratamiento")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // btnGuardarTratamiento);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // btnGuardarTratamiento);
                // // Getting the Tratamientos button and clicking on it
                // WebElement btnTratamientos = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("btnTratamientos")));
                // ((JavascriptExecutor)
                // driver).executeScript("arguments[0].scrollIntoView(true);",
                // btnTratamientos);
                // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                // btnTratamientos);

                // // Find the <span> element within the parent element
                // WebElement spanElement = wait
                // .until(ExpectedConditions.presenceOfElementLocated(By.id("spanNombreMascota")));
                // // Capture the text inside the <span> element
                // String spanText = spanElement.getText();
                // // Making sure the tratamiento was added to the Mascota "Firulais"
                // Assertions.assertThat(spanText).isEqualTo("Firulais");

                // Redirecting to add Medicamento
                safeClick(By.id("irAMedicamentos"));

                // Getting elements from the form for assigning a Medicamento to a Tratamiento
                // Search for a Medicamento
                WebElement inputSearchMedicamento = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("inputMedicamento")));
                // Assign correct information to the search input
                inputSearchMedicamento.sendKeys("Amitriptilina");

                // Searching the Medicamento
                safeClick(By.id("btnSearchMedicamento"));

                // Select the Medicamento
                safeClick(By.id("addMedicamento"));

                // Redirecting to add Tratamiento to a Mascota
                safeClick(By.id("btnActualizarMedicamentos"));

                // Getting the button guardarTratamiento and clicking on it
                safeClick(By.id("btnGuardarTratamiento"));

                // Getting the Tratamientos button and clicking on it
                safeClick(By.id("btnTratamientos"));

                // // Find the <span> element within the parent element
                // WebElement spanElement = null;
                // for (int i = 0; i < 3; i++) { // Retry up to 3 times
                // try {
                // WebElement parentElement = wait.until(
                // ExpectedConditions.presenceOfElementLocated(By.cssSelector(".titulo")));
                // spanElement = parentElement.findElement(By.tagName("span"));
                // break; // Break the loop if the element is found and no exception is thrown
                // } catch (StaleElementReferenceException e) {
                // // Retry locating the element
                // System.out.println("StaleElementReferenceException caught. Retrying...");
                // }
                // }

                // if (spanElement != null) {
                // // Capture the text inside the <span> element
                // String spanText = spanElement.getText();
                // System.out.println("Text inside the <span> element: " + spanText);

                // // Add assertions to verify the text inside the <span> element
                // Assertions.assertThat(spanText).isEqualTo("Expected Mascota Name");
                // } else {
                // System.out.println("Failed to locate the <span> element after retries.");
                // }
                // Navigate to the login page
                driver.get(BASE_URL + "login");
                // Wait for the "veterinario-login" button to be present
                btnVeterinarioLogin = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("veterinario-login")));
                // Scroll to the "veterinario-login" button
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnVeterinarioLogin);
                // Use JavaScript to click the "veterinario-login" button
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnVeterinarioLogin);
                // Assign correct information to the fields
                // Capture the fields of the login form
                inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                inputPasswordVet = driver.findElement(By.id("passwordVet"));
                inputCedulaVet.sendKeys("998877");
                inputPasswordVet.sendKeys("pass123");
                btnIniciarSesionVet = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnVeterinario")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnIniciarSesionVet);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIniciarSesionVet);

                WebElement btnDashboard = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnDashboard")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnDashboard);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDashboard);
                // Comparing the values of the Tratamientos and the Sold Drogas
                long finalNumberOfTratamientos = tratamientoRepository.count();
                long finalNumberOfSoldDrogas = drogaService.obtenerTotalUnidadesVendidas();
                Double finalGanancias = drogaService.obtenerTotalVentas();
                Assertions.assertThat(finalNumberOfTratamientos).isEqualTo(initialNumberOfTratamientos + 1);
                // Assertions.assertThat(finalNumberOfSoldDrogas).isEqualTo(initialNumberOfSoldDrogas
                // + 1);
                // Assertions.assertThat(finalGanancias > initialGanancias).isTrue();
        }

        // @AfterEach
        // public void tearDown() {
        // driver.quit();
        // }

}
