package com.revature.blazinhot.ui;

import com.revature.blazinhot.daos.OrderDao;
import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.Order;
import com.revature.blazinhot.models.User;
import com.revature.blazinhot.services.HotsauceService;
import com.revature.blazinhot.services.OrderService;
import com.revature.blazinhot.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final HotsauceService hotService;
    private final OrderService orderService;

    public MainMenu(User user, UserService userService, HotsauceService hotService, OrderService orderService) {
        this.user = user;
        this.userService = userService;
        this.hotService = hotService;
        this.orderService = orderService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the shopping menu " + user.getUsername() + "!");
                System.out.println("[1] View all products");
                System.out.println("[2] View Cart");
                System.out.println("[3] View Order History");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewHotsauces();
                        break;
                    case "2":
                        viewCart();
                        break;
                    case "3":
                        viewOrderHistory();
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

    private void viewOrderHistory(){
        Scanner scan = new Scanner(System.in);
        List<Order> orders = orderService.getAllOrdersByUser(user.getId());

        for(Order order : orders) {
            Hotsauce hotsauce = hotService.getHotsauceById(order.getHotsauce_id());
            System.out.println(order.getAmount() + " '" + hotsauce.getName() + "' - " + order.getTotal());
        }

        orderHistoryBreak:
        {
            while (true) {
                System.out.print("\nEnter 'q' to quit: ");
                String input = scan.nextLine();
                switch (input) {
                    case "q":
                        break orderHistoryBreak;
                    default:
                        System.out.println("Invalid input!");
                }
            }
        }
    }

    private void viewCart(){
        new CartMenu(user, orderService, hotService).start();
    }

    private void viewHotsauces() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nViewing all products...");

        exit: {
            while (true) {
                System.out.print("\nSelect a spiciness level");
                System.out.println("\n[1] Mild\n[2] Medium\n[3] Hot\n[4] Extra Hot\n[5] Extremely Hot");
                System.out.print("\nEnter: ");
                int level = scan.nextInt();

                String spiciness = "";

                switch (level) {
                    case 1:
                        spiciness = "mild";
                        break;
                    case 2:
                        spiciness = "medium";
                        break;
                    case 3:
                        spiciness = "hot";
                        break;
                    case 4:
                        spiciness = "extra hot";
                        break;
                    case 5:
                        spiciness = "extremely hot";
                        break;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }

                List<Hotsauce> hotsauces = hotService.getAllHotsauceBySpiciness(spiciness);

                System.out.println("\nViewing all " + spiciness + " sauces\n");
                for (int i = 0; i < hotsauces.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + hotsauces.get(i).getName() + "- $" + hotsauces.get(i).getPrice());
                }

                Hotsauce hotsauce = null;
                selectionExit:
                {
                    while (true) {
                        System.out.print("\nSelect a hotsauce to purchase: ");
                        scan.nextLine();
                        String input = scan.nextLine();
                        int selection = 0;
                        if(input.equals("quit")){
                            break selectionExit;
                        }
                        else if (input.matches("[1-9]")){
                            selection = Integer.parseInt(input);
                        }
                        try{
                            hotsauce = hotsauces.get(selection);
                            break selectionExit;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid Selection! Type 'quit' to cancel");
                        }
                    }
                }

                int amount = 0;

                amountExit:
                {
                    while(true) {
                        System.out.print("\nHow many would you like? (Enter 0 to cancel): ");

                        amount = scan.nextInt();

                        if (amount < 0) {
                            System.out.println("Invalid amount!");
                        }
                        else if(amount == 0){
                            break exit;
                        }
                        else{
                            break amountExit;
                        }
                    }
                }

                confirmationExit:
                {
                    while(true) {
                        System.out.println("Are you sure you want to add " + amount + " '" + hotsauce.getName() + "' to cart for $" + amount * hotsauce.getPrice() + "? [y/n]");
                        System.out.print("Enter: ");

                        String confirmation = scan.nextLine();
                        switch (confirmation) {
                            case "y":
                                Order order = new Order(UUID.randomUUID().toString(), user.getId(), hotsauce.getId(), user.getCart_id(), amount, amount * hotsauce.getPrice());
                                orderService.saveOrder(order);
                                break confirmationExit;
                            case "n":
                                System.out.print("Order has been cancelled!");
                                break confirmationExit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;

                        }
                    }
                }
                break exit;
            }
        }
    }
}
