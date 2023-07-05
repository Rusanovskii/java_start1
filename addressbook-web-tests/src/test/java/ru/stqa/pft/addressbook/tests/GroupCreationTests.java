package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
    }

    @Test
    public void testGroupCreation() {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData()

                .withName("1");

        app.group().create(group);
        List<GroupData> after = app.group().list();
        int index = before.size() + 1;
        assertEquals(after.size(), index);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }

}
