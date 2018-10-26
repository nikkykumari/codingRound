import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseClass {

    @BeforeMethod
    private void setUp() {
        setDriver();
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        driver.switchTo().frame(driver.findElement(By.id("modal_window")));
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
    }

    @AfterMethod
    private void tearDown() {
        quitDriver();
    }
}
