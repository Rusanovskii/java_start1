package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;


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
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContact();
      app.logout();
    }
  }
