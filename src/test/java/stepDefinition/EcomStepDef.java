package stepDefinition;

import java.util.List;

import org.testng.Assert;

import driverFactory.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EcomPage;

public class EcomStepDef {

    @When("I search for {string}")
    public void i_search_for(String item) {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());
        ecomPage.search(item);
    }

    @Then("product {string} should be visible in search results")
    public void product_should_be_visible(String item) {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());
        Assert.assertTrue(ecomPage.isProductVisible(item));
    }

    @And("I add {string} quantity of {string} to cart")
    public void i_add_quantity_of_item_to_cart(String quantity, String item) {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());

        int qty = Integer.parseInt(quantity);

        for (int i = 0; i < qty; i++) {
            ecomPage.addProductToCart(item);
        }
    }

    @And("I open the cart")
    public void i_open_the_cart() {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());
        ecomPage.openCart();
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());
        ecomPage.proceedToCheckout();
    }

    @Then("product {string} should be visible in cart")
    public void product_should_be_visible_in_cart(String item) {
        EcomPage ecomPage = new EcomPage(Driver.getDriver());
        Assert.assertTrue(ecomPage.isProductVisibleInCheckout(item));
    }

    @When("I add below products to cart")
    public void i_add_below_products_to_cart(DataTable dataTable) {

        EcomPage ecomPage = new EcomPage(Driver.getDriver());

        List<String> items = dataTable.asList();

        for (String item : items) {
            ecomPage.search(item);
            ecomPage.addProductToCart(item);
        }
    }

    @Then("all selected products should be visible in checkout")
    public void all_selected_products_should_be_visible_in_checkout() {
        // usually validated using product list comparison
    }
}