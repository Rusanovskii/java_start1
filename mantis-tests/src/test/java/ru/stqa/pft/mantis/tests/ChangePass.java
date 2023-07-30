package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePass extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void passChange() throws MessagingException, IOException, ClassNotFoundException {
        // Начало теста по смене пароля - логин
        app.changePass().login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
        // Переход на нужную страницу и выбор пользователя для смены пароля
        app.changePass().selectingUserForChanges();
        String user = app.changePass().getNameOfUser();
        String email = app.changePass().getMailOfUser();
        // Отправка запроса на смену пароля
        app.changePass().resetPass();
        // Получение письма
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findResetLink(mailMessages, email);
        // Переход на страницу смены пароля по ссылке
        app.changePass().goToResetPage(confirmationLink);
        String password = "password";
        // Ввод пароля
        app.changePass().updatePass(password);
        // Проверка авторизации с "новым" паролем
        assertTrue(app.newSession().login(user,password));
    }
    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
