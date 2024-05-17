package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;


public class CreateFolder3Test extends BaseTest {
    @Test
    public void testSpecialCharactersNameFolder() {
        String header1Text = new HomePage(getDriver())
                .clickNewItem()
                .setItemName("Fold%erdate")
                .selectFolderAndClickOk()
                .getHeaderOneText();

        Assert.assertEquals(header1Text, "Error");
    }
}
