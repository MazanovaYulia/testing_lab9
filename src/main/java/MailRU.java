import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MailRU {
    public String user = System.getProperty("user.dir");
    @Test
    public void Zadanie() throws InterruptedException {
        System.setProperty("WebDriver.Chromedriver",user+"/drivers/chromedriver-mac-arm64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mail.ru/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Находим и нажимаем на кнопку "Вход"
        driver.findElement(By.xpath("//button[@class=\"ph-login svelte-ttryjx\"]")).click();

        // Находим модальное окно
        WebElement iframeElement = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(iframeElement);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // Ввод логина и нажимаем на кнопку вход
        driver.findElement(By.xpath("//*[@class='input-0-2-71']")).sendKeys("professionaltester");
        driver.findElement(By.xpath("//*[@class='base-0-2-79 primary-0-2-93']")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        driver.findElement(By.xpath("//button[@class=\"base-0-2-79 fluid-0-2-86\"]")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        driver.findElement(By.xpath("//button[@class=\"base-0-2-79 large-0-2-87\"]")).click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());

        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("id.vk.com"));
        driver.findElement(By.xpath("//input[@class='vkuiTypography vkuiInput__el vkuiText vkuiText--sizeY-none']")).sendKeys("toptester123");
        driver.findElement(By.xpath("//*[@class='inner-0-2-81 innerTextWrapper-0-2-82']")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // Нажимаем на почту
        driver.findElement(By.xpath("//*[@class='ph-avatar-img svelte-dfhuqc']")).click();

        // Проверяем имя почты, выход из акаунта и проверка элемента "Создать Почту"
        Assert.assertEquals("sss sss",driver.findElement(By.xpath("//*[@class='ph-name svelte-1popff4']")).getText());
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        driver.findElement(By.xpath("//*[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big cea-fefe-ehxscg']")).isDisplayed();


        driver.quit();
    }
}
