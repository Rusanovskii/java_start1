package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
private GroupData group;
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
}

    @Test
    public void testContactCreation() {

        Contacts before = app.contact().all();
        app.contact().init();
        ContactData contact = new ContactData()
                        .withName("Boris")
                        .withLastname("Krasava")
                        .withNickname("123")
                        .withPhone("+ 7 999 999 77 66")
                        .withMail("1@1.ru")
                        .withAddress("Питер")
                        .withGroup(group.getName());

        app.contact().create(contact);
        app.goTo().сontactPage();
        Contacts after = app.contact().all();
        int index = before.size() + 1;
        assertThat(after.size(), equalTo( index));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}