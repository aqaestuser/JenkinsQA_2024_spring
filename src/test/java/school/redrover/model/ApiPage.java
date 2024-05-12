package school.redrover.model;

import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;
import school.redrover.runner.BaseTest;

public class ApiPage extends BasePage {
    public ApiPage(WebDriver driver) {
        super(driver);
    }

    public String getApiPageTitleText() {
        return getDriver().getTitle();
    }
}
