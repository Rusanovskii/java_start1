package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c",description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format", required = true)
    public String format;
    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

        private void saveAsCsv (List < ContactData > contacts, File file) throws IOException {
            Writer writer = new FileWriter(file);
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(), contact.getNickname(), contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPersonalMail(), contact.getWorkMail(), contact.getOtherMail()));
            }
            writer.close();
        }

        private List<ContactData> generateContact ( int count){
            List<ContactData> contacts = new ArrayList<ContactData>();
            for (int i = 0; i < count; i++) {
                contacts.add(new ContactData().withName(String.format("nome %s", i))
                        .withLastname(String.format("lastnome %s", i))
                        .withNickname(String.format("nickname %s", i))
                        .withAddress(String.format("address %s", i))
                        .withHomePhone(String.format("+ 7 999 999 77 %s", i))
                        .withMobilePhone(String.format("+ 7 999 999 77 %s", i))
                        .withWorkPhone(String.format("+ 7 999 999 77 %s", i))
                        .withPersonalMail(String.format("%s@mail.ru", i))
                        .withWorkMail(String.format("%s@mail.ru", i))
                        .withOtherMail(String.format("%s@mail.ru", i)));
            }
            return contacts;
        }

        private void saveAsJson (List < ContactData > contacts, File file) throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(contacts);
            try (Writer writer = new FileWriter(file)) {
                writer.write(json);
            }
        }
    }
