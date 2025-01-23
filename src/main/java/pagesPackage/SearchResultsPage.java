package pagesPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SearchResultsPage {
    // variables
    private WebDriver driver;
    private Actions actions;

    // locators
    private By seeAvailabilityButton = By.xpath("//div[@data-testid='property-card' and contains(., 'Tolip Hotel Alexandria')]//a[@data-testid='availability-cta-btn']");
    private By hotelTile = By.xpath("//div[text()='Tolip Hotel Alexandria']");

    // constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }


    public void clickSeeAvailabilityButton() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            try {
                // Wait for the hotel tile to be visible
                wait.until(ExpectedConditions.visibilityOfElementLocated(hotelTile));
                WebElement hotelTileElement = driver.findElement(hotelTile);

                // Scroll down until hotelTile is visible
                actions.keyDown(Keys.DOWN).perform();

                if (hotelTileElement.isDisplayed()) {
                    // Click the See Availability button
                    driver.findElement(seeAvailabilityButton).click();
                    break; // Exit loop once the button is clicked
                }
            } catch (StaleElementReferenceException e) {
                // Continue scrolling down if the hotel tile is not visible
                actions.keyDown(Keys.DOWN).perform();
            }
        }
    }
    }


