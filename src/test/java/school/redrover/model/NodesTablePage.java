package school.redrover.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class NodesTablePage extends BasePage<NodesTablePage> {

    @FindBy(css = "[href='new']")
    private WebElement newNodeButton;

    @FindBy(css = "button[href$='doDelete']")
    private WebElement deleteButton;

    @FindBy(css = "[data-id='ok']")
    private WebElement okIntoDeleteDialog;

    @FindBy(id = "computers")
    private WebElement table;

    @FindBy(css = "[href='configure']")
    private WebElement configureMonitorsButton;

    @FindBy(css = "[href^='../computer/']:not([href$='configure'])")
    private List<WebElement> nodesInTableList;

    @FindBy(css = "[href*='built-in']")
    private WebElement builtInNode;

    public NodesTablePage(WebDriver driver) {
        super(driver);
    }


    @Step("Click on the button 'New Node'")
    public CreateNodePage clickNewNodeButton() {
        newNodeButton.click();

        return new CreateNodePage(getDriver());
    }

    @Step("Move cursor to the Name and click on the dropdown chevron")
    public NodesTablePage openDropdownChevron(String name) {
        WebElement dropdownChevron = getDriver().findElement(
                By.cssSelector("#node_" + name + " > td:nth-child(2) > a > button"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));"
                        + "arguments[0].dispatchEvent(new Event('click'));",
                dropdownChevron);

        return this;
    }

    @Step("click on the 'Delete Agent' in the dropdown menu")
    public NodesTablePage clickDeleteAgentOnDropdownMenu() {
        deleteButton.click();

        return this;
    }

    @Step("Click 'Yes' in the Delete Agent window")
    public NodesTablePage clickYesInDeleteAgentWindow() {
        okIntoDeleteDialog.click();

        return this;
    }

    public boolean isContainNode(String name) {
        return table.getText().contains(name);
    }

    @Step("Click on the button 'Configure monitors'")
    public NodesConfigurePage clickConfigureMonitorsButton() {
        configureMonitorsButton.click();

        return new NodesConfigurePage(getDriver());
    }

    public boolean isNodeDisplayedInTable(String name) {
        return getDriver().findElement(By.cssSelector("[href='../computer/" + name + "/']")).isDisplayed();
    }

    public List<String> getNodesInTableList() {
        return nodesInTableList
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Click on the Node name 'Built-In Node'")
    public NodeBuiltInStatusPage clickBuiltInNode() {
        builtInNode.click();

        return new NodeBuiltInStatusPage(getDriver());
    }

    @Step("Click on the Node with the specified name")
    public NodePage clickNode(String nodeName) {
        getDriver().findElement(
                By.xpath("//a[@href='../computer/" + TestUtils.asURL(nodeName) + "/']")).click();

        return new NodePage(getDriver());
    }
}
