package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;

public class OKButtonUnavailableTest extends BaseTest {

    @Test
    public void testOKButtonIsUnavailable() {

        new HomePage(getDriver())
                .clickNewItem()
                .clearItemNameField()
                .setItemName("")
                .clickOkButton();

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
