package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Должен успешно заходить с правильным кодом из БД")
    void shouldSuccessfulLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Должен блокировать систему после 3 неверных паролей")
    void shouldBlockUserAfterThreeInvalidPasswords() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getWrongAuthInfo();

        loginPage.loginWithInvalidPassword(authInfo);
        loginPage.loginWithInvalidPassword(authInfo);
        loginPage.loginWithInvalidPassword(authInfo);

        var userStatus = SQLHelper.getUserStatus(authInfo.getLogin());
        assertEquals("blocked", userStatus);
    }
}