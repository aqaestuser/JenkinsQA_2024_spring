package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;

public class ManageAppearance1Test extends BaseTest {

    @Test
    public void testDarkThemeApply() {
        final String actualThemeApplied = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAppearanceButton()
                .clickDarkThemeButton()
                .clickApplyButton()
                .getCurrentThemeAttribute();

        Assert.assertEquals(actualThemeApplied, "dark");
    }

    @Test
    public void testDefaultThemeApply() {
        final String actualThemeApplied = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAppearanceButton()
                .clickDefaultThemeButton()
                .clickApplyButton()
                .getCurrentThemeAttribute();

        Assert.assertEquals(actualThemeApplied, "none");
    }

    @Test
    public void testSystemThemeApply() {
        final String actualThemeApplied = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAppearanceButton()
                .clickSystemThemeButton()
                .clickApplyButton()
                .getCurrentThemeAttribute();

        Assert.assertTrue(actualThemeApplied.contains("system"));
    }
}