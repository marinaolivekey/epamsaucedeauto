package Page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TestDataReader;

public class LoginPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final String PAGE_URL = TestDataReader.getTestData("testdata.loginpage.url");

    @FindBy(id = "user-name")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    @FindBy(css = ".app_logo")
    private WebElement dashboardTitle;


    public LoginPage(WebDriver driver) {
        super(driver);
        // PageFactory.initElements(this.driver, this); PageFactory.initElements is now handled in AbstractPage
    }


    @Override
    public LoginPage openPage() {
        logger.info("Login page is opening ...");
        driver.navigate().to(PAGE_URL);
        logger.info("Login page opened");
        return this;
    }


    // STEPS

    public MainPage validLogin(User user) {
        logger.info("Logging in ...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        loginButton.click();
        logger.info("Login performed");
        return new MainPage(driver);
    }

    public LoginPage typeAndClearAllCredentials(User user) {
        logger.info("Username and Password filling...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        logger.info("Username and Password filled");

        inputUsername.click();
        inputUsername.clear();
        inputUsername.sendKeys("");// Ensure field is empty
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputUsername, ""));
        logger.info("Cleared username field");

        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("");// Ensure field is empty
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputPassword, ""));
        logger.info("Cleared password field");
        return this;
    }

    public LoginPage typeAllAndClearPassword(User user) {
        logger.info("Username and Password filling...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        logger.info("Username and Password filled");

        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("");// Ensure field is empty
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputPassword, ""));
        logger.info("Cleared password field");
        return this;
    }

    public LoginPage typeAllCredentials(User user) {
        logger.info("Username and Password filling...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        logger.info("Username and Password filled");
        return this;
    }

    public LoginPage clickLogin() {
        logger.info("Clicking login button ...");
        loginButton.click();
        logger.info("Clicked login button");
        return this;
    }

    public String getErrorEmptyMessage() {
        logger.info("Loading error message ...");
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String error = errorMessage.getText();
        logger.info("Got error message: '{}'", error);
        return error;
    }

    public String getErrorLockedMessageIfLockedUser() {
        logger.info("Loading error message ...");
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String error = errorMessage.getText();
        logger.info("Got error message: '{}'", error);
        return error;
    }
}
