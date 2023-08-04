package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class RestHelper {
    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Issue getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String json = RestAssured.get(String.format(app.getProperty("web.rest.url") + "/issues/%s.json", issueId)).asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
        return new Issue().withId(issue.getAsJsonObject().get("id").getAsInt())
                .withSummary(issue.getAsJsonObject().get("subject").getAsString())
                .withDescription(issue.getAsJsonObject().get("description").getAsString())
                .withStatus(issue.getAsJsonObject().get("state_name").getAsString());
    }
}
