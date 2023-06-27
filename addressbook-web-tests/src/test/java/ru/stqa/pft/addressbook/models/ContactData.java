package ru.stqa.pft.addressbook.models;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String nickname;
    private final String personalphone;
    private final String mail;
    private final String address;
    private  String group;

    public ContactData(String name, String lastname, String nickname, String personalphone, String mail, String address, String group) {
        this.name = name;
        this.lastname = lastname;
        this.nickname = nickname;
        this.personalphone = personalphone;
        this.mail = mail;
        this.address = address;
        this.group = group;
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
}
