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

/**
 * This is the View and it calls a presenter method every time there is a user action
 */
public interface LoginView {
    /**
     * Show progress.
     */
    void showProgress();

    /**
     * Hide progress.
     */
    void hideProgress();

    /**
     * Sets username error.
     */
    void setUsernameError();

    /**
     * Sets password error.
     */
    void setPasswordError();

    /**
     * Navigate to sbo home.
     *
     * @param userID the user id
     */
    void navigateToSBOHome(String userID);

    /**
     * Navigate to truck driver home.
     *
     * @param userID the user id
     */
    void navigateToTruckDriverHome(String userID);

    /**
     * Navigate to coca cola home.
     *
     * @param userID the user id
     */
    void navigateToCocaColaHome(String userID);
}
