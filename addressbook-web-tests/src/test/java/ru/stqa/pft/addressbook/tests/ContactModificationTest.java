package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {
    private GroupData group;
    private ContactData contact;
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        List<GroupData> groups = app.group().list();
        if (groups.size() == 0) {
            group = (new GroupData().withName("1"));
            app.group().create(group);
            app.contact().returnToContactPage();
        } else {
            group = groups.iterator().next();
            app.contact().returnToContactPage();
        }
        List<ContactData> contacts = app.contact().list();
        if (contacts.size() == 0) {
            contact = (new ContactData()
                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withPhone("+ 7 999 999 77 66")
                    .withMail("1@1.ru")
                    .withAddress("Питер")
                    .withGroup(group.getName()));
            app.contact().create(contact);
            app.contact().returnToContactPage();
        } else {
            group = groups.iterator().next();
            app.contact().returnToContactPage();
        }
    }
    @Test
    public void testContactModification() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()

                .withId(modifiedContact.getId())
                .withName("Pavel")
                .withLastname("Dava")
                .withNickname("321")
                .withPhone("+ 7 666 999 77 66")
                .withMail("2@2.ru")
                .withAddress("Москва");

        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
    }
}
