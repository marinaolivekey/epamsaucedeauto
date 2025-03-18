package Page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = ".error-message-container")
    private WebElement errorMessage;

    @FindBy(className = ".app_logo")
    private WebElement dashboardTitle;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    @Override
    public LoginPage openPage() {
        logger.info("Login page is openning ...");
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

    public LoginPage loginWithEmptyUsername(User user) {
        logger.info("Logging in with Empty Username...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        inputUsername.click();
        inputUsername.clear();
        inputPassword.clear();
        loginButton.click();
        logger.info("no Login with empty Username with Error message");
        return this;
    }

    public LoginPage loginWithEmptyPassword(User user) {
        logger.info("Logging in with Empty Password ...");
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        inputPassword.click();
        inputPassword.clear();
        loginButton.click();
        logger.info("no Login with empty Password with Error message");
        return this;
    }

    public String getErrorUsernameEmptyMessage() {
        return errorMessage.getText();
    }

    public String getErrorPasswordEmptyMessage() {
        return errorMessage.getText();
    }


}
