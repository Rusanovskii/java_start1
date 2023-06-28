package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactPage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().initContactCreation();
            if (!app.getContactHelper().checkListOfGroups()) {
                app.getNavigationHelper().gotoGroupPage();
                app.getGroupHelper().createGroup(new GroupData("5", null, null));
                app.getContactHelper().initContactCreation();
            }
            app.getContactHelper().selectGroupByList();
            app.getContactHelper().createContact(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город", "5"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Борис", "Кирпичкин", "565", "+7 000 666 67 67", "444@555.ru", "Москва никогда не спит", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoContactPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
        app.logout();
    }
}
