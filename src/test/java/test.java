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

        prop.load(new FileInputStream("src/main/resources/configEMU.properties"));

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

    @Test
    public void basicTest () throws InterruptedException {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.consultantplus.app:id/search_edit"))).sendKeys(prop.getProperty("firstSearch"));
        ((AndroidDriver)driver).pressKeyCode(66);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.findElementById("");
       // MobileElement el3 = driver.findElementById("com.consultantplus.app:id/left_button");
       // el3.click();
        //Thread.sleep(3000);

        while (isElementPresent(By.id("com.consultantplus.app:id/retry_btn")) == true) {
            driver.findElement(By.id("com.consultantplus.app:id/retry_btn")).click();
        }
        MobileElement testel = driver.findElementById("com.consultantplus.app:id/search_results_title");
        String NalogText = testel.getText();
        testel.click();
        //Thread.sleep(5000);
        while (isElementPresent(By.id("com.consultantplus.app:id/retry_btn")) == true) {
            driver.findElement(By.id("\tcom.consultantplus.app:id/retryprogress_view_retry")).click();
        }
        MobileElement Head = driver.findElement(By.xpath("//android.view.View[@content-desc='НАЛОГОВЫЙ']"));
        //MobileElement SecondHead = driver.findElement(By.xpath("//android.view.View[@content-desc='КОДЕКС РОССИЙСКОЙ ФЕДЕРАЦИИ']"));
        //MobileElement Who = driver.findElement(By.xpath("//android.view.View[@content-desc='ЧАСТЬ ПЕРВАЯ']"));
        //(Head.getText() + SecondHead.getText() + Who.getText()).toLowerCase().equals("налоговый кодекс российской федерации");

        MobileElement el7 = driver.findElement(By.id("com.consultantplus.app:id/search_edit"));
        String Textin = el7.getText();
        el7.clear();
        el7.sendKeys(prop.getProperty("secondSearch"));
        ((AndroidDriver)driver).pressKeyCode(66);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        MobileElement el11 = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc='Статья 27. ']"));
        boolean eba = el11.isDisplayed();



        //
        driver.findElement(By.xpath("//*[@resource-id='com.consultantplus.app:id/list_item_entry_text']"));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView")));
        //MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView");
        //el4.click();
        MobileElement el77 = (MobileElement) driver.findElementByAccessibilityId("Поиск по тексту");
        el77.click();
        el77.sendKeys("Законный представитель налогоплательщика");
        MobileElement el88 = (MobileElement) driver.findElementByAccessibilityId("Поиск по тексту");
        el88.click();
        MobileElement el9 = (MobileElement) driver.findElementById("com.consultantplus.app:id/search_edit");
        el9.sendKeys("Законный представитель налогоплательщика");
        MobileElement el10 = (MobileElement) driver.findElementById("com.consultantplus.app:id/button_next");
        el10.click();





        //Click and pass Tutorial
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/btn_skip"))).click();

        //Click to "Is Ariyorum" Button
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/bluecollar_type_button"))).click();


        //Notification Allow
        if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size()>0) {
            driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
        }

        //Click second job
       // wait.until(ExpectedConditions.visibilityOfElementLocated
        //        (By.xpath(secondNewJob))).click();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    protected boolean isElementPresent(By by) {
        try
        {
            driver.findElement(by);
            return true;

        } catch (org.openqa.selenium.NoSuchElementException e) {

            return false;
        }
    }

}