package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
    }

    @Test
    public void testGroupCreation() {
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData()

                .withName("1");

        app.group().create(group);
        Set<GroupData> after = app.group().all();
        int index = before.size() + 1;
        assertEquals(after.size(), index);

        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        assertEquals(before, after);
    }

}
