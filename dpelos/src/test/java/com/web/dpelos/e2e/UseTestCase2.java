package com.web.dpelos.e2e;

import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.hibernate.sql.results.graph.AssemblerCreationState;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.web.dpelos.service.DrogaService;
import com.web.dpelos.service.DrogaServiceImplementation;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UseTestCase2 {
        @Autowired
        private DrogaServiceImplementation drogaService;
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

        /*
         * Test Case Description:
         * El veterinario va a suministrar un nuevo medicamento a una mascota. Busca la
         * mascota en la barra de búsqueda, la selecciona para darle un nuevo
         * tratamiento.  selecciona tratamiento y da en guardar. Para verificar que todo
         * salió bien, busca la mascota, ve sus detalles y se da cuenta que el nuevo
         * tratamiento quedó guardado de manera correcta.
         * 
         * Después se ingresa con el perfil de administrador y que la cantidad de
         * medicamentos suministrados y las ganancias sean correctas.
         */
        @Test
        public void SystemTest_UseCase2() {
                /* Variable used for saving the initial number of drogas sold. */
                Long initialUnitsSoldDrogas = drogaService.obtenerTotalUnidadesVendidas();
                // Navigate to the login page
                driver.get(BASE_URL + "login");
                /* Entering as the Admin for the 1st time */
                // Capture the fields of the login form
                WebElement inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                WebElement inputPasswordVet = driver.findElement(By.id("passwordVet"));
                inputCedulaVet.sendKeys("998877");
                inputPasswordVet.sendKeys("pass123");
                // Get the login button and click it
                safeClick(By.id("btnVeterinario"));
                // Wait for the "btnDashboard" button to be present and click it
                safeClick(By.id("btnDashboard"));
                // Extracting the initial values
                // Locate the element with id "totalTratamientos" and retrieve its text content
                WebElement totalTratamientosElement = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("totalTratamientos")));
                wait.until(ExpectedConditions.textToBePresentInElement(totalTratamientosElement,
                                "Tratamientos en el último mes:"));
                String totalTratamientosText = totalTratamientosElement.getText().replaceAll("[^0-9]", "");
                int totalTratamientosInitial = 0;
                if (!totalTratamientosText.isEmpty()) {
                        totalTratamientosInitial = Integer.parseInt(totalTratamientosText);
                }

                // Locate the element with id "totalGanancias" and retrieve its text content
                WebElement totalGananciasElement = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("totalGanancias")));
                wait.until(ExpectedConditions.textToBePresentInElement(totalGananciasElement,
                                "Total de Ganancias de la Veterinaria:"));
                String totalGananciasText = totalGananciasElement.getText().replaceAll("[^0-9]", ""); // Remove all
                                                                                                      // non-numeric
                                                                                                      // characters
                double totalGananciasInitial = 0.0;
                if (!totalGananciasText.isEmpty()) {
                        totalGananciasInitial = Double.parseDouble(totalGananciasText);
                }
                // Navigate to the login page to enter as a Veterinario
                driver.get(BASE_URL + "login");

                // Wait for the "veterinario-login" button to be present and click it
                safeClick(By.id("veterinario-login"));

                // Capture the fields of the login form
                inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                inputPasswordVet = driver.findElement(By.id("passwordVet"));
                inputCedulaVet.sendKeys("998877");
                inputPasswordVet.sendKeys("pass123");

                // Get the login button and click it
                safeClick(By.id("btnVeterinario"));

                // Wait for the "btnDashboard" button to be present and click it
                safeClick(By.id("btnDashboard"));

                // Navigate to the login page
                driver.get(BASE_URL + "login");

                // Wait for the "veterinario-login" button to be present and click it
                safeClick(By.id("veterinario-login"));

                // Capture the fields of the login form
                inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                inputPasswordVet = driver.findElement(By.id("passwordVet"));

                // Assign correct information to the fields
                inputCedulaVet.sendKeys("741258963");
                inputPasswordVet.sendKeys("password505");

                // Get the login button and click it
                safeClick(By.id("btnVeterinario"));

                // Wait for the "btnVerMascotas" button to be present and click it
                safeClick(By.id("btnVerMascotas"));

                // Wait for the "search-input" input to be present
                WebElement inputSearchMascota = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("search-input")));

                // Assign correct information to the search input
                inputSearchMascota.sendKeys("Firulais");

                // Wait for the "btnBuscarMascota" button to be present and click it
                safeClick(By.id("btnSearchMascota"));

                // Wait for the "cardMascota" element to be present and click it
                safeClick(By.id("cardMascota"));

                // Getting elements from the form for assigning a Tratamiento to a Mascota
                WebElement inputMotivoTratamiento = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("motivoTratamiento")));
                WebElement inputRecomendacionesGenerales = driver.findElement(By.id("recomendacionesGenerales"));
                inputMotivoTratamiento.sendKeys("A la mascota le Lele pancha.");
                inputRecomendacionesGenerales.sendKeys("Dar mucho amor.");

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
                // Accesing the Tratamiento
                safeClick(By.id("btnVerTratamientoActivo"));
                // Clicking on Resumen
                safeClick(By.id("btnResumenTratamiento"));
                // Clicking on Finalizar Tratamiento
                safeClick(By.id("btnFinalizarTratamiento"));
                /* Entering as the Admin for the 2nd time */
                // Navigate to the login page
                driver.get(BASE_URL + "login");

                // Wait for the "veterinario-login" button to be present and click it
                safeClick(By.id("veterinario-login"));

                // Capture the fields of the login form
                inputCedulaVet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaVet")));
                inputPasswordVet = driver.findElement(By.id("passwordVet"));
                inputCedulaVet.sendKeys("998877");
                inputPasswordVet.sendKeys("pass123");

                // Get the login button and click it
                safeClick(By.id("btnVeterinario"));

                // Wait for the "btnDashboard" button to be present and click it
                safeClick(By.id("btnDashboard"));

                // Locate the element with id "totalTratamientos" and retrieve its text content
                totalTratamientosElement = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("totalTratamientos")));
                wait.until(ExpectedConditions.textToBePresentInElement(totalTratamientosElement,
                                "Tratamientos en el último mes:"));
                totalTratamientosText = totalTratamientosElement.getText().replaceAll("[^0-9]", "");
                int totalTratamientosFinal = 0;
                if (!totalTratamientosText.isEmpty()) {
                        totalTratamientosFinal = Integer.parseInt(totalTratamientosText);
                }

                // Locate the element with id "totalGanancias" and retrieve its text content
                totalGananciasElement = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("totalGanancias")));
                wait.until(ExpectedConditions.textToBePresentInElement(totalGananciasElement,
                                "Total de Ganancias de la Veterinaria:"));
                totalGananciasText = totalGananciasElement.getText().replaceAll("[^0-9.]",
                                "");

                double totalGananciasFinal = 0.0;
                if (!totalGananciasText.isEmpty()) {
                        totalGananciasFinal = Double.parseDouble(totalGananciasText);
                }

                /* Verifying that the values actually change */
                Assertions.assertThat(totalTratamientosFinal).isEqualTo(totalTratamientosInitial
                                + 1);
                Assertions.assertThat(totalGananciasFinal).isGreaterThan(totalGananciasInitial);
                // Variable used for saving the final number of drogas sold.
                Long finalUnitsSoldDrogas = drogaService.obtenerTotalUnidadesVendidas();
                Assertions.assertThat(finalUnitsSoldDrogas).isGreaterThan(initialUnitsSoldDrogas);
        }

        /* Closes the browser after have finished the test. */
        @AfterEach
        public void tearDown() {
                driver.quit();
        }

}
