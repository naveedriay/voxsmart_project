package com.voxsmart.page_objects;

import com.voxsmart.config.LocalWebDriver;
import com.voxsmart.config.ProjectProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Scope("cucumber-glue")
public abstract class AbstractPageObject {

    public            String          path;
    protected final   WebDriver       driver;
    public    final   int             waitTimeOutSeconds;
    public 	  static  String 	      CURRENT_URL = "";


    private static final Logger log = (Logger) Logger.getLogger("AbstractPageObject");

    public ProjectProperties properties = new ProjectProperties();

    public AbstractPageObject() {
        this.path = properties.getApplicationBaseUrl();
        this.driver = LocalWebDriver.REAL_DRIVER;
        this.waitTimeOutSeconds = properties.getSeleniumWaitTimeOutSeconds();
    }

    public abstract void   pageShownWithElements() throws InterruptedException;
    public abstract String getCssElementPath(String element_name )throws  ClassNotFoundException, NoSuchFieldException, IllegalAccessException;

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public int getTimeout() {
        return waitTimeOutSeconds;
    }

    public void goToAndWait() {
        log.info("Going to path: " + path);
        driver.navigate().to(path);
    }

    public void ensure_current_url(String url_path) {
        String loaded_page_url = driver.getCurrentUrl();
        wait_until_true_or_timeout(ExpectedConditions.urlContains(url_path));
    }

    // wait until condition is true or timeout is reached
    public <V> V wait_until_true_or_timeout(ExpectedCondition<V> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, waitTimeOutSeconds)
                .ignoring(StaleElementReferenceException.class);
        try {
            return wait.until(isTrue);
        } catch (TimeoutException rte) {
            throw new TimeoutException(rte.getMessage());
        }
    }
    public WebElement find(By locator) {
        try {
            wait_until_true_or_timeout(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }
    public void click(WebElement element) {
        log.info("Clicking on WebElement " + element.toString());
        element.click();
    }
    public boolean checkElementVisibility(String element_css_path) throws InterruptedException{
        boolean found = false;
        for(int i=0;(i<20 && !found); i++) {
            found = driver.findElements(By.cssSelector(element_css_path)).size() >= 1; // checks if element exists ( 1 or more)
            Thread.sleep(1000);
        }
        return found;
    }
    public void checkElementInVisibility(String element_css) {
        wait_until_true_or_timeout(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(element_css)));
    }

    public void scrollCurrentPageTo(String option){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        if(option.equalsIgnoreCase("bottom")){
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        }
        else { // Scroll back to Top of page.
            jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        }
    }
}
