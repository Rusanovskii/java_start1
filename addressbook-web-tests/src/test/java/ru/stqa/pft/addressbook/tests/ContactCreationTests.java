package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
private GroupData group;
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("1"));
        } else {
            app.goTo().сontactPage();
        }
}
@DataProvider
public Iterator<Object[]> validContactsFromCvs() throws IOException {
    File photo = new File("src/test/resources/111.png");
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
        String[] split = line.split(";");
        list.add(new Object[] {new ContactData().withName(split[0]).withLastname(split[1]).withNickname(split[2]).withAddress(split[3]).withHomePhone(split[4]).withMobilePhone(split[5]).withWorkPhone(split[6]).withPersonalMail(split[7]).withWorkMail(split[8]).withOtherMail(split[9]).withPhoto(photo)});
        line = reader.readLine();
    }
    return list.iterator();
}
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }
    @Test
    public void testContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        app.contact().init();
        app.contact().create(contact);
        app.goTo().сontactPage();
        int index = before.size() + 1;
        assertThat(app.contact().count(), equalTo(index));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}