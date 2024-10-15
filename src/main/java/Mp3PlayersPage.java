import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Mp3PlayersPage extends BasePage{
     @FindBy(css="button#button-list")
     private WebElement listButton;

    public Mp3PlayersPage(WebDriver driver) {
        super(driver);
    }
    public void clickListButton(){
        listButton.click();
    }
}
