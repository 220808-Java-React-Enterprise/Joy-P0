package com.revature.yolp;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.services.UserService;
import com.revature.yolp.ui.LoginMenu;
import com.revature.yolp.utils.database.ConnectionFactory;

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
