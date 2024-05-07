package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.AboutJenkinsPage;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class FooterTest extends BaseTest {

    @Test
    public void testJenkinsVersionFooter() {
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//*[@class='jenkins-button jenkins-button--tertiary jenkins_ver']"));

        Assert.assertEquals(jenkinsVersion.getText(), "Jenkins 2.440.2");
    }

    @Test
    public void testApiInfo() {
        getDriver().findElement(By.xpath("//*[@class='jenkins-button jenkins-button--tertiary rest-api']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/api/");
    }

    @Test
    public void testLinkButtonsListInVersionDropDown() {
        final List<String> expectedDropDownElementsValues = new ArrayList<>(List.of("About Jenkins", "Get involved", "Website"));

        getDriver().findElement(By.cssSelector("[class$=jenkins_ver]")).click();
        List<WebElement> dropDownElements = getDriver().findElements(By.className("jenkins-dropdown__item"));
        List<String> actualDropDownElementsValues = new ArrayList<>();
        for (WebElement element : dropDownElements) {
            actualDropDownElementsValues.add(element.getDomProperty("innerText"));
        }

        Assert.assertEquals(actualDropDownElementsValues, expectedDropDownElementsValues, "Allarm!");
    }

    @Test
    public void testRestAPIButtonTitle() {
        getDriver().findElement(By.xpath("//a[@href='api/']")).click();

        String titleText = getDriver().getTitle();

        Assert.assertEquals(titleText, "Remote API [Jenkins]");
    }

    @Test
    public void testJenkinsVersion() {
        AboutJenkinsPage page = new HomePage(getDriver()).jenkinsFooterClick()
                .selectAboutJenkins();

        Assert.assertTrue(page.versionJenkins.isDisplayed());
    }

    @Test
    public void testDropDownLink() {
        HomePage page = new HomePage(getDriver()).jenkinsFooterClick();

        Assert.assertTrue(getWait5().until(ExpectedConditions.elementToBeClickable(page.aboutJenkinsDropdownItem)).isDisplayed());

        Assert.assertTrue(page.involvedDropdownItem.isDisplayed());
        Assert.assertTrue(page.websiteDropdownItem.isDisplayed());
    }

    @Test
    public void testJenkinsInformationFooter() {
        List<String> tabBarMenu = List.of("Mavenized dependencies", "Static resources", "License and dependency information for plugins");

        AboutJenkinsPage page = new HomePage(getDriver()).jenkinsFooterClick()
                .selectAboutJenkins();

        Assert.assertTrue(page.tabBar.getText().contains(tabBarMenu.get(0)), "Expected: " + tabBarMenu.get(0));
        Assert.assertTrue(page.tabBar.getText().contains(tabBarMenu.get(1)), "Expected: " + tabBarMenu.get(1));
        Assert.assertTrue(page.tabBar.getText().contains(tabBarMenu.get(2)), "Expected: " + tabBarMenu.get(2));
    }
}