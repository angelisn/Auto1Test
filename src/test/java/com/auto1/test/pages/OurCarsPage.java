package com.auto1.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the 'Our Cars' page (main search page)
 *
 */
public class OurCarsPage {

    private final int WAIT_TIMEOUT_SECONDS = 30;

    private WebDriver driver;

    public OurCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Go to the 'Our Cars' page
     *
     */
    public void navigateTo() {
        driver.get("https://www.auto1.com/en/our-cars");
    }

    /**
     * Get the list of selected manufacturers in the filter
     * @return manufacturer's list
     */
    public List<String> getSelectedManufacturers() {
        List<WebElement> manufacturerElements = driver.findElements(By.cssSelector("div.search-group ul > li[title]"));
        List<String> titles = new ArrayList<>();

        for (WebElement e : manufacturerElements) {
            titles.add(e.getAttribute("title"));
        }

        return titles;
    }

    /**
     * Selects a manufacturer from the list and waits until the results' list is refreshed
     * @param manufacturer the name of the manufacturer to be selected
     */
    public void selectManufacturer(String manufacturer) {
        WebElement e = driver.findElement(By.xpath("//ul[contains(@class,\"manufacturers-list\")]/li/span[text()=\"" + manufacturer + "\"]"));
        e.click();
        waitForElementToBeHidden(By.cssSelector("div.loading-ticker"));
    }

    /**
     * Get the list of results
     * @return A list containing {SearchResult} items
     */
    public List<SearchResult> getResults() {
        List<WebElement> resultElements = driver.findElements(By.cssSelector("ul.car-list > li.car-item"));
        List<SearchResult> results = new ArrayList<>();
        for (WebElement e : resultElements) {
            results.add(new SearchResult(e));
        }
        return results;
    }

    private void waitForElementToBeHidden(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
