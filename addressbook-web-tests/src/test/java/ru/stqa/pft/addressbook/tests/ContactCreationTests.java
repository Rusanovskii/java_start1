package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
private GroupData group;
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
}

    @Test
    public void testContactCreation() {

        Set<ContactData> before = app.contact().all();
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
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size()+1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);
    }
}