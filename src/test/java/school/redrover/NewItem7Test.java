package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem7Test extends BaseTest {


    @Test
    public void testGoToNewJobPage() {

        getDriver().findElement(By.linkText("New Item")).click();

        String title = getDriver().findElement(By.xpath("//*[text()='Enter an item name']")).getText();

        Assert.assertEquals(title, "Enter an item name");

    }
}
