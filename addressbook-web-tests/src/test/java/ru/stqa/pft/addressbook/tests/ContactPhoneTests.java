package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    private ContactData contact;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().сontactPage();
        Contacts contacts = app.contact().all();
        if (contacts.size() == 0) {
            contact = (new ContactData()
                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withAddress("Питер")
                    .withHomePhone("+7(999)9997766")
                    .withMobilePhone("+7-666-666-77-66")
                    .withPersonalMail("1@1.ru")
                    .withWorkMail("2@2.ru"));
            app.contact().create(contact);
            app.contact().returnToContactPage();
        } else {
            app.contact().returnToContactPage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().сontactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoAboutPhones = app.contact().infoAboutPhones(contact);

        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoAboutPhones.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoAboutPhones.getMobilePhone())));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
