package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() ==0) {
            app.group().create(new GroupData()

                    .withName("1"));
        }
    }
    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()

                .withId(modifiedGroup.getId())
                .withName("1")
                .withHeader("2")
                .withFooter("3");

        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        assertEquals(before, after);
    }


}
