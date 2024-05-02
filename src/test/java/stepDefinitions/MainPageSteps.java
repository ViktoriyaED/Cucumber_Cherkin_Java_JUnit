package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageSteps {

    private final WebDriver driver;

    public MainPageSteps() {
        driver = Hooks.getDriver();
    }

    @When("User opens main page")
    public void user_opens_main_page() {
        driver.get("https://demoqa.com");
    }

    @Then("Main Page is opened")
    public void main_page_is_opened() {
        Assert.assertEquals(driver.getTitle(), "DEMOQA");
    }

    @When("User clicks on the side menu Elements page")
    public void user_clicks_on_the_side_menu_elements_page() {
        WebElement elementsSideMenu = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsSideMenu.click();
    }

    @Then("Elements Page is opened")
    public void elements_page_is_opened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/elements");
    }

//    @When("User clicks on the side menu {string} page")
//    public void user_clicks_on_the_side_menu(String menu) {
//        WebElement sideMenu = driver.findElement(By.xpath(String.format("//h5[text()='%s']", menu.toLowerCase())));
//        sideMenu.click();
//    }
}
