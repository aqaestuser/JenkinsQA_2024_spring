package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class TestUtils {

    private static final By userID = By.xpath("(//body)[1]");

    public static String getUserID (WebDriver driver) {
        return driver.findElement(userID).getText();

    }

}
