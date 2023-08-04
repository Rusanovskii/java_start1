package ru.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{

    //@Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        //skipIfNotFixed(2); - тест для soap
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
// Перед выполнением теста проверяем статус баг-репорта, чтобы пропускать тест, если он не исправен
       // skipIfNotFixed(2);
        // Проверка по rest
        skipIfNotFixedByBugify(596);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
