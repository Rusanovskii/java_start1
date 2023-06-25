package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {
    private final String browser;
    public WebDriver wd;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(EDGE)) {
            wd = new EdgeDriver();
        }

        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        wd.get("http://localhost/addressbook/");
        login();
    }

    public void login() {
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public void logout() {

        wd.findElement(By.linkText("Logout")).click();
    }

    public GroupHelper getGroupHelper() {

        return groupHelper;
    }

    public ContactHelper getContactHelper() {

        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {

        return navigationHelper;
    }
}
