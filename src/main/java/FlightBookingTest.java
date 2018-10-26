import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BaseClass {

    @BeforeMethod
    private void setUp() {
        setDriver();
    }


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(5000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(5000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        List<WebElement> listOfRows = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr"));
        int noOfRows = listOfRows.size();
        List<WebElement> listOfCol = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + noOfRows + "]/td[@data-event='click']"));
        int noOfCol = listOfCol.size();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + noOfRows + "]/td[" + noOfCol + "]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }

    @AfterMethod
    private void tearDown() {
        quitDriver();
    }
}
