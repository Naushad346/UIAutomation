package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EcomPage extends BasePage {

    private By searchBox = By.cssSelector(".search-keyword");
    private By productNames = By.cssSelector("h4.product-name");
    private By addToCartButtons = By.xpath("//button[text()='ADD TO CART']");
    private By cartIcon = By.cssSelector("a.cart-icon");
    private By proceedToCheckout = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    private By checkoutProducts = By.cssSelector(".product-name");

    public EcomPage(WebDriver driver) {
        super(driver);
    }

    public void search(String item) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(item);
    }

    public boolean isProductVisible(String item) {

        List<WebElement> products = driver.findElements(productNames);

        for (WebElement product : products) {
            if (product.getText().toLowerCase().contains(item.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public void addProductToCart(String item) {

        List<WebElement> products = driver.findElements(productNames);
        List<WebElement> buttons = driver.findElements(addToCartButtons);

        for (int i = 0; i < products.size(); i++) {

            String productName =
                    products.get(i).getText().split("-")[0].trim();

            if (productName.equalsIgnoreCase(item)) {
                buttons.get(i).click();
                break;
            }
        }
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckout).click();
    }

    public boolean isProductVisibleInCheckout(String item) {

        List<WebElement> products = driver.findElements(checkoutProducts);

        for (WebElement product : products) {
            if (product.getText().toLowerCase().contains(item.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}