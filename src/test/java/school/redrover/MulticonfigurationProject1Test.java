package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderProjectPage;
import school.redrover.model.HomePage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Random;


public class MulticonfigurationProject1Test extends BaseTest {
    final String PROJECT_NAME = generateRandomText(20);
    final String FOLDER_NAME = generateRandomText(10);

    private void createMulticonfigurationProject(){
        getDriver().findElement(By.xpath("//*[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//*[@class='jenkins-input']")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//*[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void createFolder(){
        getDriver().findElement(By.xpath("//*[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private String generateRandomNumber(){
        Random r = new Random();
        int randomNumber = r.nextInt(100) + 1;
        return String.valueOf(randomNumber);
    }

    private String generateRandomText(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    @Test
    public void testMoveProjectToFolderFromDashboardPage(){

        TestUtils.createFolderProject(this, FOLDER_NAME);
        TestUtils.createMultiConfigurationProject(this, PROJECT_NAME);

        new HomePage(getDriver())
                .clickJobByName(PROJECT_NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickMoveOptionInMenu()
                .selectFolder(FOLDER_NAME)
                .clickMove()
                .clickLogo()
                .clickFolder(FOLDER_NAME);

        boolean isProjectMoved = new FolderProjectPage(getDriver()).getItemListInsideFolder().contains(PROJECT_NAME);

        Assert.assertTrue(isProjectMoved);
    }
}
