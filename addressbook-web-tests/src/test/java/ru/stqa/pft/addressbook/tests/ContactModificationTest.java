package ru.stqa.pft.addressbook.tests;
import ru.stqa.pft.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Борис", "Кирпичкин", "565", "+7 000 666 67 67", "444@555.ru", "Москва никогда не спит"));
        app.getGroupHelper().submitContactModification();
        app.getNavigationHelper().gotoContactPage();
        app.logout();
    }
}
