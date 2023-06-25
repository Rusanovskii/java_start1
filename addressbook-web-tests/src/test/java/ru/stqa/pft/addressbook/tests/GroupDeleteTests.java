package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import static java.lang.System.setProperty;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete() throws Exception {
        setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
