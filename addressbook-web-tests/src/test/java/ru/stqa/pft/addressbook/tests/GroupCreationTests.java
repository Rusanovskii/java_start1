package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import static java.lang.System.setProperty;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("5", "5", "5"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        app.logout();
    }

}
