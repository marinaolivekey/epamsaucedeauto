package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    private final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(css = ".app_logo")
    private WebElement mainPageTitle;

    @Override
    protected MainPage openPage() {
        logger.info("Opening main page...");
        driver.navigate().to(BASE_URL);
        logger.info("Opened main page");
        return this;
    }

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getTitleLoggedInUser() {
        return mainPageTitle.getText();
    }
}
