package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

import java.util.List;

public class AboutJenkinsPage extends BasePage<AboutJenkinsPage> {

    @FindBy(xpath = "//p[@class='app-about-version']")
    private WebElement versionJenkins;

    @FindBy(xpath = "//div[@class='tabBar']")
    private WebElement jenkinsTabPanel;

    public AboutJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public String getJenkinsVersion() {
        return versionJenkins.getText().split(" ")[1];
    }

    //    TODO refactor this - no data inside model
    public boolean isExistJenkinsInformationFooter() {
        List<String> tabBarMenu = List.of(
                "Mavenized dependencies",
                "Static resources",
                "License and dependency information for plugins");
        return tabBarMenu.stream().allMatch(x -> jenkinsTabPanel.getText().contains(x));
    }
}
