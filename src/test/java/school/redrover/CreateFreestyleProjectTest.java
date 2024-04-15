package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        final String expectedProjectName = "new Freestyle project";

        getDriver().findElement(By.xpath("//*[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).clear();
        getDriver().findElement(By.id("name")).sendKeys(expectedProjectName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        String newProjectName = getDriver().findElement(By.tagName("h1")).getText();

        Assert.assertEquals(newProjectName, expectedProjectName);

    }

    @Test
    public void testCreatingFreestyleInvalidChar() {

        String[] invalidCharacters = {"!", "@", "#", "$", "%", "^", "&", "*", "?", "|", "/", "["};

        getDriver().findElement(By.xpath("//a[@it='hudson.model.Hudson@72b00a0']")).click();

        for (String invalidChar : invalidCharacters) {
            getDriver().findElement(By.xpath("//*[@class='jenkins-input']")).clear();
            getDriver().findElement(By.xpath("//*[@class='jenkins-input']")).sendKeys(invalidChar);

            String actualResult = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"))
                    .getText();
            String expectedResult = "» ‘" + invalidChar + "’ is an unsafe character";
            Assert.assertEquals(actualResult, expectedResult);

            boolean okButton = getDriver().findElement(By.xpath("//button[@type='submit']")).isEnabled();
            Assert.assertFalse(okButton);
        }
    }

    @Test
    public void testFreestyleProjectCreate() {
        getDriver().findElement(By.xpath("//a[@it='hudson.model.Hudson@72b00a0']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Project8");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        WebElement nameOfProject = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));

        String actualResult = nameOfProject.getText();
        String expectedResult = "Project8";

        Assert.assertEquals(actualResult, expectedResult);
    }
}


