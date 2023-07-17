package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddGroupTest extends TestBase {
    private ContactData contact;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().сontactPage();
            File photo = new File("src/test/resources/111.png");
            contact = (new ContactData()
                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withAddress("г.Санкт-Петербург, ул.Строителей, дом 2/5, кв.12")
                    .withHomePhone("+ 7 999 999 77 66")
                    .withMobilePhone("7-666-666-77-66")
                    .withWorkPhone("7-999-666-77-66")
                    .withPersonalMail("1@1.ru")
                    .withWorkMail("3@3.ru")
                    .withOtherMail("PoBEdiTel@2.ru")
                    .withPhoto(photo));
            app.contact().create(contact);
            app.goTo().сontactPage();
        } else {
            app.goTo().сontactPage();
        }
    }

    @Test
    public void testAddInGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        GroupData before = app.group().groupEmpty(groups, contact);
        if (before == null) {
            before = app.db().groups().stream().max((g1, g2) -> Integer.max(g1.getId(), g2.getId())).get();
        }
        app.goTo().сontactPage();
        app.contact().addToGroup(contact.getId(), before);
        GroupData after = app.db().group(before.getId());
        assertThat(after.getContacts(),equalTo(before.getContacts().withAdded(contact)));
    }
}
