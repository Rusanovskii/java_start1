package ru.stqa.pft.addressbook;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String nickname;
    private final String personalphone;
    private final String mail;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String groupid;
    private final String address;

    public ContactData(String name, String lastname, String nickname, String personalphone, String mail, String bday, String bmonth, String byear, String groupid, String address) {
        this.name = name;
        this.lastname = lastname;
        this.nickname = nickname;
        this.personalphone = personalphone;
        this.mail = mail;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.groupid = groupid;
        this.address = address;
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

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroupid() {
        return groupid;
    }

    public String getAddress() {
        return address;
    }
}
