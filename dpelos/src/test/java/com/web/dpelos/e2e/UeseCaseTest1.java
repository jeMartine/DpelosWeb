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
public class UeseCaseTest1 {

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

    @Test
    public void SystemTest_UseCase1() {
        // Get the initial count of Mascota entities
        long initialCount = mascotaRepository.count();
        System.out.println("Initial count of Mascota entities: " + initialCount);
        long duenoCount = duenoRepository.count();
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

        // Assign incorrect information to the fields
        inputCedulaVet.sendKeys("wrongCedula");
        inputPasswordVet.sendKeys("wrongPassword");

        // Get the login button and click it
        WebElement btnIniciarSesionVet = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnVeterinario")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnIniciarSesionVet);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIniciarSesionVet);

        // Wait for the error message to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toast-container")));

        // Clear the incorrect information
        inputCedulaVet.clear();
        inputPasswordVet.clear();

        // Assign correct information to the fields
        inputCedulaVet.sendKeys("987654321");
        inputPasswordVet.sendKeys("password456");

        // Click the login button again
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnIniciarSesionVet);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIniciarSesionVet);

        // Wait for the "btnRegistrarMascota" button to be present
        WebElement btnRegistrarMascota = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnRegistrarMascota")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnRegistrarMascota);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnRegistrarMascota);
        // Wait for the "btnAgregarDueno" button to be present
        WebElement btnAgregarDueno = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgregarDueno")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnAgregarDueno);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnAgregarDueno);

        // Wait for the "cedulaDueno" input to be present
        WebElement inputCedulaDueno = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaDueno")));
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
        WebElement btnRegistrarDueno = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnRegistrarDueno")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnRegistrarDueno);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnRegistrarDueno);
        // Correcting the information
        inputApellidoDueno.clear();
        inputApellidoDueno.sendKeys("Albarracin");
        // Get the Registrar Dueno button and click it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnRegistrarDueno);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnRegistrarDueno);

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
        WebElement razaDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("razaMascota")));
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
        WebElement btnAgregarRegistrarMascota = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnRegistrarMascota")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                btnAgregarRegistrarMascota);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                btnAgregarRegistrarMascota);

        // // Wait for the "btnVerMascotas" button to be present and click it
        // WebElement btnVerMascotas = wait
        // .until(ExpectedConditions.presenceOfElementLocated(By.id("btnVerMascotas")));
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", btnVerMascotas);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
        // btnVerMascotas);

        // // Wait for the "search-input" input to be present
        // WebElement inputSearchMascota =
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-input")));
        // // Assign correct information to the search input
        // inputSearchMascota.sendKeys("Pagani");

        // // Wait for the "btnBuscarMascota" button to be present and click it
        // WebElement btnSearchMascota = wait
        // .until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchMascota")));
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);",
        // btnSearchMascota);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
        // btnSearchMascota);
        // Assert that the new count is equal to the initial count plus 1

        // Login as a Dueno
        driver.get(BASE_URL + "login");
        // Typing the cedula in the input field.
        WebElement inputCedulaCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaDueno")));
        inputCedulaCliente.sendKeys("1029641463");
        // Getting the login button and clicking it.
        WebElement btnIniciarSesionDueno = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("btnDueno")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnIniciarSesionDueno);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIniciarSesionDueno);

        // Get the new count of Mascota entities
        long newCount = mascotaRepository.count();
        Assertions.assertThat(newCount).isEqualTo(initialCount + 1);
        // Retrieve all Mascota entities associated with the Dueno
        Dueno dueno = duenoRepository.findByCedulaDueno("1029641463");
        Collection<Mascota> mascotas = mascotaRepository.findByIdDueno(dueno);
        // Assert that the retrieved Mascota's name is equal to the one created in the
        // test
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas.size()).isEqualTo(1);
        Assertions.assertThat(mascotas.iterator().next().getNombreMascota()).isEqualTo("Pagani");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
