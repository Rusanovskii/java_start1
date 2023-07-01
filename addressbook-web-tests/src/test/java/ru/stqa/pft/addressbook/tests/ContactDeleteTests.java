package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ContactDeleteTests extends TestBase {
  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().initContactCreation();
      if (!app.getContactHelper().checkListOfGroups()) {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("5", null, null));
        app.getContactHelper().initContactCreation();
      }
        app.getContactHelper().selectGroupByList();
        app.getContactHelper().createContact(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город", "5"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContact();
      List<ContactData> after = app.getContactHelper().getContactList();
      assertEquals(after.size(), before.size() - 1);

      before.remove(before.size() -1);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      assertEquals(before, after);

      app.logout();
    }
  }
