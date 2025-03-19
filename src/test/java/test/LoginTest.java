package test;

import Page.LoginPage;
import model.User;
import org.testng.annotations.Test;
import service.UserCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends CommonConditions {

    // UC-3
    @Test
    public void validLoginStandardUser() {

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }

    @Test
    public void noLoginWithLockedUser() {

        User testUser = UserCreator.withLockedOutUserCredentialsFromProperty();
        String errorMessage = new LoginPage(driver)
                .openPage()
                .typeAllCredentials(testUser)
                .clickLogin()
                .getErrorLockedMessageIfLockedUser();

        assertThat(errorMessage).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void validLoginProblemUser() {

        User testUser = UserCreator.withProblemUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }

    @Test
    public void validLoginGlitchUser() {

        User testUser = UserCreator.withPerformanceGlitchUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }

    @Test
    public void validLoginErrorUser() {

        User testUser = UserCreator.withErrorUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }

    @Test
    public void validLoginVisualUser() {

        User testUser = UserCreator.withVisualUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }


    // UC-1
    @Test
    public void shouldShowErrorIfStandardUsernameEmpty(){

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String errorMessage = new LoginPage(driver)
                .openPage()
                .typeAndClearAllCredentials(testUser)
                .clickLogin()
                .getErrorEmptyMessage();

        assertThat(errorMessage).isEqualTo("Epic sadface: Username is required");
    }


    // UC-2
    @Test
    public void shouldShowErrorIfPasswordEmpty() {

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String errorMessage = new LoginPage(driver)
                .openPage()
                .typeAllAndClearPassword(testUser)
                .clickLogin()
                .getErrorEmptyMessage();

        assertThat(errorMessage).isEqualTo("Epic sadface: Password is required");
    }
}
