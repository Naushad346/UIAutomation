package stepDefinition;

import driverFactory.Driver;
import io.cucumber.java.en.Given;
import pages.BasePage;
import utils.ConfigReader;

public class CommonStefDef {

    BasePage basePage = new BasePage(Driver.getDriver());

    @Given("I am on the {string}")
    public void i_am_on_the(String pageName) {

        String url = ConfigReader.get(pageName);
        System.out.println("url = "+url);

        if (url == null) {
            throw new RuntimeException(
                    "No URL found for key: " + pageName);
        }

        basePage.open(url);
    }
}