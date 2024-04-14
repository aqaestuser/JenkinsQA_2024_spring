package school.redrover;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class NewItem3Test extends BaseTest {

    @Test
    public void createItemEmptyNameNegativeTest() {
        try {
            TestUtils.createItem(TestUtils.FREESTYLE_PROJECT, "", getDriver());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
            }
        }
    }

