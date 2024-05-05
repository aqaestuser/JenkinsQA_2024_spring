package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class MultibranchPipelineStatusPage extends BasePage {

    public MultibranchPipelineStatusPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Configure the project']")
    private WebElement configureButton;

    public MultibranchPipelineConfigPage selectConfigure() {
        configureButton.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }
}