package ru.stqa.pft.addressbook.tests;
import ru.stqa.pft.addressbook.models.ContactData;
import org.testng.annotations.Test;

import static java.lang.System.setProperty;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation(){
    setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город","5"));
    app.logout();
  }

}
