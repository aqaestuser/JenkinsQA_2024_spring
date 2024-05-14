package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class BuildHistoryTest extends BaseTest{
    private final String PROJECT_NAME = "My freestyle project";

    @Test
    public void testCreatFreestyleProject() {
        List<String> actualMyProject = new HomePage(getDriver())
                .clickNewItem()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .clickLogo()
                .getItemList();

        Assert.assertTrue(actualMyProject.contains(PROJECT_NAME));
        }

        @Test(dependsOnMethods = "testCreatFreestyleProject")
        public void testGetTableBuildHistory() {

        List<String> list = new HomePage(getDriver())
                .scheduleBuildForItem(PROJECT_NAME)
                .clickBuildHistory()
                .getBuildsList();

        Assert.assertTrue(list.contains(PROJECT_NAME));
    }
}