package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;

import static org.openqa.selenium.By.name;
import static org.testng.AssertJUnit.assertTrue;

public class ContactHelper extends HelperBase {
    public boolean acceptNextAlert = true;
    private ContactData contact;

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void initContactCreation() {

        click(By.linkText("add new"));
    }

    public void submitContactCreation() {

        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(name("firstname"), contactData.getName());
        type(name("lastname"), contactData.getLastname());
        type(name("nickname"), contactData.getNickname());
        type(name("mobile"), contactData.getPersonalphone());
        type(name("email"), contactData.getMail());
        type(name("address2"), contactData.getAddress());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void selectContact() {

        click(name("selected[]"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
        click(By.linkText("Logout"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact,true);
        submitContactCreation();
        returnToContactPage();
    }
}

