package com.revature.blazinhot.ui;

import com.revature.blazinhot.models.*;
import com.revature.blazinhot.services.HotsauceService;
import com.revature.blazinhot.services.OrderService;
import com.revature.blazinhot.services.TransactionService;
import com.revature.blazinhot.services.WarehouseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CartMenu implements IMenu {
    private final User user;
    private final OrderService orderService;
    private final HotsauceService hotsauceService;
    private final WarehouseService warehouseService;
    private final TransactionService transactionService;

    public CartMenu(User user, OrderService orderService, HotsauceService hotsauceService, WarehouseService warehouseService, TransactionService transactionService) {
        this.user = user;
        this.orderService = orderService;
        this.hotsauceService = hotsauceService;
        this.warehouseService = warehouseService;
        this.transactionService = transactionService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to your cart " + user.getUsername() + "!");

                List<Order> orders = orderService.getAllOrdersByCart(user.getCart_id());
                double total = 0;
                for(Order order : orders){
                    Hotsauce hotsauce = hotsauceService.getHotsauceById(order.getHotsauce_id());
                    total += order.getTotal();
                    System.out.print("\n" + order.getAmount() + " '" + hotsauce.getName() + "' - ");
                    System.out.printf("$%.2f\n", order.getTotal());
                }
                if(orders.size() == 0){
                    System.out.println("Your cart is currently empty!");
                    System.out.println("\n[q] Back To Main Menu");
                    System.out.print("\nEnter: ");
                }
                else {
                    System.out.printf("\nTotal: $%.2f\n", total);
                    System.out.println("\nWould you like to proceed with purchasing these items?");
                    System.out.println("\n[y] Proceed \n[n] Clear Cart \n[q] Back To Main Menu");
                    System.out.print("\nEnter: ");
                }
                switch (scan.nextLine()) {
                    case "y":
                        confirmCart(orders);
                        break exit;
                    case "n":
                        clearCart(orders);
                        break exit;
                    case "q":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void clearCart(List<Order> orders) {
        for(Order o : orders){
            orderService.deleteOrder(o);
        }
    }

    private void confirmCart(List<Order> orders){
        System.out.println("Thank you for your order!\nYou have purchased the following\n");
        LocalDateTime now = LocalDateTime.now();
        double total = 0;
        for(Order o : orders) {
            total += o.getTotal();
            Hotsauce hotsauce = hotsauceService.getHotsauceById(o.getHotsauce_id());
            System.out.printf("%d '%s' for $%.2f\n\n", o.getAmount(), hotsauce.getName(), o.getTotal());
            o.setTimestamp(now);
            o.setCart_id("");
            Warehouse warehouse = warehouseService.getWarehouseByHotsauceId(o.getHotsauce_id());
            warehouseService.updateStock(warehouse.getId(), warehouse.getQuantity() - o.getAmount());
            String uuid = UUID.randomUUID().toString();
            o.setTransaction_id(uuid);
            orderService.updateOrder(o);
            Transaction transaction = new Transaction(uuid, warehouse.getId());
            transactionService.saveTransaction(transaction);
        }

        System.out.printf("Total price $%.2f\n", total);

    }
}
