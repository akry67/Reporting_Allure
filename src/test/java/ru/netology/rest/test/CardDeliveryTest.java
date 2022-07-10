package ru.netology.rest.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.rest.utils.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.rest.utils.DataGenerator.Registration.generateDate;

public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSubmitRequest() {

        String planningDate = generateDate(6);
        String planningDateNew = generateDate(8);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDate);
        $(By.name("name")).val(DataGenerator.Registration.generateName("ru"));
        $(By.name("phone")).val(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDateNew);
        $(byText("Перепланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDateNew), Duration.ofSeconds(15));
    }

    @Test
    void wrongTest() {
        String planningDate = generateDate(6);
        String planningDateNew = generateDate(8);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDate);
        $(By.name("name")).val(DataGenerator.Registration.generateName("ru"));
        $(By.name("phone")).val(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDateNew);
        $(byText("Перепланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча не запланирована"), Duration.ofSeconds(15));
    }
}

