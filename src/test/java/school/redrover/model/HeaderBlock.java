package school.redrover.model;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class HeaderBlock extends BasePage {

    public HeaderBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='search-box']")
    private WebElement searchBox;

    public HeaderBlock sendRequestToSearchBox(String requestData){
        searchBox.sendKeys(requestData);
        return this;
    }

    public SystemConfigurationPage makeClickToSearchBox(){
        searchBox.sendKeys(Keys.ENTER);
        return new SystemConfigurationPage (getDriver());
    }



}
