package com.revature.yolp.ui;

import com.revature.yolp.models.Restaurant;
import com.revature.yolp.models.Review;
import com.revature.yolp.models.User;
import com.revature.yolp.services.RestaurantService;
import com.revature.yolp.services.ReviewService;
import com.revature.yolp.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final RestaurantService restoService;
    private final ReviewService reviewService;

    public MainMenu(User user, UserService userService, RestaurantService restoService, ReviewService reviewService) {
        this.user = user;
        this.userService = userService;
        this.restoService = restoService;
        this.reviewService =reviewService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the main menu " + user.getUsername() + "!");
                System.out.println("[1] View all restaurants");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewRestaurants();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewRestaurants() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nViewing all restaurants...");
                List<Restaurant> restaurants = restoService.getAllRestaurants();

                for (int i = 0; i < restaurants.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + restaurants.get(i).getName());
                }

                System.out.print("\nSelect a restaurant: ");
                int index = scan.nextInt() - 1;

                try {
                    Restaurant selectedResto = restaurants.get(index);

                    List<Review> reviews = reviewService.getAllReviewsByRestaurantId(selectedResto.getId());

                    System.out.println("\nName: " + selectedResto.getName());
                    for (Review r : reviews) {
                        System.out.println("Comment: " + r.getComment());
                        System.out.println("Rating: " + r.getRating());
                        System.out.println("User: " + userService.getUserById(r.getUser_id()).getUsername());
                    }

                    System.out.print("\nComment: ");
                    scan.nextLine();
                    String comment = scan.nextLine();

                    System.out.print("\nRating [1 - 5]: ");
                    int rating = scan.nextInt();

                    Review review = new Review(UUID.randomUUID().toString(), comment, rating, user.getId(), selectedResto.getId());
                    reviewService.saveReview(review);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
            }
        }
    }
}
