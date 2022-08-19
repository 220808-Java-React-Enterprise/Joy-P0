package com.revature.blazinhot;

import com.revature.blazinhot.daos.UserDAO;
import com.revature.blazinhot.services.UserService;
import com.revature.blazinhot.ui.LoginMenu;
import com.revature.blazinhot.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* dependency injection */
//        UserDAO userDAO = new UserDAO();
//        UserService userService = new UserService(userDAO);
//        new LoginMenu(userService).start();

        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
