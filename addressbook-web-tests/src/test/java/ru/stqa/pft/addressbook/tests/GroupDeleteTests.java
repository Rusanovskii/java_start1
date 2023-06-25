package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.GroupData;

import static java.lang.System.setProperty;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete() throws Exception {
        setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("5", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
