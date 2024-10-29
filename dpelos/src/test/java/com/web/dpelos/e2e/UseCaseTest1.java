package com.web.dpelos.e2e;

import java.time.Duration;
import java.util.*;

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
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UseCaseTest1 {

        @Autowired
        private MascotaRepository mascotaRepository;
        @Autowired
        private DuenoRepository duenoRepository;

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
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

        /*
         * Test Case Description:
         * Llega un usuario nuevo a la veterinaria con su mascota. El veterinario que
         * está disponible en ese momento intenta ingresar con su usuario y contraseña
         * al sistema. Este se equivoca la primera vez, sin embargo, al segundo intento
         * logra ingresar sin ningún problema. Va a la sección de registro de clientes,
         * pide los datos y da en el botón de “registrar”. Nuevamente el veterinario se
         * equivoca en uno de los campos, corrige e intenta nuevamente. El cliente queda
         * registrado. Posteriormente va a la sección de mascotas y registra la mascota
         * asociándola al dueño. Esta vez todo sale bien a la primera.
         * 
         * El veterinario pide al dueño que ingrese con su cedula a través del portal de
         * clientes. Este ingresa y ve que los datos de la mascota son correctos. Se
         * acaba la prueba.
         */
        @Test
        public void SystemTest_UseCase1() {
                // Get the initial count of Mascota entities
                long initialCount = mascotaRepository.count();
                System.out.println("Initial count of Mascota entities: " + initialCount);
                long duenoCount = duenoRepository.count();

                // Navigate to the login page
                driver.get(BASE_URL + "login");

                // Wait for the "veterinario-login" button to be present and click it
                safeClick(By.id("veterinario-login"));

                // Capture the fields of the login form
                WebElement inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                WebElement inputPasswordVet = driver.findElement(By.id("passwordVet"));

                // Assign incorrect information to the fields
                inputCedulaVet.sendKeys("wrongCedula");
                inputPasswordVet.sendKeys("wrongPassword");

                // Get the login button and click it
                safeClick(By.id("btnVeterinario"));

                // Wait for the error message to appear
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toast-container")));

                // Clear the incorrect information
                inputCedulaVet.clear();
                inputPasswordVet.clear();

                // Assign correct information to the fields
                inputCedulaVet.sendKeys("987654321");
                inputPasswordVet.sendKeys("password456");

                // Click the login button again
                safeClick(By.id("btnVeterinario"));

                // Wait for the "btnRegistrarMascota" button to be present and click it
                safeClick(By.id("btnRegistrarMascota"));

                // Wait for the "btnAgregarDueno" button to be present and click it
                safeClick(By.id("btnAgregarDueno"));

                // Wait for the "cedulaDueno" input to be present
                WebElement inputCedulaDueno = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaDueno")));
                WebElement inputNombreDueno = driver.findElement(By.id("nombreDueno"));
                WebElement inputApellidoDueno = driver.findElement(By.id("apellidoDueno"));
                WebElement inputCorreoDueno = driver.findElement(By.id("correoDueno"));
                WebElement inputCelularDueno = driver.findElement(By.id("celularDueno"));
                WebElement inputURLFotoDueno = driver.findElement(By.id("fotoUrl"));

                // Assign correct information to the Registrar Dueno fields
                inputCedulaDueno.sendKeys("1029641463");
                inputNombreDueno.sendKeys("Diego");
                inputApellidoDueno.sendKeys("Albarrac1n");
                inputCorreoDueno.sendKeys("diego@gmail.com");
                inputCelularDueno.sendKeys("3115123796");
                inputURLFotoDueno.sendKeys(
                                "https://media.licdn.com/dms/image/v2/D4E03AQF0sXyuzyiN8g/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1718231100540?e=1735776000&v=beta&t=IUS8ZRAGMAapj31SUKD5mz1VEAJ2svTbQH94Rro0yRI");

                // Get the Registrar Dueno button and click it
                safeClick(By.id("btnRegistrarDueno"));

                // Correcting the information
                inputApellidoDueno.clear();
                inputApellidoDueno.sendKeys("Albarracin");

                // Get the Registrar Dueno button and click it again
                safeClick(By.id("btnRegistrarDueno"));

                // Get the new count of Dueno entities
                long newDuenoCount = duenoRepository.count();
                // Assert that the new count is equal to the initial count plus 1
                Assertions.assertThat(newDuenoCount).isEqualTo(duenoCount + 1);

                // Getting the elements inside the form Crear Mascota.
                WebElement inputCedulaDuenoMascota = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaDueno")));
                WebElement inputNombreMascota = driver.findElement(By.id("nombreMascota"));
                WebElement inputEdadMascota = driver.findElement(By.id("edadMascota"));
                WebElement inputUrlFotoMascota = driver.findElement(By.id("urlFotoMascota"));
                WebElement razaDropdown = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.name("razaMascota")));
                WebElement enfermedadDropdown = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.name("enfermedadMascota")));

                // Assign correct information to the Crear Mascota fields
                inputCedulaDuenoMascota.sendKeys("1029641463");
                inputNombreMascota.sendKeys("Pagani");
                inputEdadMascota.sendKeys("2");
                inputUrlFotoMascota.sendKeys("https://i.pinimg.com/564x/dd/da/89/ddda899cbe2e18766ca1797806efe8b8.jpg");
                Select selectRaza = new Select(razaDropdown);
                selectRaza.selectByVisibleText("Labrador");
                Select selectEnfermedad = new Select(enfermedadDropdown);
                selectEnfermedad.selectByVisibleText("Parvovirus");

                // Get the Registrar Mascota button and click it
                safeClick(By.id("btnRegistrarMascota"));

                // Login as a Dueno
                driver.get(BASE_URL + "login");

                // Typing the cedula in the input field.
                WebElement inputCedulaCliente = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaDueno")));
                inputCedulaCliente.sendKeys("1029641463");

                // Getting the login button and clicking it.
                safeClick(By.id("btnDueno"));

                // Get the new count of Mascota entities
                long newCount = mascotaRepository.count();
                Assertions.assertThat(newCount).isEqualTo(initialCount + 1);

                // Retrieve all the mascota elements inside the carousel
                List<WebElement> mascotaElements = driver.findElements(By.cssSelector(".card-mascota .img-caption p"));

                // Check that each mascota's name and raza match the expected values
                for (WebElement mascotaElement : mascotaElements) {
                        String nombreMascota = mascotaElement.getText();

                        // Click the mascota element to display the info-mascota div
                        // mascotaElement.click();
                        safeClick(By.id("btnImg-mascota"));
                        // Wait for the info-mascota div to be present
                        WebElement infoMascotaDiv = wait
                                        .until(ExpectedConditions.presenceOfElementLocated(By.id("info-mascota")));

                        // Retrieve the razaMascota element inside the info-mascota div
                        WebElement razaElement = infoMascotaDiv.findElement(By.id("razaMascota"));
                        String razaMascota = razaElement.getText();

                        Assertions.assertThat(nombreMascota).isEqualTo("Pagani");
                        Assertions.assertThat(razaMascota).isEqualTo("Labrador");
                }
        }

        /* Closes the browser after have finished the test. */
        @AfterEach
        public void tearDown() {
                driver.quit();
        }

}
