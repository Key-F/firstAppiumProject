import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import java.util.Properties;
import java.io.FileInputStream;

public class test {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    Properties prop = new Properties();



    @BeforeMethod
    public void setup () throws java.io.IOException {

        prop.load(new FileInputStream("src/main/resources/configREAL.properties"));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", prop.getProperty("deviceName"));
        caps.setCapability("udid", prop.getProperty("udid")); //Id устройства, получаемое командой "adb devices"
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "6.0");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", prop.getProperty("appPackage"));
        caps.setCapability("appActivity",prop.getProperty("appActivity"));
        caps.setCapability("noReset","false");
        caps.setCapability("unicodeKeyboard", "true");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test // [UT.1]
    public void docSearchTest () throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (isElementPresent(By.id("com.consultantplus.app:id/button_intro_prev")) == true) { // Если есть приветствие
            MobileElement el1 = (MobileElement) driver.findElementById("com.consultantplus.app:id/button_intro_prev");
            el1.click(); // Пропускаем вступительную часть
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.consultantplus.app:id/search_edit"))).sendKeys(prop.getProperty("firstSearch")); // Ввод первого запроса
        ((AndroidDriver)driver).pressKeyCode(66);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.consultantplus.app:id/search_results_title")));
        MobileElement testel = driver.findElementById("com.consultantplus.app:id/search_results_title"); // Выбор первого результата
        testel.click();

        // Проверка наличия всех необходимых элементов
        driver.findElement(By.xpath("//android.view.View[@content-desc='НАЛОГОВЫЙ']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc='КОДЕКС']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc=' РОССИЙСКОЙ ФЕДЕРАЦИИ']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc='ЧАСТЬ']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc='ПЕРВАЯ']"));

    }

    @Test // [UT.2]
    public void searchStringTest () throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (isElementPresent(By.id("com.consultantplus.app:id/button_intro_prev")) == true) { // Если есть приветствие
            MobileElement el1 = (MobileElement) driver.findElementById("com.consultantplus.app:id/button_intro_prev");
            el1.click(); // Пропускаем вступительную часть
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.consultantplus.app:id/search_edit"))).sendKeys(prop.getProperty("firstSearch")); // Ввод первого запроса
        ((AndroidDriver)driver).pressKeyCode(66);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.consultantplus.app:id/search_results_title")));
        MobileElement testel = driver.findElementById("com.consultantplus.app:id/search_results_title"); // Выбор первого результата
        testel.click();

        MobileElement searchstring = driver.findElement(By.id("com.consultantplus.app:id/search_edit")); // Поиск строки поиска
        String Textin = searchstring.getText();

        Assert.assertEquals(Textin, prop.getProperty("firstSearch"));

    }

    @Test // [UT.3]
    public void searchInTextTest () throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (isElementPresent(By.id("com.consultantplus.app:id/button_intro_prev")) == true) { // Если есть приветствие
            MobileElement el1 = (MobileElement) driver.findElementById("com.consultantplus.app:id/button_intro_prev");
            el1.click(); // Пропускаем вступительную часть
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.consultantplus.app:id/search_edit"))).sendKeys(prop.getProperty("firstSearch")); // Ввод первого запроса
        ((AndroidDriver)driver).pressKeyCode(66);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.consultantplus.app:id/search_results_title")));
        MobileElement testel = driver.findElementById("com.consultantplus.app:id/search_results_title"); // Выбор первого результата
        testel.click();

        MobileElement searchstring = driver.findElement(By.id("com.consultantplus.app:id/search_edit")); // Поиск поисковой строки
        searchstring.clear();
        searchstring.sendKeys(prop.getProperty("secondSearch")); // Выполнение второго запроса
        ((AndroidDriver)driver).pressKeyCode(66);

        // Проверка наличия необходимого текста
        driver.findElement(By.xpath("//android.view.View[@content-desc='Статья 27. ']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc='Законный']"));
        driver.findElement(By.xpath("//android.view.View[@content-desc='представитель']"));
        driver.findElement(By.xpath("(//android.view.View[@content-desc='налогоплательщика'])[1]"));

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    protected boolean isElementPresent(By by) {  // Метод для проверки наличия элемента
        try
        {
            driver.findElement(by);
            return true;

        } catch (org.openqa.selenium.NoSuchElementException e) {

            return false;
        }
    }

}