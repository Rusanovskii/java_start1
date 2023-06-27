package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Борис", "Кирпичкин", "565", "+7 000 666 67 67", "444@555.ru", "Москва никогда не спит", null));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Борис", "Кирпичкин", "565", "+7 000 666 67 67", "444@555.ru", "Москва никогда не спит", null), false);
        app.getGroupHelper().submitContactModification();
        app.getNavigationHelper().gotoContactPage();
        app.logout();
    }
}
