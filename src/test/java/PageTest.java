import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class PageTest extends BaseTest {
    //private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    void openMain() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMp3playersWindow();
        mainPage.clickDropDwnShowAll();
        mainPage.clickListButton();
        Thread.sleep(10000);
    }

    private static int rowNumber = 0;

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/products.csv", numLinesToSkip = 1)
    void csvFileReadFromFile(String product) throws InterruptedException {
        int randomNumber = (int)(Math.random() * 20) + 1;
        MainPage mainPage = new MainPage(driver);
        mainPage.openMp3playersWindow();
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Show All MP3 Players")));
        mainPage.clickDropDwnShowAll();
        mainPage.clickListButton();

        Assertions.assertTrue(mainPage.titlesContains(product), product + " does not exist in the eshop");

        mainPage.openItems(rowNumber);
        rowNumber++;

        mainPage.enterQuantity(randomNumber);
        Thread.sleep(2000);
        assertTrue(mainPage.checkDefaultItemCart());
        mainPage.clickAddToCartButton();
        Thread.sleep(500);
        assertTrue(mainPage.checkSuccessAlert(product));
        mainPage.checkSubmittedItemCart(randomNumber);
        assertTrue(mainPage.checkSubmittedItemCart(randomNumber));
    }


}
