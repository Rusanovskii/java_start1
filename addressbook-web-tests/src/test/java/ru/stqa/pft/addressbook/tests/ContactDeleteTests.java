package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import static java.lang.System.setProperty;


public class ContactDeleteTests extends TestBase{
  @Test
  public void testContactDelete() throws Exception {
    setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.logout();
  }
}
