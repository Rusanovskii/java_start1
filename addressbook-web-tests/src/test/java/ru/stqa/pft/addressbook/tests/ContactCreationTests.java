package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().сontactPage();
        app.contact().init();
        if (app.group().present1() || app.group().present2()) {
            app.goTo().groupPage();
            app.group().create(new GroupData()

                    .withName("1"));

            app.contact().init();
        }
    }
    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                        .withName("Boris")
                        .withLastname("Krasava")
                        .withNickname("123")
                        .withPhone("+ 7 999 999 77 66")
                        .withMail("1@1.ru")
                        .withAddress("Питер")
                        .withGroup("1");

        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }
}