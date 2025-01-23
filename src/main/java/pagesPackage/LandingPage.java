package pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class LandingPage {

    //variables
    private WebDriver driver;
    private String URL = "https://www.booking.com/";


    //locators
    private By locationSearchInput = By.name("ss");
    private By dateSearchInput = By.xpath("//div[@data-testid=\"searchbox-dates-container\"]");
    private By dismissBobUpBox = By.xpath("//button[@aria-label='Dismiss sign-in info.']");
    private By checkInDateCalender;
    private By checkOutDateCalender;
    private By searchButton = By.xpath("//button[@type='submit']");
    private By calendarNextButton = By.xpath("//button[@aria-label='Next month']");
    private By monthTitle = By.xpath("//h3[@aria-live='polite']");

    //constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //actions
    public void navigate() {
        driver.navigate().to(URL);
    }

    public void dismissBobUp() {
        driver.findElement(dismissBobUpBox).click();

    }

    public void enterLocation(String location) {
        driver.findElement(locationSearchInput).sendKeys(location);
    }

    public void selectDate(String checkInDate, String checkOutDate) {

        driver.findElement(dateSearchInput).click();

        while (!driver.findElement(monthTitle).getText().contains("October")) {
            driver.findElement(calendarNextButton).click();
        }


        checkInDateCalender = By.xpath("//span[@aria-label='" + checkInDate + "']");
        driver.findElement(checkInDateCalender).click();
        checkOutDateCalender = By.xpath("//span[@aria-label='" + checkOutDate + "']");
        driver.findElement(checkOutDateCalender).click();

    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }
}
