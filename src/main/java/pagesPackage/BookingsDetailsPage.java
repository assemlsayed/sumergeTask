package pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class BookingsDetailsPage {

    //variables
    private WebDriver driver;
    private Actions actions;

    //locators
    private By roomAmountPicker = By.xpath("//select[@data-block-id='78883120_406187102_1_33_0_131741']");
    private By BedTypeRadioButton = By.xpath("//input[@name='bedPreference_78883120' and @value='2']");
    private By reserveButton = By.xpath("//div[@data-component='hotel/new-rooms-table/reservation-cta']");
    private By chosenCheckInDate = By.xpath("//div[@id=\"hp_availability_style_changes\"]//span[contains(text(),'Wed, Oct 1')]");
    private By chosenCheckOutDate = By.xpath("//div[@id=\"hp_availability_style_changes\"]//span[contains(text(),'Wed, Oct 14')]");


    // constructor
    public BookingsDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    //actions
    public void selectRoomDetails() {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        Select select = new Select(driver.findElement(roomAmountPicker));
        select.selectByValue("1");
        actions.moveToElement(driver.findElement(BedTypeRadioButton)).click().perform();

    }

    public void clickReserveButton() {
        actions.moveToElement(driver.findElement(reserveButton)).click().perform();
    }

    public String getChosenCheckInDate() {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        return driver.findElement(chosenCheckInDate).getText();
    }

    public String getChosenCheckOutDate() {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        return driver.findElement(chosenCheckOutDate).getText();
    }
}
