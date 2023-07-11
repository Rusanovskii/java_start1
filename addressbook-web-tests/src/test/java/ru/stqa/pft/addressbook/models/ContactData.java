package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String name;
    @Expose
    private String lastname;
    @Expose
    private String nickname;
    @Expose
    private String allPhones;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String personalMail;
    @Expose
    private String allMails;
    @Expose
    private String workMail;
    @Expose
    private String otherMail;
    @Expose
    private String address;
    @Expose
    private String group;
    @Expose
    private File photo;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withPersonalMail(String personalMail) {
        this.personalMail = personalMail;
        return this;
    }
    public ContactData withWorkMail(String workMail) {
        this.workMail = workMail;
        return this;
    }
    public ContactData withOtherMail(String otherMail) {
        this.otherMail = otherMail;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }
    public String getAllPhones() {
        return allPhones;
    }

    public String getHomePhone() {
        return homePhone;
    }
    public String getMobilePhone() {return mobilePhone; }
    public String getWorkPhone() {return workPhone; }


    public String getAllMails() {
        return allMails;
    }

    public String getPersonalMail() {
        return personalMail;
    }
    public String getWorkMail() {return workMail; }
    public String getOtherMail() {return otherMail; }

    public String getAddress() {
        return address;
    }
    public String getGroup() { return group;}
    public File getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

}