package ru.netology.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public VerificationPage() {
        $("[data-test-id=code] input").shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String code) {
        $("[data-test-id=code] input").setValue(code);
        $("[data-test-id=action-verify]").click();
        return new DashboardPage();
    }
}
