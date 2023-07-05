package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ContactDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().сontactPage();
        if (app.contact().list().size() == 0) {
            app.contact().init();
            if (app.group().present1() || app.group().present2()) {
                app.goTo().groupPage();
                app.group().create(new GroupData("5", null, null));
                app.contact().init();
            }
            app.contact().create(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город", "5"));
        }
    }
    @Test
    public void testContactDelete() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }
}

