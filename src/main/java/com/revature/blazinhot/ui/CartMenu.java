package com.revature.blazinhot.ui;

import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.Order;
import com.revature.blazinhot.models.User;
import com.revature.blazinhot.services.HotsauceService;
import com.revature.blazinhot.services.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;
import java.util.Scanner;

public class CartMenu implements IMenu {
    private final User user;
    private final OrderService orderService;
    private final HotsauceService hotsauceService;

    public CartMenu(User user, OrderService orderService, HotsauceService hotsauceService) {
        this.user = user;
        this.orderService = orderService;
        this.hotsauceService = hotsauceService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to your cart " + user.getUsername() + "!");

                List<Order> orders = orderService.getAllOrdersByCart(user.getCart_id());

                for(Order order : orders){
                    Hotsauce hotsauce = hotsauceService.getHotsauceById(order.getHotsauce_id());
                    System.out.println(order.getAmount() + " '" + hotsauce.getName() + "' - " + order.getTotal());
                }
                System.out.println("\n[y] Confirm order [n] Cancel order [m] Modify Cart [c] Clear Cart [q] Quit");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "y":
                        confirmCart(orders);
                        System.out.println(orders);
                        break exit;
                    case "n":
                        break;
                    case "m":
                        break;
                    case "c":
                        break;
                    case "q":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void confirmCart(List<Order> orders){

    }
}
