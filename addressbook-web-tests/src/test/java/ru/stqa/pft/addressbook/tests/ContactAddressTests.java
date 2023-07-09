package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressTests extends TestBase{
    private ContactData contact;
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToContactPage();
        Contacts contacts = app.contact().all();
        if (contacts.size() == 0) {
            contact = (new ContactData()
                    .withName("Boris")
                    .withLastname("Krasava")
                    .withNickname("123")
                    .withAddress("г.Санкт-Петербург, ул.Строителей, дом 25, кв.12")
                    .withHomePhone("+7(999)9997766")
                    .withMobilePhone("+7-666-666-77-66")
                    .withOtherMail("3@3.ru")
                    .withPersonalMail("1@1.ru")
                    .withWorkMail("2@2.ru")
                    .withWorkMail("PoBEdiTel@2.ru"));
            app.contact().create(contact);
            app.contact().returnToContactPage();
        } else {
            app.contact().returnToContactPage();
        }
    }

    @Test
    public void testContactAdderess() {
        app.goTo().сontactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoAboutAddress = app.contact().infoAboutAddress(contact);

        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoAboutAddress.getAddress())));
    }
    public String cleaned(String phone) {
        return phone.replaceAll("/", "-").replaceAll("\\n", "\\r");
    }
}
