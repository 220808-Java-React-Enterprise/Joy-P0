package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.RestaurantDAO;
import com.revature.blazinhot.models.Restaurant;

import java.util.List;

public class HotsauceService {
    private final HotsauceDAO hotDAO;

    public HotsauceService(HotsauceDAO hotDAO) {
        this.hotDAO = hotDAO;
    }

    public List<Hotsauce> getAllHotsauceBySpiciness() {
        return hotDAO.getAllBySpiciness();
    }
}
