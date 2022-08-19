package com.revature.blazinhot.ui;

import com.revature.blazinhot.models.User;
import com.revature.blazinhot.services.UserService;

public class AdminMenu implements IMenu {
    private final User user;
    private final UserService userService;

    public AdminMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to the admin menu " + user.getUsername() + "!");
    }
}
