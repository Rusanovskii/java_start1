package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import static java.lang.System.setProperty;


public class ContactDeleteTests extends TestBase{
  @Test
  public void testContactDelete() throws Exception {
    setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город","5"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoContactPage();
    app.logout();
  }
}
