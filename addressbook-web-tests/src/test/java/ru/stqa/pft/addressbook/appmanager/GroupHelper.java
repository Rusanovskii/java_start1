package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.ArrayList;
import java.util.List;

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
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }
    public void modify(GroupData group, int index) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }
    public boolean present2() {
        try {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("5");
        } catch (Exception NoSuchElementException) {
            selectByIndex(name("new_group"), 0);
        }
        return false;
    }


    public boolean present1() {
        if (checkListOfGroups()) {
            return true;
        }
        return false;
    }

    public boolean checkListOfGroups() {
        click(By.name("new_group"));
        new Select(wd.findElement(name("new_group"))).selectByValue(String.valueOf("[none]"));
        return true;
    }
}
