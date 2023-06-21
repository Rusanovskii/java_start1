package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class ContactDeleteTests extends TestBase{
  @Test
  public void testContactDelete() throws Exception {
    selectContact();
    deleteSelectedContact();
    logout();
  }
}
