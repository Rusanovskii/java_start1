package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class GroupDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()

                    .withName("2"));
        }
    }
    @Test
    public void testGroupDelete() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(),before.size() - 1);

        before.remove(deletedGroup);
        assertEquals(before, after);
    }
}
