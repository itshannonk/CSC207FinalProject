/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 * We modified the code.
 */
package com.example.scotiabankpaymentsystem.Registration;

import android.app.Activity;

import com.example.scotiabankpaymentsystem.data.model.User;

public class RegisterPresenter implements RegisterInteractor.OnRegisterFinishedListener {
    private RegistrationView registerView;
    private RegisterInteractor registerInteractor;

    RegisterPresenter(RegistrationView registerView, RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }

    void registerUser(Activity registerActivity, String firstName, String lastName, String password, String email, String role, String address) {
        if (registerView != null) {
            registerView.showProgress();
        }
        registerInteractor.register(registerActivity, firstName, lastName, password, email, address, role, this);
    }


    @Override
    public void onFirstNameError() {
        if (registerView != null) {
            registerView.setFirstnameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onLastNameError() {
        if (registerView != null) {
            registerView.setLastnameError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onAccountAlreadyExistsError() {
        if (registerView != null) {
            registerView.setAccountAlreadyExistsError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onPasswordError() {
        if (registerView != null) {
            registerView.setPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onEmailError() {
        if (registerView != null) {
            registerView.setEmailError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onAddressError() {
        if (registerView != null) {
            registerView.setAddressError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onRoleError() {
        if (registerView != null) {
            registerView.setRoleError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.navigateToHome();
        }

    }
}
