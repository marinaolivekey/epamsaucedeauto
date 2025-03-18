package test;

import Page.LoginPage;
import model.User;
import org.testng.annotations.Test;
import service.UserCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends CommonConditions {

    @Test
    public void validLogin() {

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String dashboardTitle = new LoginPage(driver)
                .openPage()
                .validLogin(testUser)
                .getTitleLoggedInUser();

        assertThat(dashboardTitle).isEqualTo("Swag Labs");
    }

    @Test
    public void shouldShowErrorIfUsernameEmpty(){

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String errorMessage = new LoginPage(driver)
                .openPage()
                .loginWithEmptyUsername(testUser)
                .getErrorUsernameEmptyMessage();

        assertThat(errorMessage).isEqualTo("Epic sadface: Username is required");
    }

    @Test
    public void shouldShowErrorIfPasswordEmpty() {

        User testUser = UserCreator.withStandardUserCredentialsFromProperty();
        String errorMessage = new LoginPage(driver)
                .openPage()
                .loginWithEmptyPassword(testUser)
                .getErrorUsernameEmptyMessage();

        assertThat(errorMessage).isEqualTo("Epic sadface: Password is required");
    }
}
