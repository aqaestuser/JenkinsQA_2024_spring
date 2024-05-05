package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class FolderConfigPage extends BasePage {

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "//label[@data-title='Disabled']")
    private WebElement toggle;

    @FindBy(id = "enable-disable-project")
    private WebElement statusToggle;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderStatusPage clickSaveButton() {
        saveButton.click();

        return new FolderStatusPage(getDriver());
    }

    public FolderConfigPage disabledToggle() {
        toggle.click();

        return this;
    }

    public String getStatusToggle() {
        return statusToggle.getDomProperty("checked");
    }
}
