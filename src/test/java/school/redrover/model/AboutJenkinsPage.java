package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class AboutJenkinsPage extends BasePage {

    public AboutJenkinsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[.='Version 2.440.2']")
    public WebElement versionJenkins;

    @FindBy(xpath = "//div[@class='tabBar']")
    public WebElement tabBar;

}
