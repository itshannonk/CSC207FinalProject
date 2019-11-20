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

package com.example.scotiabankpaymentsystem.login;

import android.app.Activity;

/**
 * This is the Presenter who acts as the middleman between View and Model.
 * It retrieves data from the model and returns it formatted to the view.
 */
public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    void validateCredentials(Activity loginActivity, String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(loginActivity, username, password, this);
    }

    void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onLoginError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSBOSuccess() {
        if (loginView != null) {
            loginView.navigateToSBOHome();
        }
    }

    @Override
    public void onTruckDriverSuccess() {
        if (loginView != null) {
            loginView.navigateToTruckDriverHome();
        }
    }

    @Override
    public void onCocaColaSuccess() {
        if (loginView != null) {
            loginView.navigateToCocaColaHome();
        }
    }

}

