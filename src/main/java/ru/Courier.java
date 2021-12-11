package ru;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    public String login;
    public String password;
    public String firstName;

    public Courier(){
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public Courier sameLogin(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        return this;
    }
    public static Courier getCourierWithSameLogin() {
        return new Courier().sameLogin("uniqueLogin", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }
    public static Courier getWithLoginOnly() {
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }
    public static Courier getWithPasswordOnly() { return new Courier().setPassword(RandomStringUtils.randomAlphabetic(10)); }

    public Courier setName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public static Courier getWithNameOnly() {
        return new Courier().setName(RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setLoginPass(String login, String password) {
        this.login = login;
        this.password = password;
        return this;
    }
    public static Courier getWithLoginPassOnly() {
        return new Courier().setLoginPass(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setPassName(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
        return this;
    }
    public static Courier getWithPassNameOnly() {
        return new Courier().setPassName(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setNameLogin(String firstName, String login) {
        this.firstName = firstName;
        this.login = login;
        return this;
    }
    public static Courier getWithNameLoginOnly() {
        return new Courier().setNameLogin(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }
}
