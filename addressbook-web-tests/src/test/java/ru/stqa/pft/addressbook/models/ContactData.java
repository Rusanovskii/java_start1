package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String name;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "nickname")
    private String nickname;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String personalMail;
    @Expose
    @Transient
    private String allMails;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String workMail;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String otherMail;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Transient
    private String group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(nickname, that.nickname) && Objects.equals(allPhones, that.allPhones) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(personalMail, that.personalMail) && Objects.equals(allMails, that.allMails) && Objects.equals(workMail, that.workMail) && Objects.equals(otherMail, that.otherMail) && Objects.equals(address, that.address) && Objects.equals(group, that.group) && Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, nickname, allPhones, homePhone, mobilePhone, workPhone, personalMail, allMails, workMail, otherMail, address, group, groups);
    }

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @Transient
    private Set<GroupData> groups = new HashSet<GroupData>();

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
        this.photo = photo.getPath();
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
        return new File(photo);
    }
    public Groups getGroups(){
        return new Groups(groups);
    }

}