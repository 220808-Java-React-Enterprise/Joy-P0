package com.revature.blazinhot.ui;

import com.revature.blazinhot.daos.RestaurantDAO;
import com.revature.blazinhot.daos.ReviewDAO;
import com.revature.blazinhot.daos.UserDAO;
import com.revature.blazinhot.models.User;
import com.revature.blazinhot.services.RestaurantService;
import com.revature.blazinhot.services.ReviewService;
import com.revature.blazinhot.services.UserService;
import com.revature.blazinhot.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements IMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to BlazinHOT!");
                System.out.println("[1] Login");
                System.out.println("[2] Signup");
                System.out.println("[x] Exit!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        userService.register(user);
                        try {
                            new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO())).start();
                        } catch (NullPointerException e) {}
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                confirmation:
                {
                    while (true) {
                        System.out.println("\ntype 'back' to go back or press enter for login: ");

                        String userInput = scan.nextLine();

                        if (userInput.equals("back")) {
                            break exit;
                        } else if (userInput.equals("")) {
                            break confirmation;
                        }
                    }
                }

                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    User user = userService.login(username, password);
                    if (user.getRole().equals("ADMIN")) new AdminMenu(user, new UserService(new UserDAO())).start();
                    else new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO())).start();
                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private User signup() {
        String email = "";
        String username = "";
        String password = "";
        String password2 = "";
        User user;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreating account...");

        exit:
        {
            while (true) {
                confirmation:
                {
                    while (true) {
                        System.out.println("\ntype 'back' to go back or press enter for signup: ");

                        String userInput = scan.nextLine();

                        if (userInput.equals("back")) {
                            break exit;
                        } else if (userInput.equals("")) {
                            break confirmation;
                        }
                    }
                }
                emailExit:
                {
                    while (true) {
                        System.out.print("\nEnter an email: ");
                        email = scan.nextLine();

                        try {
                            userService.isValidEmail(email);
                            userService.isDuplicateEmail(email);
                            break emailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username: ");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter a password: ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.print("\nRe enter password: ");
                            password2 = scan.nextLine();

                            userService.isSamePassword(password, password2);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                confirmExit: {
                    while (true) {
                        System.out.println("\nIs this correct (y/n):");
                        System.out.println("Email: " + email + "\nUsername: " + username + "\nPassword: " + password);
                        System.out.print("\nEnter: ");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                user = new User(UUID.randomUUID().toString(), username, password, email);
                                return user;
                            case "n":
                                System.out.println("\nRestarting...");
                                break confirmExit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }
            }
        }
        return null;
    }
}
