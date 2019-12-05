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

package com.example.scotiabankpaymentsystem.businessowner.home;

/**
 * This is the HomeView and it calls a presenter method every time there is a user action
 */
interface SBOHomeView {

    /**
     * Navigate to activity see invoices
     */
    void navigateToActivitySeeInvoices();

    /**
     * Navigate to activity see user info
     */
    void navigateToActivitySeeUserInformation();

    /**
     * Navigate to activity log out.
     */
    void navigateToActivityLogOut();

    /**
     * Display name.
     */
    void displayName();

    /**
     * Sets display name.
     *
     * @param username the username
     */
    void setDisplayName(String username);

}