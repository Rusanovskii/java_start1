package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {
    private ContactData contact;
    @BeforeMethod
    public void ensurePreconditions(){
        if  (app.db().contacts().size() == 0){
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
            app.contact().returnToContactPage();
    } else {
        app.goTo().сontactPage();
    }
    }
    @Test
    public void testContactDelete() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        int index = before.size() - 1;
        assertThat(app.contact().count(), equalTo(index));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.without(deletedContact)));
    }
}

