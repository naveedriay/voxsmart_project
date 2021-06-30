package com.voxsmart.step_definitions;

import com.voxsmart.config.LocalWebDriver;
import com.voxsmart.config.PageUtility;
import com.voxsmart.config.ProjectProperties;
import com.voxsmart.page_objects.AbstractPageObject;
import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.voxsmart.config.LocalWebDriver.REAL_DRIVER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class CommonStepDefinition {

    protected Scenario scenario;
    private   String    current_page_url;
    private   String 	authID;
    private AbstractPageObject currentPage;

    @Autowired
    public LocalWebDriver sharedWebDriver;

    public ProjectProperties properties = new ProjectProperties();

    @Autowired
    private PageUtility page_util;

    /**
     * Get a reference to the current cucumber scenario
     * Supports writing text and xml to report within test steps
     */
    @Before
    public void before() throws Throwable {
        authID = "";	current_page_url = "";
        // you can set desired window size here, Mobile: [414,788], Tablet: [1024,768], Desktop: [1280,800]
        REAL_DRIVER.manage().window().setSize(new Dimension(1280,800));
    }
    /**
     * Embed a screenshot in test report if a ui scenario is marked as failed
     */
    @After
    public void embedScreenshotIfFailed(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + REAL_DRIVER.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) REAL_DRIVER).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Throwable somePlatformsDontSupportScreenshotsOrBrowserHasDied) {
                somePlatformsDontSupportScreenshotsOrBrowserHasDied.printStackTrace(System.err);
            }
        }
    }

    @When("^I navigate to (.*) page$")
    public void iGoToAGivenPage(String page_name) throws Throwable {
            REAL_DRIVER.navigate().to(properties.getApplicationBaseUrl());
    }

    @And("I successfully landed on (.*) page$")
    public void iSuccessfullyLandedOnCoinMarketCapHomepagePage(String page_name) throws Throwable {
        currentPage = page_util.getRelativePageObject(page_name);
        System.out.println("Current URL Expected: "+ currentPage.CURRENT_URL);
        currentPage.ensure_current_url(currentPage.CURRENT_URL);
        currentPage.pageShownWithElements();
    }

    @When("^I click the (.*) (tab|link|button)$")
    public void iClickTheElement(String element_name, String type) throws Throwable {
        String element_css = currentPage.getCssElementPath(element_name);
        currentPage.click(currentPage.find(By.cssSelector(element_css)));
    }

    @When("I move mouse over (.*) link")
    public void iMoveMouseOverCertainLink(String element_name) throws Throwable {
        String element_css = currentPage.getCssElementPath(element_name);
        Actions action = new Actions(REAL_DRIVER);
        WebElement we = REAL_DRIVER.findElement(By.cssSelector(element_css));
        action.moveToElement(we).build().perform();
       // action.moveToElement(we).moveToElement(REAL_DRIVER.findElement(By.xpath("/expression-here"))).click().build().perform();
    }

    @Then("^make sure (.*) is (visible|Invisible)$")
    public void makeSureGivenElementIsVisible(String element_name, String option) throws Throwable {
        String element_css = currentPage.getCssElementPath(element_name);
        if(option.equals("visible"))
            assertTrue("Not Found the Element "+ element_css, currentPage.checkElementVisibility(element_css));
        else
            currentPage.checkElementInVisibility(element_css);
    }

    @And("^make sure (.*) is (enabled|disabled)$")
    public void makeSureGivenElementIsEnabled(String element_name, String option) throws Throwable {
        String element_css = currentPage.getCssElementPath(element_name);
        WebElement buttonElement = currentPage.find(By.cssSelector("#input-example button"));
        WebElement textFieldElement = currentPage.find(By.cssSelector(element_css));

        if(option.equals("enabled")) {
            currentPage.wait_until_true_or_timeout(ExpectedConditions.textToBePresentInElement(buttonElement,"Disable"));
            assertTrue(element_css + " is disabled", textFieldElement.isEnabled());
        }
        else {
            currentPage.wait_until_true_or_timeout(ExpectedConditions.textToBePresentInElement(buttonElement,"Enable"));
            assertFalse(element_css + " is enabled", textFieldElement.isEnabled());
        }
    }

    @And("^the title of page reads \"(.*)\"$")
    public void theTitleOfReads( String expected_title) throws Throwable {
        String element_css = currentPage.getCssElementPath("pageTitle");
        String actual_title = currentPage.find(By.cssSelector(element_css)).getText();
        assertTrue("Expected: "+expected_title+", Found: "+actual_title, expected_title.equalsIgnoreCase(actual_title));
    }


    @Then("make sure there are {int} records on the page")
    public void makeSureThereAreRecordsOnThePage(int expected_records) throws Throwable {

        List<WebElement> currency_list = currentPage.getDriver().findElements(By.cssSelector(".cmc-table tbody tr"));
        assertTrue("Expected: 100, Found "+currency_list.size(), currency_list.size()==expected_records);
    }

}
