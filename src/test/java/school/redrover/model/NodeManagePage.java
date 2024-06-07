package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.List;

public class NodeManagePage extends BasePage<NodeManagePage> {
    public NodeManagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='jenkins-app-bar__controls']")
    private WebElement markThisNodeTemporaryOfflineButton;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement markThisNodeTemporaryOfflineConfirmationBtn;

    @FindBy(css = ".jenkins-button.jenkins-button--primary")
    private WebElement bringThisNodeBackOnlineBtn;

    @FindBy(xpath = "//div[@class='message']")
    private WebElement nodeOnlineStatusMessage;

    public NodeManagePage clickMarkThisNodeTemporaryOfflineButton() {
        markThisNodeTemporaryOfflineButton.click();
        return new NodeManagePage(getDriver());
    }

    public NodeManagePage clickMarkThisNodeTemporaryOfflineConfirmationBtn() {
        markThisNodeTemporaryOfflineConfirmationBtn.click();
        return new NodeManagePage(getDriver());
    }

    public NodeManagePage clickBringThisNodeBackOnlineBtn() {
        try {
            getWait5().until(ExpectedConditions.elementToBeClickable(bringThisNodeBackOnlineBtn)).click();
        } catch (Exception e) {

        }
        return new NodeManagePage(getDriver());
    }

    public String getNodeOnlineStatusText() {
        return nodeOnlineStatusMessage.getText();
    }

    public List<WebElement> nodeOnlineStatusText() {
        return getDriver().findElements(By.xpath("//div[@class='message']"));
    }
}
