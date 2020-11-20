package tests;


import org.openqa.selenium.*;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DropdownSearchPage;
import pages.MenuListPanelPage;
import utilities.Driver;


public class Class {

    MenuListPanelPage menuList = new MenuListPanelPage();
    DropdownSearchPage dropdownSearchPage = new DropdownSearchPage();




    /*
From this URL: https://www.seleniumeasy.com/test/basic-first-form-demo.html
    1. Set the value of id user-message to "QA assessment trial #1"
    2. Click 'Show Message'
    3. Assert that "Your Message" matches "QA assessment trial #1"
*/
    @Test
    public void shoudHaveCorrectMessage() throws InterruptedException {

        Driver.getDriver().get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        Thread.sleep(5000);
        Driver.getDriver().findElement(By.xpath("//a[@id='at-cv-lightbox-close']")).click();
        WebElement txtfld = Driver.getDriver().findElement(By.id("user-message"));
        WebElement btn = Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Show Message')]"));
        WebElement yourMesg = Driver.getDriver().findElement(By.xpath("//span[@id='display']"));
        txtfld.sendKeys("QA assessment trial #1");
        btn.click();
        Assert.assertEquals(yourMesg.getText(), "QA assessment trial #1");
    }

//    /*
//    From this URL: https://www.seleniumeasy.com/test/basic-first-form-demo.html.
//     Your next test should:
//        1. Interact with the menu on the left of the page and click "Input Forms"
//        2. On the sub-menu, click "Checkbox Demo"
//        3. Under "Multiple Checkbox Demo" there are "product requirements" for check/uncheck all,
//           come up with a test assertion to test this functionality.
//*/
    @Test  //PASSED
    public void checkboxTest() throws InterruptedException {

        Driver.getDriver().get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        Thread.sleep(5000);
        Driver.getDriver().findElement(By.xpath("//a[@id='at-cv-lightbox-close']")).click();
        menuList.selectFromAllExamplesDropdown("Input Forms");
        menuList.selectFromSubMenuDropdown("Checkbox Demo");
        Assert.assertEquals(menuList.selectSingleCheckListAndGetText(), "Success - Check box is checked");


    }

    @Test    //PASSED
    public void multiCheckBoxTest() throws InterruptedException {
        Driver.getDriver().get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        Thread.sleep(5000);
        Driver.getDriver().findElement(By.xpath("//a[@id='at-cv-lightbox-close']")).click();
        menuList.selectFromAllExamplesDropdown("Input Forms");
        menuList.selectFromSubMenuDropdown("Checkbox Demo");
        Driver.getDriver().manage().window().maximize();
        menuList.clickOnCheckAllButton();
        menuList.selectFromMultipleCheckList("Option 1");
        WebElement button = Driver.getDriver().findElement(By.xpath("//input[@id='check1']"));
        Assert.assertTrue(button.getAttribute("value").equals("Check All"));
    }

    /*
    In real life we wouldn't waste precious seconds to mouse nav, sorry to make you do that.
    The next test is working with selectors but it would be far too easy to use native selects.
    My final test for you is to work with jquery selects.
        1. From this URL: https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html
        2. Under the country select Japan & assert the field value is Japan
        3. Under the multi select select Delaware & Vermont & assert the field values
        4. Under US Outlying Territories assert that Guam & United States Minor Outlying Islands are disabled
*/
    @Test  //PASSED
    public void selectJapanTest() {
        Driver.getDriver().get("https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        dropdownSearchPage.dropdownWithSearchBoxClick("Japan");
        Assert.assertTrue(dropdownSearchPage.isCountrySelected("Japan"));


    }

    @Test  //PASSED
    public void multiSelectDelewareAndVermont() {
        Driver.getDriver().get("https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        dropdownSearchPage.selectMultipleStates("Delaware");
       dropdownSearchPage.selectMultipleStates("Vermont");
        Assert.assertTrue(dropdownSearchPage.isStateSelected("Delaware"));
        Assert.assertTrue(dropdownSearchPage.isStateSelected("Vermont"));


    }

    @Test //PASSED
    public void validateDisabledValuesSelect() {
        Driver.getDriver().get("https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        Assert.assertTrue(dropdownSearchPage.isDisabled("Guam"));
        Assert.assertTrue(dropdownSearchPage.isDisabled("United States Minor Outlying Islands"));
    }

}
