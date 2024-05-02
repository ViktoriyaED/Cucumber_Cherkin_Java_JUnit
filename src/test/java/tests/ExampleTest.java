package tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

@Ignore
public class ExampleTest extends Hooks {

    @Test
    public void test() {
        getDriver().get("https://demoqa.com");
        Assert.assertEquals(getDriver().getTitle(), "DEMOQA");
        WebElement elementsSideMenu = getDriver().findElement(By.xpath("//h5[text()='Elements']"));
        elementsSideMenu.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/elements");
    }
}
