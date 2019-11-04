package com.example.scotiabankpaymentsystem.Registration;

public interface RegistrationView {
    void showProgress();

    void hideProgress();

    void setFirstnameError();

    void setRoleError();

    void setLastnameError();

    void setAccountAlreadyExistsError();

    void setPasswordError();

    void setEmailError();

    void setAddressError();

    void navigateToHome();
}
