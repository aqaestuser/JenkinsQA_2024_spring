package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static school.redrover.runner.TestUtils.*;

import java.util.*;

public class Dashboard1Test extends BaseTest {

    public List<String> getNamesList() {
        List<String> names = new ArrayList<>();
        names.add("Imagine Dragons");
        names.add("Queen");
        names.add("A-ha");
        names.add("Depeche Mode");
        names.add("Rammstein");
        names.add("Antonio Vivaldi");
        return names;
    }

    public void createItemsFromList(List<String> list) {
        for (String name : list) {
            createItem(MULTIBRANCH_PIPELINE, name, getDriver());
            goToMainPage(getDriver());
        }

    }

    public List<String> getItemNamesFromColumnAfterSortingByName() {
        getDriver().findElement(By.xpath("//a[@class='sortheader' and text()='Name']")).click();
        return getTexts(getDriver().findElements(By.xpath("//td/a[contains(@href, 'job/')]")));
    }

    @Test
    public void testSortItems() {
        List<String> names = getNamesList();
        createItemsFromList(names);
        Collections.sort(names);
        Collections.reverse(names);

        Assert.assertEquals(getItemNamesFromColumnAfterSortingByName(), names);

        Collections.reverse(names);

        Assert.assertEquals(getItemNamesFromColumnAfterSortingByName(), names);
    }

    @Test
    public void testCreateViewWithSelectedItem() {
        createItemsFromList(getNamesList());

        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("Name")).sendKeys("Classic");
        getDriver().findElement(By.id("hudson.model.ListView")).click();
        getDriver().findElement(By.id("ok")).click();

    }
}