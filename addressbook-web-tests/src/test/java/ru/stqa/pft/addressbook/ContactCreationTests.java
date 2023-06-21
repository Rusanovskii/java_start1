package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm();
    submitContactCreation();
    gotoContactPage();
    logout();
  }

}
