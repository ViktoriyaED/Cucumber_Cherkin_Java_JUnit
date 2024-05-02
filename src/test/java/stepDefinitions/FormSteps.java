package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormSteps {

    private final WebDriver driver;
    private final WebDriverWait wait5;
    private Map<String, String> formData = new HashMap<>();

    public FormSteps() {
        this.driver = Hooks.getDriver();
        this.wait5 = Hooks.getWait5();
    }

    @When("Choose Practice Form submenu")
    public void choose_practice_form_submenu() {
        driver.findElement(By.xpath("//span[text()='Practice Form']")).click();
    }

    @Then("Practice Form page is opened")
    public void practice_form_page_is_opened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/automation-practice-form");
    }

    @When("User clicks on the side menu Forms")
    public void userClicksOnTheSideMenuFormsPage() {
        WebElement formsSideMenu = driver.findElement(By.xpath("//h5[text()='Forms']"));
        formsSideMenu.click();
    }

//    @When("User clicks on submenu Practice Form")
//    public void userClicksOnThePracticeFormSubmenu() {
//        WebElement formsSideMenu = driver.findElement(By.xpath("//h5[text()='Forms']"));
//        formsSideMenu.click();
//    }

    @When("User fill out the First Name field with {string} and Last Name with {string}")
    public void user_fill_out_the_last_name_fields_with(String firstName, String lastName) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        formData.put("Student Name", firstName + " " + lastName);
    }

    @When("Fill out the Email field with {string}")
    public void user_fill_out_the_email_field_with(String email) {
        driver.findElement(By.id("userEmail")).sendKeys(email);
        formData.put("Student Email", email);
    }

    @When("User select gender {string}")
    public void user_select_their_gender(String gender) {
        WebElement radioButton = driver.findElement(By.cssSelector(String.format("input[name='gender'][value='%s']~label", gender)));
        radioButton.click();
        formData.put("Gender", gender);
    }

    @When("User fill out the Mobile field with {string}")
    public void user_fill_out_the_mobile_field_with(String mobile) {
        driver.findElement(By.id("userNumber")).sendKeys(mobile);
        formData.put("Mobile", mobile);
    }

//    @When("User clicks on the Submit button")
//    public void userClicksOnTheSubmitButton() {
//        WebElement submitButton = driver.findElement(By.cssSelector("#submit"));
//        Hooks.scrollToElement(driver, submitButton);
//        submitButton.click();
//    }

    @Then("User should see a Thanks for submitting the form popup")
    public void user_should_see_a_popup() {
        WebElement modalContainer = driver.findElement(By.className("modal-content"));
        wait5.until(ExpectedConditions.visibilityOf(modalContainer));
        Assert.assertTrue(modalContainer.isDisplayed());
    }

    @Then("User should not see a Thanks for submitting the form popup")
    public void user_should_not_see_a_popup() {
        List<WebElement> modalContentBodyList = driver.findElements(By.cssSelector(".modal-body tbody tr td"));
        Assert.assertEquals(0, modalContentBodyList.size());
        Assert.assertTrue(modalContentBodyList.isEmpty());
    }

    @Then("Entered data should be displayed")
    public void entered_data_should_be_displayed() {
        List<WebElement> modalContentBodyList = driver.findElements(By.cssSelector(".modal-body tbody tr td"));
        Map<String, String> actualFormData = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                actualFormData.put(modalContentBodyList.get(i).getText(), modalContentBodyList.get(i + 1).getText());
            }
        }
        Assert.assertArrayEquals(formData.values().toArray(), actualFormData.values().toArray());
        Assert.assertTrue(actualFormData.equals(formData));
        Assert.assertEquals(actualFormData.entrySet(), formData.entrySet());
    }
}
