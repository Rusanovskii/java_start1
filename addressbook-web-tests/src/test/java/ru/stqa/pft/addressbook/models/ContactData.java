package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String name;
    private final String lastname;
    private final String nickname;
    private final String personalphone;
    private final String mail;
    private final String address;
    private  String group;

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public ContactData(int id, String name, String lastname, String nickname, String personalphone, String mail, String address, String group) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.nickname = nickname;
        this.personalphone = personalphone;
        this.mail = mail;
        this.address = address;
        this.group = group;
    }
    public ContactData(String name, String lastname, String nickname, String personalphone, String mail, String address, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastname = lastname;
        this.nickname = nickname;
        this.personalphone = personalphone;
        this.mail = mail;
        this.address = address;
        this.group = group;
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

    public String getPersonalphone() {
        return personalphone;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
