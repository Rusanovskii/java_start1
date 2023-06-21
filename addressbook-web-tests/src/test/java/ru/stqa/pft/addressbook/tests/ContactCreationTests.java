package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm();
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoContactPage();
    app.logout();
  }

}
