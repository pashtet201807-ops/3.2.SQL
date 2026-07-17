package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "password");
    }

    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo("vasya", "12345");
    }
}