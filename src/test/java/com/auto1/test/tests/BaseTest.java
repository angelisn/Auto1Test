package com.auto1.test.tests;

import com.auto1.test.pages.OurCarsPage;
import com.auto1.test.pages.SearchResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void filterBMWs() {
        // Initialize the 'Our Cars' page
        OurCarsPage ourCars = new OurCarsPage(driver);

        // Visit auto1's home page
        ourCars.navigateTo();

        // Select BMW from the Manufacturer's list
        ourCars.selectManufacturer("BMW");

        // Get the selected manufacturers and confirm they match ["BMW"]
        List<String> expectedSelectedManufacturers = new ArrayList<>();
        expectedSelectedManufacturers.add("BMW");
        assert ourCars.getSelectedManufacturers().equals(expectedSelectedManufacturers);

        // Perform assertion on each result
        for (SearchResult result : ourCars.getResults()) {
            // Check that the car is a BMW
            assert result.getTitle().contains("BMW");
            // Check that there is a photo
            assert result.hasPhoto();
            // Check that information is complete
            assert result.hasCompleteInformation();
        }
    }

    @AfterSuite
    private void tearDown() {
        driver.quit();
    }
}
