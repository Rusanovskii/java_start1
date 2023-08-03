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
    private WebDriver wd;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private ChangeHelper changeHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) throws IOException {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }


    public void stop() {
        if (wd != null){
        wd.quit();}
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper ==null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }
    public ChangeHelper changePass() {
        if (changeHelper == null){
            changeHelper = new ChangeHelper(this);
        }
        return changeHelper;
    }
    public FtpHelper ftp() {
        if (ftp ==null) {
        ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        // чтобы инициализация стала ленивой ее сюда переносим
        if (wd == null) {
            if (browser.equals(FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(CHROME)) {
                wd = new ChromeDriver();
                setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
            } else if (browser.equals(EDGE)) {
                wd = new EdgeDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
    public MailHelper mail() {
        if(mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
