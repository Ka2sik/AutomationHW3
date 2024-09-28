package ru.netology.webselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTestsForBankForm {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void shouldShowErrorIfSendEmptyCustomerName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79269262626");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldShowErrorIfSendCustomerNameWithWhiteSpace() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79269262626");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldShowErrorIfSendCustomerNameInLatin() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ivan Vasilev");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79269262626");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendCustomerNameWithNumbers() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Вася123");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79269262626");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendCustomerNameWithSymbols() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Вася;%^");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79269262626");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendEmptyTelephone() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldShowErrorIfSendTelephoneWithWhiteSpace() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("  ");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldShowErrorIfSendTelephoneWithLetters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Телефон");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendTelephoneWithSymbols() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+790123@567&");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendTelephoneWith10Numbers() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7901234567");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldShowErrorIfSendTelephoneWith12Numbers() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+790123456789");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid .input__sub")).isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldChangeTextToRedIfSendWithoutAgreement() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий Иванович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+790123456789");
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector(".checkbox__text")).getText();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid")).isDisplayed());
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и " +
                "разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }

}
