package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class SearchBox3Test extends BaseTest {
    private static final By SEARCH_BOX = By.xpath("//input[@id='search-box']");

    @Test
    public void testSearchWithValidData() {
        getDriver().findElement(SEARCH_BOX).sendKeys("config");
        getDriver().findElement(SEARCH_BOX).sendKeys(Keys.ENTER);

        String systemPageTitle = getDriver().findElement(By.xpath("//h1[.='System']")).getText();

        Assert.assertEquals(systemPageTitle, "System");
    }
}
