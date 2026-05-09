package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driverFactory.Driver;

public class ScreenShotUtil {

    public static void capture(String name) {

        try {
            File src = ((TakesScreenshot) Driver.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    src.toPath(),
                    Paths.get("target/screenshots/" + name + ".png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}