package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
    private ContactData contact;
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("1"));
        } if  (app.db().contacts().size() == 0){
            app.goTo().сontactPage();
        File photo = new File("src/test/resources/111.png");
        Groups groups = app.db().groups();
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
                    .withPhoto(photo)
                    .inGroup(groups.iterator().next()));
            app.contact().create(contact);
            app.contact().returnToContactPage();
        } else {
            app.goTo().сontactPage();
        }
    }
    @Test
    public void testContactModification() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/222.png");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withName("Pavel")
                .withLastname("Dava")
                .withNickname("321")
                .withAddress("г.Москва, ул.Строителей, дом 2/5, кв.12")
                .withHomePhone("+ 7 444 999 77 66")
                .withMobilePhone("7-333-666-77-66")
                .withWorkPhone("7-555-666-77-66")
                .withPersonalMail("1@2.ru")
                .withWorkMail("22@3.ru")
                .withOtherMail("111l@2.ru")
                .withPhoto(photo);
        app.goTo().сontactPage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo( before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
