package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import static java.lang.System.setProperty;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("3", "3", "3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
