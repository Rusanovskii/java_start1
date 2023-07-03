package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
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

    public void selectContact(int index) {
        wd.findElements(name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        returnToContactPage();
    }

    public boolean selectGroupByList1() {
        try {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("5");
        } catch (Exception NoSuchElementException) {
            selectByIndex(name("new_group"), 0);
        }
        return false;
    }


    public boolean selectGroupByList() {
        if (checkListOfGroups()) {
            return true;
        }
        return false;
    }

    public boolean checkListOfGroups() {
        click(By.name("new_group"));
        new Select(wd.findElement(name("new_group"))).selectByValue(String.valueOf("[none]"));
        return true;
    }
    public boolean checkListOfGroups1() {
        click(By.name("new_group"));
        wd.findElement(name("new_group")).findElement(By.xpath(("(.//*[normalize-space(text()) and normalize-space(.)='Secondary'])[1]/preceding::option[5]")));
        return true;
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }


    public List<ContactData> getContactList() {
            List<ContactData> contacts = new ArrayList<>();
            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements) {
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
                String name = element.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[3]")).getText();
                String lastname = element.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[2]")).getText();
                ContactData contact = new ContactData(id, name, lastname, null, null, null, null, null);
                contacts.add(contact);
            }
            return contacts;
        }
    }


