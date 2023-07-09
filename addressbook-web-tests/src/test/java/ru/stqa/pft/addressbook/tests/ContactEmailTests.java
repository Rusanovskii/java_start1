package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
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
                    .withWorkPhone("+7-888-666-77-66")
                    .withPersonalMail("1_win@1.ru")
                    .withOtherMail("3@3.ru"));
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
        ContactData contactInfoAboutEmails = app.contact().infoAboutEmails(contact);

        assertThat(contact.getAllMails(), equalTo(cleaned(mergeMails(contactInfoAboutEmails))));
    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getPersonalMail(),contact.getWorkMail(),contact.getOtherMail())
                .stream().filter((s)-> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return mail.replaceAll("[A-Z]", "[a-z]").replaceAll("_", "-");
    }
}

