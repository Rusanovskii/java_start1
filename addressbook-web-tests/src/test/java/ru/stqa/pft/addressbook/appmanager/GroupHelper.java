package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.name;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void submitGroupCreation() {

        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }


    public void initGroupCreation() {

        click(By.name("new"));
    }

    public void deleteSelectedGroups() {

        click(By.name("delete"));
    }

    public void selectGroup(int index) {

        wd.findElements(name("selected[]")).get(index).click();
    }
    public void selectGroupById(int id) {

        wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }
    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}

