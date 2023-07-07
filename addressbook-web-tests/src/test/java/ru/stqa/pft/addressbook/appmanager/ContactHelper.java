package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

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
        type(name("mobile"), contactData.getPhone());
        type(name("email"), contactData.getMail());
        type(name("address2"), contactData.getAddress());

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

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
        try {
            Thread.sleep(4000);
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

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void returnToContactPage() {
        click(By.linkText("home"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(ContactData contact) {
        init();
        fillForm(contact, true);
        submit();

    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void modify(ContactData contact) {

        initContactModification(contact.getId());
        fillForm(contact,false);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
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
    public Contacts all() {
        Contacts contacts = new Contacts();
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

}


