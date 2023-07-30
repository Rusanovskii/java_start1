package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangeHelper extends HelperBase {

    public ChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) throws ClassNotFoundException {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void updatePass(String updatePass) {
        type(By.name("password"), updatePass);
        type(By.name("password_confirm"), updatePass);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void selectingUserForChanges() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement(By.xpath("html/body/table[3]/tbody/tr[4]/td[1]/a")).click();
    }

    public String getNameOfUser() {
        return wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    }

    public String getMailOfUser() {
        return wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    }

    public void resetPass() {
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void goToResetPage(String link) {
        wd.get(link);
    }

}
