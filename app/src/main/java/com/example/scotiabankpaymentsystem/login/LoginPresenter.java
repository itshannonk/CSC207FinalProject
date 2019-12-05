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
import android.content.Context;

/**
 * This is the Presenter who acts as the middleman between View and Model.
 * It retrieves data from the model and returns it formatted to the view.
 */
public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    /**
     * Instantiates a new Login presenter.
     *
     * @param loginView       the login view
     * @param loginInteractor the login interactor
     */
    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    /**
     * Validate credentials.
     *
     * @param loginActivity the login activity
     * @param username      the username
     * @param password      the password
     * @param context       the context
     */
    void validateCredentials(Activity loginActivity, String username, String password, Context context) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(loginActivity, username, password, this, context);
    }

    /**
     * On destroy.
     */
    void onDestroy() {
        loginView = null;
    }

    @Override
    /**
     * When the username causes an error when it doesn't exist in firebase or when it is empty.
     */
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    /**
     * This when there is a password error if the username exists in firebase or when the password
     * is empty.
     */
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    /**
     * This gives the default error when something is wrong.
     */
    public void onLoginError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSBOSuccess(String userID) {
        if (loginView != null) {
            loginView.navigateToSBOHome(userID);
        }
    }

    @Override
    public void onTruckDriverSuccess(String userID) {
        if (loginView != null) {
            loginView.navigateToTruckDriverHome(userID);
        }
    }
    @Override
    public void onCocaColaSuccess(String userID) {
        if (loginView != null) {
            loginView.navigateToCocaColaHome(userID);
        }
    }

}

