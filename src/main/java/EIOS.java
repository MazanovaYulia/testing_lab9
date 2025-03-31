import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class EIOS {
    public String user = System.getProperty("user.dir");
    private WebDriver driver;
    private WebDriverWait wait;
    @Test
    public void Zadanie() throws InterruptedException {
        System.setProperty("WebDriver.Chromedriver",user+"/drivers/chromedriver-mac-arm64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://eios.kemsu.ru/a/eios");
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']")));
        loginField.clear();
        passwordField.clear();
        loginField.sendKeys("stud71633");
        passwordField.sendKeys("yuliaPI211lab9");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        WebElement myFIOLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'css-10pdxt6') and contains(@class, 'efn4aem0')]")));
        String FIO = myFIOLink.getText();
        assertEquals("Межецкая Ю.Н.", FIO);
        System.out.println("Личный кабинет EIOS - Межецкая Ю.Н.");
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'css-1oslnw8') and contains(@class, 'efn4aem0')]")));
        logoutButton.click();

        wait.until(ExpectedConditions.urlToBe("https://eios.kemsu.ru/a/eios"));
        System.out.println("Выход выполнен успешно");
    }
    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(1000);
            driver.quit();
        }
    }
}
