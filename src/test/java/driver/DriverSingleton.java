package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {

    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
    private static WebDriver driver;

    private DriverSingleton() {
    } // constructor

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser");
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().clearDriverCache().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.error("No browser found with name -> {}", browser);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

/*
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private DriverSingleton() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (threadDriver.get() == null) {
            String browser = System.getProperty("browser").toLowerCase();
            logger.info("Initializing driver for browser: {}", browser);

            try {
                switch (browser) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        threadDriver.set(new FirefoxDriver());
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        threadDriver.set(new EdgeDriver());
                        break;
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        threadDriver.set(new ChromeDriver());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }

                WebDriver driver = threadDriver.get();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
                logger.info("Driver initialized and configured for {}", browser);
            } catch (Exception e) {
                logger.error("Failed to initialize driver: {}", e.getMessage());
                throw e;
            }
        }
        return threadDriver.get();
    }

    public static void closeDriver() {
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            logger.info("Quitting driver");
            driver.quit();
            threadDriver.remove();
        }
    }
}
 */