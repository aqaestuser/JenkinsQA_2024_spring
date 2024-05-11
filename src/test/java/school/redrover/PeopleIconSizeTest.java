package school.redrover;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;

public class PeopleIconSizeTest extends BaseTest {

    @Test
    public void testIconSizeSmall() {
        final Dimension actualIconSize = new HomePage(getDriver())
                .clickPeopleButton()
                .clickSmallIconButton()
                .getUserIconSize();

        Assert.assertEquals(actualIconSize, new Dimension(16, 16));
    }

    @Test
    public void testIconSizeMedium() {
        final Dimension actualIconSize = new HomePage(getDriver())
                .clickPeopleButton()
                .clickMediumIconButton()
                .getUserIconSize();

        Assert.assertEquals(actualIconSize, new Dimension(20, 20));
    }

    @Test
    public void testIconSizeLarge() {
        final Dimension actualIconSize = new HomePage(getDriver())
                .clickPeopleButton()
                .clickLargeIconButton()
                .getUserIconSize();

        Assert.assertEquals(actualIconSize, new Dimension(24, 24));
    }
}
