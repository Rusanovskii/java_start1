package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
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
            contact = (new ContactData()
                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withHomePhone("+ 7 999 999 77 66")
                    .withPersonalMail("1@1.ru")
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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()

                .withId(modifiedContact.getId())
                .withName("Pavel")
                .withLastname("Dava")
                .withNickname("321")
                .withAddress("Москва")
                .withHomePhone("+ 7 999 999 77 66")
                .withMobilePhone("7-666-666-77-66")
                .withPersonalMail("2@2.ru")
                .withWorkMail("3@3.ru");


        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo( before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.without(modifiedContact).withAdded(contact)));
    }
}
