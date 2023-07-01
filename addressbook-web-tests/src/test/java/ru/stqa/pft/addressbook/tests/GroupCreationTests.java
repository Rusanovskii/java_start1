package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("5", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
        app.logout();
    }

}
