package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;


public class MultibranchPipeline2Test extends BaseTest {

    @Test
    public void testEnableMultibranchPipeline() {
        var page = new HomePage(getDriver()).clickCreateAJob()
                .setItemName("TextName")
                .selectMultibranchPipelineAndClickOk()
                .clickOnToggle()
                .clickSaveButton()
                .selectConfigure()
                .disabledToggle()
                .clickSaveButton()
                .selectConfigure();

        Assert.assertEquals(page.getStatusToggle(), "true");
    }

    @Test
    public void testDisabledMultibranchPipeline() {
        var page = new HomePage(getDriver()).clickCreateAJob()
                .setItemName("TextName1")
                .selectMultibranchPipelineAndClickOk()
                .clickOnToggle()
                .clickSaveButton()
                .selectConfigure();

        Assert.assertEquals(page.getStatusToggle(), "false");
    }
}