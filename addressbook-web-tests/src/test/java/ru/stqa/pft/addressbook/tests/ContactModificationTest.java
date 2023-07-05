package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().сontactPage();
        if(app.contact().list().size() == 0){
            app.contact().init();
            if(!app.group().present1()||!app.group().present2()){
                app.goTo().groupPage();
                app.group().create(new GroupData()

                        .withName("1"));

                app.contact().init();
            }
            app.contact().list();
            app.contact().create(new ContactData()

                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withPhone("+ 7 999 999 77 66")
                    .withMail("1@1.ru")
                    .withAddress("Питер")
                    .withGroup("1"));
        }
    }
    @Test
    public void testContactModification() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()

                .withId(before.get(index).getId())
                .withName("Pavel")
                .withLastname("Dava")
                .withNickname("321")
                .withPhone("+ 7 666 999 77 66")
                .withMail("2@2.ru")
                .withAddress("Москва");

        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }
}
