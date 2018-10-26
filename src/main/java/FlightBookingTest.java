import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BaseClass {

    @FindBy(id = "OneWay")
    private WebElement oneWayButton;

    @FindBy(id = "FromTag")
    private WebElement fromDropdown;

    @FindBy(id = "ui-id-1")
    private WebElement fromAutoFill;

    @FindBy(id = "ToTag")
    private WebElement toDropdown;

    @FindBy(id = "ui-id-2")
    private WebElement toAutoFill;

    @FindBy(id = "SearchBtn")
    private WebElement searchButton;

    @BeforeMethod
    private void setUp() {
        setDriver();
        PageFactory.initElements(driver, this);
    }


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        oneWayButton.click();

        fromDropdown.clear();
        fromDropdown.sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(5000);
        List<WebElement> originOptions = fromAutoFill.findElements(By.tagName("li"));
        originOptions.get(0).click();

        toDropdown.clear();
        toDropdown.sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(5000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = toAutoFill.findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        // finding number of rows for dynamic calendar selection
        List<WebElement> listOfRows = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr"));
        int noOfRows = listOfRows.size();

        // finding number of columns for dynamic calendar selection
        List<WebElement> listOfCol = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + noOfRows + "]/td[@data-event='click']"));
        int noOfCol = listOfCol.size();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + noOfRows + "]/td[" + noOfCol + "]/a")).click();

        //all fields filled in. Now click on search
        searchButton.click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }

    @AfterMethod
    private void tearDown() {
        quitDriver();
    }
}
