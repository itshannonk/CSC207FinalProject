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
package com.example.scotiabankpaymentsystem.registration;

/**
 * The interface Registration view.
 */
public interface RegistrationView {
    /**
     * Show progress.
     */
    void showProgress();

    /**
     * Hide progress.
     */
    void hideProgress();

    /**
     * Sets firstname error.
     */
    void setFirstnameError();

    /**
     * Sets role error.
     */
    void setRoleError();

    /**
     * Sets lastname error.
     */
    void setLastnameError();

    /**
     * Sets account already exists error.
     */
    void setAccountAlreadyExistsError();

    /**
     * Sets password error.
     */
    void setPasswordError();

    /**
     * Sets email error.
     */
    void setEmailError();

    /**
     * Sets address error.
     */
    void setAddressError();

    /**
     * Navigate to home.
     *
     * @param userID the user id
     */
    void navigateToHome(String userID);
}
