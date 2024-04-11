package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MainPageHeaderElementsTest extends BaseTest {

    @Test
    public void testLogoElementsDisplayed () {
     WebElement iconLogo = getDriver().findElement(
             By.cssSelector("div.logo>a#jenkins-home-link>img#jenkins-head-icon"));
     Assert.assertTrue(iconLogo.isDisplayed());

     WebElement textLogo = getDriver().findElement(
             By.cssSelector("div.logo>a#jenkins-home-link>img#jenkins-name-icon"));
     Assert.assertTrue(textLogo.isDisplayed());

     WebElement urlMainPage = getDriver().findElement(
             By.cssSelector("div.logo>a#jenkins-home-link"));
     Assert.assertEquals(urlMainPage.getAttribute("href"), "http://localhost:8080/");
    }
    @Test
    public void testSearchBoxElementsDisplayed () {
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("div#searchform")).isDisplayed());
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("input#search-box")).isEnabled());
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("span.main-search__icon-leading>svg[aria-hidden='true']")).isDisplayed());

     WebElement urlSearchBox = getDriver().findElement(
             By.cssSelector("a.main-search__icon-trailing"));
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("a.main-search__icon-trailing>svg[aria-hidden='true']")).isDisplayed());
     Assert.assertTrue(urlSearchBox.isEnabled());
     Assert.assertEquals(urlSearchBox
             .getAttribute("href"), "https://www.jenkins.io/redirect/search-box");
    }
    @Test
    public void testSecButtonElementsDisplayed () {
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("a#visible-sec-am-button>svg[aria-hidden='true']")).isDisplayed());
    }
    @Test

    public void testUserElementsDisplayed () {

     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("a.model-link>svg.icon-md")).isDisplayed());

     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("a.model-link>svg.icon-md")).isDisplayed());
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("button.jenkins-menu-dropdown-chevron")).isDisplayed());
     Assert.assertTrue(getDriver().findElement(
             By.cssSelector("button.jenkins-menu-dropdown-chevron")).isEnabled());
    }
    @Test
    public void testLogOutElementsDisplayed () {

     Assert.assertEquals(getDriver().findElement(
             By.cssSelector("div.login.page-header__hyperlinks > a:nth-child(4)"))
             .getAttribute("href"), "http://localhost:8080/logout");
     Assert.assertTrue(getDriver().findElement(
             By.xpath("//*[contains(text(),'log out')]"))
             .isDisplayed());
     Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href='/logout']"))
             .isEnabled());
    }
}
