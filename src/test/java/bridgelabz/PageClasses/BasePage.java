package bridgelabz.PageClasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    public static Logger log = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ================= WAIT METHODS =================

    public void waitForElementVisible(WebElement element, int time) {
        log.info("Waiting for element to be visible: " + element);
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.visibilityOf(element));
        log.info("Element is visible now: " + element);
    }

    public void waitForClickable(WebElement element, int time) {
        log.info("Waiting for element to be clickable: " + element);
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.elementToBeClickable(element));
        log.info("Element is clickable now: " + element);
    }

    // ================= SEND KEYS =================

    public void sendKeys(WebElement element, String text) {
        try {
            waitForElementVisible(element, 10);
            log.info("Clearing text from: " + element);
            element.clear();
            log.info("Entering text '" + text + "' into: " + element);
            element.sendKeys(text);
        } catch (Exception e) {
            log.error("Failed to send keys to element: " + element, e);
        }
    }

    // ================= CLICK METHODS =================

    public void click(WebElement element) {
        try {
            waitForClickable(element, 10);
            log.info("Clicking on element: " + element);
            element.click();
        } catch (Exception e) {
            log.warn("Normal click failed. Trying JavaScript click: " + element);
            clickUsingJS(element);
        }
    }

    public void clickUsingJS(WebElement element) {
        try {
            log.info("Performing JavaScript click on: " + element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            log.error("JS Click failed on element: " + element, e);
        }
    }

    // ================= CONDITIONAL METHODS =================

    public boolean isElementDisplayed(WebElement element) {
        try {
            boolean flag = element.isDisplayed();
            log.info("Is element displayed? " + flag + " --> " + element);
            return flag;
        } catch (Exception e) {
            log.warn("Element not displayed: " + element);
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        try {
            boolean flag = element.isEnabled();
            log.info("Is element enabled? " + flag + " --> " + element);
            return flag;
        } catch (Exception e) {
            log.warn("Element not enabled: " + element);
            return false;
        }
    }

    public boolean isElementSelected(WebElement element) {
        try {
            boolean flag = element.isSelected();
            log.info("Is element selected? " + flag + " --> " + element);
            return flag;
        } catch (Exception e) {
            log.warn("Element not selected: " + element);
            return false;
        }
    }
     
}
