package com.example.scotiabankpaymentsystem.driver.home;

/**
 * The interface Driver home view.
 */
public interface DriverHomeView {


    /**
     * Navigate to activity see delivery.
     */
    void navigateToActivitySeeDelivery();

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
