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
            if(app.group().present1()||app.group().present2()){
                app.goTo().groupPage();
                app.group().create(new GroupData("5",null,null));
                app.contact().init();
            }
            app.contact().list();
            app.contact().create(new ContactData("Boris","Krasava","123","+ 7 999 999 77 66","1@1.ru","Питер",null));
        }
    }
    @Test
    public void testContactModification() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Boris", "Krasava", "321", "+ 7 666 999 77 66", "2@2.ru", "Москва", null);
        int index = before.size() - 1;
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
