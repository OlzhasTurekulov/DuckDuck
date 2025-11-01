package autotests.tests;

import autotests.clients.DuckActionsClient;
import autotests.payloads.DefaultResponseProperties;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Flaky;
import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;

@Epic("Тестирование функций лететь, вывод характеристик, плавать, крякать")
public class DuckActionsTest extends DuckActionsClient {

    //Тест-кейсы для /api/duck/action/fly
    @Test(description = "fly (wingsState - ACTIVE)")
    @Description("Проверка, что уточка полетела. Положение крыльев - ACTIVE")
    @Flaky
    @CitrusTest
    public void successfulFlyActive(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("yellow")
                .height(10.0)
                .material("plastic")
                .sound("quak")
                .wingsState(DuckProperties.WingsState.ACTIVE);
        DefaultResponseProperties defaultResponseProperties = new DefaultResponseProperties().message("I'm flying");

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckFly(runner, "${duckId}");
        validateResponsePayload(runner, defaultResponseProperties);
    }

    @Test(description = "fly (wingsState - FIXED)")
    @Description("Проверка, что уточка полетела. Положение крыльев - FIXED")
    @Flaky
    @CitrusTest
    public void successfulFlyFixed(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("yellow")
                .height(10.0)
                .material("plastic")
                .sound("quak")
                .wingsState(DuckProperties.WingsState.FIXED);
        DefaultResponseProperties defaultResponseProperties = new DefaultResponseProperties().message("I can't fly");

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckFly(runner, "${duckId}");
        validateResponsePayload(runner, defaultResponseProperties);
    }

    //Тест-кейсы для /api/duck/action/properties
    @Test(description = "properties (wingsState - ACTIVE)")
    @Description("Показать характеристики уточки, где положение крыльев - ACTIVE")
    @Flaky
    @CitrusTest
    public void successfulPropertiesActive(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("red")
                .height(11.0)
                .material("rubber")
                .sound("quack")
                .wingsState(DuckProperties.WingsState.ACTIVE);

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckProperties(runner, "${duckId}");
        validateResponsePayload(runner, duckProperties);
    }

    @Test(description = "properties (wingsState - ACTIVE)")
    @Description("Показать характеристики уточки, где положение крыльев - FIXED")
    @Flaky
    @CitrusTest
    public void successfulPropertiesFixed(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("red")
                .height(11.0)
                .material("rubber")
                .sound("quack")
                .wingsState(DuckProperties.WingsState.FIXED);

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckProperties(runner, "${duckId}");
        validateResponsePayload(runner, duckProperties);
    }

    @Test(description = "properties (wingsState - ACTIVE)")
    @Description("Показать характеристики уточки, где положение крыльев - UNDEFINED")
    @Flaky
    @CitrusTest
    public void successfulPropertiesUndefined(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("red")
                .height(11.0)
                .material("rubber")
                .sound("quack")
                .wingsState(DuckProperties.WingsState.UNDEFINED);

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckProperties(runner, "${duckId}");
        validateResponsePayload(runner, duckProperties);
    }

    @Test(description = "properties (material not rubber)")
    @Description("Показать характеристики уточки, где материал не rubber")
    @Flaky
    @CitrusTest
    public void successfulPropertiesNotRubber(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties()
                .color("red")
                .height(11.0)
                .material("plastic")
                .sound("quack")
                .wingsState(DuckProperties.WingsState.ACTIVE);

        duckCreatePayload(runner, duckProperties);
        extractId(runner);
        deleteFinally(runner);
        duckProperties(runner, "${duckId}");
        validateResponsePayload(runner, duckProperties);
    }

    //Тест-кейсы для /api/duck/action/quak
    @Test(description = "quak (0, 2)")
    @Description("Проверка голоса уточки. Кол-во повторов - 0, кол-во кряков в звуке - 2")
    @CitrusTest
    @Ignore
    public void successfulQuakOptionOne(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckQuack(runner, "${duckId}", "0", "2");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"\"\n" +
                        "}");
    }

    @Test(description = "quak (3, 4)")
    @Description("Проверка голоса уточки. Кол-во повторов - 3, кол-во кряков в звуке - 2")
    @Flaky
    @CitrusTest
    @Ignore
    public void successfulQuakOptionTwo(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckQuack(runner, "${duckId}", "3", "2");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"quak-quak, quak-quak, quak-quak\"\n" +
                        "}");
    }

    @Test(description = "quak (2, 3)")
    @Description("Проверка голоса уточки. Кол-во повторов - 2, кол-во кряков в звуке - 3")
    @Flaky
    @CitrusTest
    @Ignore
    public void successfulQuakOptionThree(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckQuack(runner, "${duckId}", "2", "3");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"quak-quak-quak, quak-quak-quak\"\n" +
                        "}");
    }

    @Test(description = "quak (3, 3)")
    @Description("Проверка голоса уточки. Кол-во повторов - 3, кол-во кряков в звуке - 3")
    @CitrusTest
    @Ignore
    public void successfulQuakOptionFour(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckQuack(runner, "${duckId}", "3", "3");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"quak-quak-quak, quak-quak-quak, quak-quak-quak\"\n" +
                        "}");
    }

    @Test(description = "quak (3, 3) DB")
    @Description("Проверка голоса уточки. Кол-во повторов - 3, кол-во кряков в звуке - 3")
    @Ignore
    @CitrusTest
    public void successfulQuakOptionFourDB(@Optional @CitrusResource TestCaseRunner runner) {
        runner.$(query(db)
                .statement("SELECT nvl(max(id), 0)+1 ID FROM DUCK")
                .extract("ID", "duckId"));
        deleteFinally(runner);
        databaseUpdate(runner,
                "INSERT INTO DUCK (id, color, height, material, sound, wings_state)\n" +
                        "VALUES (${duckId}, 'orange', 10.0, 'rubber', 'quak','ACTIVE');");
        duckQuack(runner, "${duckId}", "3", "3");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"quak-quak-quak, quak-quak-quak, quak-quak-quak\"\n" +
                        "}");
    }

    @Test(description = "quak (3, 0)")
    @Description("Проверка голоса уточки. Кол-во повторов - 3, кол-во кряков в звуке - 0")
    @Ignore
    @CitrusTest
    public void successfulQuakOptionFive(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckQuack(runner, "${duckId}", "3", "0");
        validateResponseString(runner,
                "{\n" +
                        "  \"sound\": \"\"\n" +
                        "}");
    }

    //Тест-кейсы для /api/duck/action/swim
    @Test(description = "swim")
    @Description("Проверка, что уточка поплыла")
    @Flaky
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateString(runner, "red", "11", "rubber", "quak", "ACTIVE");
        extractId(runner);
        deleteFinally(runner);
        duckSwim(runner, "${duckId}");
        validateResponseString(runner,
                "{\n" +
                        "  \"message\": \"I'm swimming\"\n" +
                        "}");
    }
}
