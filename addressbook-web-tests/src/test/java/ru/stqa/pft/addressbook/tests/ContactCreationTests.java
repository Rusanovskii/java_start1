package ru.stqa.pft.addressbook.tests;
import ru.stqa.pft.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation(){
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Boris", "Krasava", "123", "+ 7 999 999 77 66", "1@1.ru", "Питер хороший город"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoContactPage();
    app.logout();
  }

}
