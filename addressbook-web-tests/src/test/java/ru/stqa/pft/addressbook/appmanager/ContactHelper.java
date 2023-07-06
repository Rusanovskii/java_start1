package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;

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

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
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

   // public void selectContact() {
   //     click(By.name("selected[]"));
   // }
   public void selectContact(int index) {
       wd.findElements(name("selected[]")).get(index).click();
   }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void returnToContactPage() {
        wd.findElement(By.linkText("home")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();

    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }


    public List<ContactData> list() {
            List<ContactData> contacts = new ArrayList<>();
            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements) {
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
                String name = element.findElement(By.xpath(".//td[3]")).getText();
                String lastname = element.findElement(By.xpath(".//td[2]")).getText();
                contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
            }
            return contacts;
        }
    public void modify(ContactData contact, int index) {
        initContactModification(index);
        fillContactForm(contact,false);
        submitContactModification();
        returnToContactPage();
    }
    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
    }
    }


