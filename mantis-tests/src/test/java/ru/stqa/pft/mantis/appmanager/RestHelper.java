package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class RestHelper {
    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public String getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String json = RestAssured.get(String.format(app.getProperty("web.rest.url") + "/issues/%s.json", issueId)).asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonArray issue = parsed.getAsJsonObject().getAsJsonArray("issues");
        return issue.get(0).getAsJsonObject().get("state_name").getAsString();
    }
}
