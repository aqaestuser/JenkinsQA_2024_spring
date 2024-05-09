package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import school.redrover.model.base.BasePage;

import java.util.List;

public class AboutJenkinsPage extends BasePage {

    @FindBy(xpath = "//p[.='Version 2.440.2']")
    public WebElement versionJenkins;

    @FindBy(xpath = "//div[@class='tabBar']")
    public WebElement tabBar;

    public AboutJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public AboutJenkinsPage assertIsDisplayedVersionJenkins() {
        Assert.assertTrue(versionJenkins.isDisplayed());

        return this;
    }

    public void assertExistJenkinsInformationFooter() {
        List<String> tabBarMenu = List.of("Mavenized dependencies", "Static resources", "License and dependency information for plugins");
        tabBarMenu.stream().allMatch(x -> tabBar.getText().contains(x));
    }
}
