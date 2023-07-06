package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

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

        List<ContactData> before = app.contact().list();
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
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size()+1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }
}