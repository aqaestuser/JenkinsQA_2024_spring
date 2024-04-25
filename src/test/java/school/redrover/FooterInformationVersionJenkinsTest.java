package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.List;


public class FooterInformationVersionJenkinsTest extends BaseTest {

    private List<String> checkЕabBar = List.of("Mavenized dependencies", "Static resources", "License and dependency information for plugins");

    @Test
    public void testJenkinsInformationVersionFooter() {

        getDriver().findElement(By.cssSelector("[class$='jenkins_ver']")).click();

        WebElement dropDownMenu = getDriver().findElement(By.cssSelector("#tippy-1"));
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//a[@href='/manage/about']"))));

        WebElement aboutJenkins = dropDownMenu.findElement(By.xpath("//a[@href='/manage/about']"));
        Assert.assertTrue(aboutJenkins.isDisplayed());

        WebElement getInvolved = dropDownMenu.findElement(By.xpath("//a[@href='https://www.jenkins.io/participate/']"));
        Assert.assertTrue(getInvolved.isDisplayed());

        WebElement webSite = dropDownMenu.findElement(By.xpath("//a[@href='https://www.jenkins.io/']"));
        Assert.assertTrue(webSite.isDisplayed());

        getDriver().findElement(By.xpath("//a[@href='/manage/about']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//p[.='Version 2.440.2']")).isDisplayed());

        WebElement tabBar = getDriver().findElement(By.xpath("//div[@class='tabBar']"));

        Assert.assertTrue(tabBar.getText().contains(checkЕabBar.get(0)), "Mavenized dependencies");
        Assert.assertTrue(tabBar.getText().contains(checkЕabBar.get(1)), "Static resources");
        Assert.assertTrue(tabBar.getText().contains(checkЕabBar.get(2)), "License and dependency information for plugins");
    }
}