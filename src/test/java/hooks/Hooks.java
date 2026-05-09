package hooks;

import driverFactory.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ScreenShotUtil;

public class Hooks {

    @Before
    public void setup() {
        Driver.initDriver(ConfigReader.get("browser"));
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenShotUtil.capture(scenario.getName());
        }

        Driver.quitDriver();
    }
}