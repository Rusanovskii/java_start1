package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {
    private GroupData group;
    private ContactData contact;
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        Groups groups = app.group().all();
        if (groups.size() == 0) {
            group = (new GroupData().withName("1"));
            app.group().create(group);
            app.contact().returnToContactPage();
        } else {
            group = groups.iterator().next();
            app.contact().returnToContactPage();
        }
        Contacts contacts = app.contact().all();
        if (contacts.size() == 0) {
            contact = (new ContactData().withName("Boris").withLastname("Krasava").withGroup(group.getName()));
            app.contact().create(contact);
            app.contact().returnToContactPage();
        } else {
            group = groups.iterator().next();
            app.contact().returnToContactPage();
        }
    }
    @Test
    public void testContactDelete() throws Exception {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        int index = before.size() - 1;
        assertThat(app.contact().count(), equalTo(index));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.without(deletedContact)));
    }
}

