package testPackage;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import pagesPackage.BookingsDetailsPage;
import pagesPackage.ConfirmationPage;
import pagesPackage.LandingPage;
import pagesPackage.SearchResultsPage;
import utilities.ExcelReader;

import java.io.IOException;
import java.time.Duration;

public class BookingTest {
    WebDriver driver = null;
    Wait<WebDriver> wait = null;
    LandingPage landingPage = null;
    Actions actions = null;
    SearchResultsPage searchResultsPage = null;
    BookingsDetailsPage bookingsDetailsPage = null;
    ConfirmationPage confirmationPage = null;

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {  // Data provider method
        ExcelReader excelReader = new ExcelReader(); // Create an object of the ExcelReader class
        return excelReader.getExcelData(); // Return the data from the Excel sheet
    }

    /**
     * 1- Open www.booking.com
     * 2- In search type the Location Alexandria, select Check-in date 1 October 2024 and Check-
     * out date 14 October 2024 and click Search
     * 3-From search results, pick Tolip Hotel Alexandria (it can be on first or second page)
     * 4-Click on See Availability button to go to the booking’s details page
     * 5-In details page: Select the bed and amount and click I’ll reserve button to navigate to the
     * confirmation page
     * a.	Test Cases for the below:
     * i.	In details page: Assert that the chosen check-in and check-out dates are displayed correctly
     * ii.	In the reservation page assert that the name of the hotel is shown in the box
     */


    @Test(dataProvider = "testData")
    public void assertCheckInAndCheckOutDatesInDetailsPage(String location, String checkInDate, String checkOutDate) { //THE TEST CASE IS EXPECTED TO FAIL

        landingPage.navigate();
        wait.until(d -> {
            landingPage.dismissBobUp();
            return true;
        });
        landingPage.enterLocation(location);
        landingPage.selectDate(checkInDate, checkOutDate);
        landingPage.clickSearch();
        searchResultsPage.clickSeeAvailabilityButton();
        wait.until(d -> {
            Assert.assertEquals(bookingsDetailsPage.getChosenCheckInDate(), checkInDate);
            Assert.assertEquals(bookingsDetailsPage.getChosenCheckOutDate(), checkOutDate);
            return true;
        });
    }


    @Test(dataProvider = "testData")
    public void assertHotelNameInReservationPage(String location, String checkInDate, String checkOutDate) {

        landingPage.navigate();
        wait.until(d -> {
            landingPage.dismissBobUp();
            return true;
        });
        landingPage.enterLocation(location);
        landingPage.selectDate(checkInDate, checkOutDate);
        landingPage.clickSearch();
        searchResultsPage.clickSeeAvailabilityButton();
        wait.until(d -> {
            bookingsDetailsPage.selectRoomDetails();
            bookingsDetailsPage.clickReserveButton();
            return true;
        });
        wait.until(d -> {
            Assert.assertTrue(confirmationPage.getHotelName());
            return true;
        });
    }


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.manage().window().maximize();
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
        ;

        actions = new Actions(driver);
        landingPage = new LandingPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        bookingsDetailsPage = new BookingsDetailsPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
