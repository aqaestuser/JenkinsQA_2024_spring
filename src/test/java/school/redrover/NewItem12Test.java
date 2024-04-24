package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class NewItem12Test extends BaseTest {
        @Test
        public void CreateNewItem(){

            getDriver().findElement(By.linkText("New Item")).click();
            getDriver().findElement(By.id("main-panel")).click();

            getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required")));

            WebElement validationMessage = getDriver().findElement(By.id("itemname-required"));
            Assert.assertEquals(validationMessage.getText(), "» This field cannot be empty, please enter a valid name");

            getDriver().findElement(By.id("name")).sendKeys("GBtest");
            getDriver().findElement(By.cssSelector("#j-add-item-type-standalone-projects > ul > li.hudson_matrix_MatrixProject > label > span")).click();
            WebElement okButton = getDriver().findElement(By.id("ok-button"));
            Assert.assertTrue(okButton.isEnabled());
            okButton.click();

            getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

            WebElement projectName1 = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));
            Assert.assertEquals(projectName1.getText(), "Project GBtest");

        }
}
