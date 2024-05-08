package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class MultibranchPipelineRenamePage extends BasePage {

    @FindBy (css ="input.jenkins-input.validated")
    private WebElement newNameInput;

    @FindBy (xpath = "//div[@id='bottom-sticker']//button")
    private WebElement saveRenameButton;

    public MultibranchPipelineRenamePage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineRenamePage clearNameInputField() {
        newNameInput.clear();

        return this;
    }

    public MultibranchPipelineRenamePage setNewName(String name) {
        newNameInput.sendKeys(name);

        return this;
    }

    public MultibranchPipelineStatusPage clickSaveRenameButton() {
        new Actions(getDriver()).doubleClick(saveRenameButton).perform();

        return new MultibranchPipelineStatusPage(getDriver());
    }

}
