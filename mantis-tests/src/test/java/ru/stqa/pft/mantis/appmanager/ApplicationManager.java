package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    public WebDriver wd;

    public ApplicationManager(String browser) throws IOException {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals(FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(CHROME)) {
            wd = new ChromeDriver();
            setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        } else if (browser.equals(EDGE)) {
            wd = new EdgeDriver();
        }

        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

    }

    public void stop() {
        wd.quit();
    }
}
