package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("1"));
            app.goTo().groupPage();
        }
        app.goTo().groupPage();
    }
    @Test
    public void testGroupDelete() throws Exception {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        int index = before.size() - 1;
        assertThat(app.group().count(), equalTo(index));
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.without(deletedGroup)));
    }
}
