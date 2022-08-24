package com.revature.blazinhot.ui;

import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.User;
import com.revature.blazinhot.models.Warehouse;
import com.revature.blazinhot.services.HotsauceService;
import com.revature.blazinhot.services.UserService;
import com.revature.blazinhot.services.WarehouseService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final HotsauceService hotsauceService;
    private final WarehouseService warehouseService;

    public AdminMenu(User user, UserService userService, HotsauceService hotsauceService, WarehouseService warehouseService) {
        this.user = user;
        this.userService = userService;
        this.hotsauceService = hotsauceService;
        this.warehouseService = warehouseService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exitAdmin:
        {
            while (true) {
                System.out.println("\nWelcome to the admin menu " + user.getUsername() + "!");
                System.out.println("[1] Replenish Products");
                System.out.println("[q] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        replenishProducts();
                        break;
                    case "q":
                        break exitAdmin;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void replenishProducts() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Select which product to replenish!");

        List<Hotsauce> hotsauces = hotsauceService.getAll();

        System.out.println("\nViewing all products\n");
        for (int i = 0; i < hotsauces.size(); i++) {
            Warehouse warehouse = warehouseService.getWarehouseByHotsauceId(hotsauces.get(i).getId());
            System.out.printf("[%d] %s \nIn-stock: %d\n\n", i+1, hotsauces.get(i).getName(), warehouse.getQuantity());
        }

        Hotsauce hotsauce = null;
        Warehouse warehouse = null;
        selectionExit:
        {
            while (true) {
                System.out.print("\nSelect a hotsauce to replenish: ");
                //scan.nextLine();
                String input = scan.nextLine();
                int selection = -1;

                if (input.equals("q")) {
                    return;
                } else if (input.matches("^[1-9]$")) {
                    selection = Integer.parseInt(input) - 1;
                    //System.out.println(selection);
                }

                try {
                    hotsauce = hotsauces.get(selection);
                    warehouse = warehouseService.getWarehouseByHotsauceId(hotsauce.getId());
                    break selectionExit;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid Selection! Type 'q' to cancel");
                }
            }
        }

        int amount = 0;
        amountExit:
        {
            while(true) {
                System.out.print("\nHow many would you like to replenish? (Enter 0 to cancel): ");

                try{
                    amount = Integer.parseInt(scan.nextLine());
                } catch (Exception e){
                    System.out.println("Please enter a number.");
                    break;
                }

                if (amount < 0) {
                    System.out.println("Invalid amount!");
                }
                else if(amount == 0){
                    return;
                }
                else{
                    break amountExit;
                }
            }
        }

        warehouseService.updateStock(warehouse.getId(), warehouse.getQuantity() + amount);
        System.out.println("Product has been replenished!");
    }
}
