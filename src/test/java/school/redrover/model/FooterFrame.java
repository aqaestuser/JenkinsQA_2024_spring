package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseFrame;
import school.redrover.model.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class FooterFrame extends BaseFrame {

    public FooterFrame(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class$=jenkins_ver]")
    private WebElement version;

    @FindBy(xpath = "//a[@href='api/']")
    private WebElement apiLink;

    @FindBy(xpath = "//div/a[@href='/manage/about']")
    private WebElement aboutJenkinsDropdownItem;

    @FindBy(xpath = "//div/a[@href='https://www.jenkins.io/participate/']")
    private WebElement involvedDropdownItem;

    @FindBy(xpath = "//div/a[@href='https://www.jenkins.io/']")
    private WebElement websiteDropdownItem;

    @FindBy(className = "jenkins-dropdown__item")
    private List<WebElement> dropDownElements;


    public String getVersionOnFooter() {

        return version.getText().split(" ")[1];
    }

    public <T extends BasePage> T clickVersion(T page) {
        getWait2().until(ExpectedConditions.visibilityOf(version)).click();

        return page;
    }

    public ApiPage clickApiLink() {
        apiLink.click();

        return new ApiPage(getDriver());
    }

    public boolean isDisplayedAboutJenkinsDropdownItem() {

        return getWait5().until(ExpectedConditions.elementToBeClickable(aboutJenkinsDropdownItem)).isDisplayed();
    }

    public boolean isDisplayedInvolvedDropdownItem() {

        return involvedDropdownItem.isDisplayed();
    }

    public boolean isDisplayedWebsiteDropdownItem() {

        return websiteDropdownItem.isDisplayed();
    }

    public AboutJenkinsPage selectAboutJenkinsAndClick() {
        getWait5().until(ExpectedConditions.elementToBeClickable(aboutJenkinsDropdownItem)).click();

        return new AboutJenkinsPage(getDriver());
    }

    public List<String> getVersionDropDownElementsValues() {
        List<String> actualDropDownElementsValues = new ArrayList<>();
        for (WebElement element : dropDownElements) {
            actualDropDownElementsValues.add(element.getDomProperty("innerText"));
        }

        return actualDropDownElementsValues;
    }
}