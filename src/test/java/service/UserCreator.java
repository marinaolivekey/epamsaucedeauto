package service;

import model.User;

public class UserCreator {

    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withStandardUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.standart.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }

    public static User withLockedOutUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.lockedout.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }

    public static User withProblemUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.problem.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }

    public static User withPerformanceGlitchUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.performanceglitch.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }

    public static User withErrorUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.error.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }

    public static User withVisualUserCredentialsFromProperty() {
        String username = TestDataReader.getTestData("testdata.visual.user.name");
        String password = TestDataReader.getTestData(TESTDATA_USER_PASSWORD);
        return new User(username, password);
    }
}
