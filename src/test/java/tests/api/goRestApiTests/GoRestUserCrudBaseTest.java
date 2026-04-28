package tests.api.goRestApiTests;

import api.clients.GoRestUserClient;
import core.ApiBaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public abstract class GoRestUserCrudBaseTest extends ApiBaseTest {

    protected GoRestUserClient client;
    private final List<Integer> createdUserIds = new ArrayList<>();

    @BeforeMethod(alwaysRun = true)
    public void initGoRestClient() {
        client = new GoRestUserClient(api);
        createdUserIds.clear();
    }

    protected void trackCreatedUser(int userId) {
        createdUserIds.add(userId);
    }

    @AfterMethod(alwaysRun = true)
    public void deleteTrackedUsers() {
        for (Integer userId : createdUserIds) {
            client.deleteUserById(userId);
        }
        createdUserIds.clear();
    }
}
