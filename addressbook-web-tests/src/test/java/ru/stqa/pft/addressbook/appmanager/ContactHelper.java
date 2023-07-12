package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.name;
import static org.testng.AssertJUnit.assertTrue;

public class ContactHelper extends HelperBase {
    public boolean acceptNextAlert = true;


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void init() {

        click(By.linkText("add new"));
    }

    public void submit() {

        click(By.name("submit"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(name("firstname"), contactData.getName());
        type(name("lastname"), contactData.getLastname());
        type(name("nickname"), contactData.getNickname());
        type(name("address"), contactData.getAddress());
        type(name("home"), contactData.getHomePhone());
        type(name("mobile"), contactData.getMobilePhone());
        type(name("work"), contactData.getWorkPhone());
        type(name("email"), contactData.getPersonalMail());
        type(name("email2"), contactData.getWorkMail());
        type(name("email3"), contactData.getOtherMail());
        attach(name("photo"), contactData.getPhoto());


        if (creation) {
            try {
                selectByText(By.name("new_group"), contactData.getGroup());

            } catch (Exception NoSuchElementException) {
                selectByIndex(By.name("new_group"));
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
    public void attach(By locator, File file) {
        if(file != null){
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }
    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value= '" +id+ "']")).click();
    }

    private static void initContactModification(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    public void create(ContactData contact) {
        init();
        fillForm(contact, true);
        submit();
        contactCache = null;

    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void modify(ContactData contact) {

        initContactModification(contact.getId());
        fillForm(contact,false);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLastname(lastname));
        }
        return contacts;
    }
    public ContactData infoAboutEmails(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String personalMail = wd.findElement(By.name("email")).getAttribute("value");
        String workMail = wd.findElement(By.name("email2")).getAttribute("value");
        String otherMail = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname)
                .withPersonalMail(personalMail).withWorkMail(workMail).withOtherMail(otherMail);
    }
    public ContactData infoAboutAddress(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname).withAddress(address);
    }
    public ContactData infoAboutPhones(ContactData contact) {
        initContactModification(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }
    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allMails = element.findElement(By.xpath(".//td[5]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLastname(lastname)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllMails(allMails));

        }
        return new Contacts(contactCache);
    }
    public Contacts contactCache = null;

    public int count() {
        return wd.findElements(name("selected[]")).size();
    }


}


