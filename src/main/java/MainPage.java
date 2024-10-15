import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = ".dropdown:nth-of-type(8) [data-bs-toggle]")
    private WebElement mp3Players;
    @FindBy(css = ".dropdown:nth-of-type(8) .see-all")
    private WebElement dropDwnShowAll;
    @FindBy(css = "button#button-list")
    private WebElement listButton;

    @FindBy(css = "div.description a")
    private List<WebElement> itemTitles;
    @FindBy(id = "input-quantity")
    private WebElement qtyInputField;
    @FindBy(id="button-cart")
    private WebElement addToCartButton;
    @FindBy(id="alert")
    private  WebElement alertText;
    @FindBy(xpath = "//div[@id='header-cart']//button[@type='button']")
    private WebElement itemCart;
    @FindBy(css=".price-new")
    private WebElement priceTag;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMp3playersWindow() {
        Actions actions = new Actions(driver);
        actions.moveToElement(mp3Players).perform();
    }

    public void clickDropDwnShowAll() {
        dropDwnShowAll.click();
    }

    public void clickListButton() {
        listButton.click();
    }

    public boolean titlesContains(String name) {
        for (WebElement el : itemTitles) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void openItems(Integer elementNumber) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemTitles.get(elementNumber));
        Thread.sleep(500);
        itemTitles.get(elementNumber).click();
    }
    public void enterQuantity(int randomValue){
        qtyInputField.clear();
        qtyInputField.sendKeys(Integer.toString(randomValue));
    }
    public void clickAddToCartButton(){
        addToCartButton.click();
    }
    public boolean checkSuccessAlert(String product){
        String expectedAlert = "Success: You have added " + product + " to your shopping cart!";
        String actualAlert = alertText.getText();
        return expectedAlert.equals(actualAlert);
    }
    public boolean checkDefaultItemCart(){
        String defaultItemCart = itemCart.getText();
        String expectedDefaultItemCart = "0 item(s) - $0.00";
        return defaultItemCart.equals(expectedDefaultItemCart);
    }

    public boolean checkSubmittedItemCart(int quantity){
        String price = priceTag.getText();
        double strippedPrice = Double.parseDouble(price.replace("$", ""));
        String itemsCartField = itemCart.getText();
        double totalAmount = strippedPrice * quantity;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String formattedAmount = decimalFormat.format(totalAmount);
        String expectedItemCart = quantity + " item(s) - $" + formattedAmount;

        return expectedItemCart.equals(itemsCartField);
    }
}
