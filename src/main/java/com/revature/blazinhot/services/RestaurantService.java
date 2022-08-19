package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.RestaurantDAO;
import com.revature.blazinhot.models.Restaurant;

import java.util.List;

public class RestaurantService {
    private final RestaurantDAO restoDAO;

    public RestaurantService(RestaurantDAO restoDAO) {
        this.restoDAO = restoDAO;
    }

    public List<Restaurant> getAllRestaurants() {
        return restoDAO.getAll();
    }
}
