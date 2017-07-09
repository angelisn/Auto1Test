package com.auto1.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page fragment representing each search result item
 */
public class SearchResult {

    private WebElement rootElement;

    public SearchResult(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    /**
     * Indicates whether there is a photo available
     * @return a boolean indicating whether there is a photo available
     */
    public boolean hasPhoto() {
        return rootElement.findElement(By.cssSelector("div.car-img > img")).isDisplayed();
    }

    /**
     * Returns the car's title
     * @return the car's title
     */
    public String getTitle() {
        return rootElement.findElement(By.cssSelector("div.car-name")).getText();
    }

    /**
     * Indicates whether information about the car is complete
     * @return information completeness indication
     */
    public boolean hasCompleteInformation() {
        List<WebElement> information = rootElement.findElements(By.cssSelector("div.car-info > table > tbody  > tr > td:nth-child(2)"));
        for (WebElement informationItem : information) {
            if (informationItem.getText().isEmpty()) return false;
        }
        /* else */
        return true;
    }
}
