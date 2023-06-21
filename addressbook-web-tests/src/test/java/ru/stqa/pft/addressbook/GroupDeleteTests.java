package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
