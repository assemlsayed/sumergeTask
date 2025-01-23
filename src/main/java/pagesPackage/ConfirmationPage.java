package pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    // variables
    private WebDriver driver;


    // locators
   private By hoteNameText = By.xpath("//h1[contains(@class, 'e1eebb6a1e')]");

    // constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    };

    // actions
    public boolean getHotelName() {
        return driver.findElement(hoteNameText).isDisplayed();
    }

}
