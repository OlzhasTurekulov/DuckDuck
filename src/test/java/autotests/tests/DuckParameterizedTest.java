package autotests.tests;

import autotests.clients.DuckActionsClient;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckParameterizedTest extends DuckActionsClient {
    DuckProperties duckProperties1 = new DuckProperties()
            .color("yellow")
            .height(10.0)
            .material("plastic")
            .sound("quack")
            .wingsState(DuckProperties.WingsState.ACTIVE);
    DuckProperties duckProperties2 = new DuckProperties()
            .color("red")
            .height(11.0)
            .material("rubber")
            .sound("quack")
            .wingsState(DuckProperties.WingsState.FIXED);
    DuckProperties duckProperties3 = new DuckProperties()
            .color("blue")
            .height(5.0)
            .material("metallic")
            .sound("quack")
            .wingsState(DuckProperties.WingsState.UNDEFINED);
    DuckProperties duckProperties4 = new DuckProperties()
            .color("green")
            .height(10.9)
            .material("plastic")
            .sound("quack")
            .wingsState(DuckProperties.WingsState.ACTIVE);
    DuckProperties duckProperties5 = new DuckProperties()
            .color("orange")
            .height(0.099)
            .material("rubber")
            .sound("quack")
            .wingsState(DuckProperties.WingsState.FIXED);

    @Test(dataProvider = "duckList")
    @CitrusTest
    @CitrusParameters({"runner", "payload", "response"})
    public void successfulDuckCreate(@Optional @CitrusResource TestCaseRunner runner, Object payload, String response) {
        duckCreatePayload(runner, payload);
        validateResponseResource(runner, response);
    }

    @DataProvider(name = "duckList")
    public Object[][] DataProvider() {
        return new Object[][]{
                {null, duckProperties1, "getDuckPropertiesTest/duckYellowProperties.json"},
                {null, duckProperties2, "getDuckPropertiesTest/duckRedProperties.json"},
                {null, duckProperties3, "getDuckPropertiesTest/duckBlueProperties.json"},
                {null, duckProperties4, "getDuckPropertiesTest/duckGreenProperties.json"},
                {null, duckProperties5, "getDuckPropertiesTest/duckOrangeProperties.json"}
        };
    }
}
