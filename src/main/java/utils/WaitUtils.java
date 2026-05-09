package utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.Driver;

public class WaitUtils {

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait =
                new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebElement element) {
        WebDriverWait wait =
                new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}