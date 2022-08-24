package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.HotsauceDAO;
import com.revature.blazinhot.models.Hotsauce;

import java.util.List;

public class HotsauceService {
    private final HotsauceDAO hotDAO;

    public HotsauceService(HotsauceDAO hotDAO) {
        this.hotDAO = hotDAO;
    }

    public List<Hotsauce> getAllHotsauceBySpiciness(String spiciness) {
        return hotDAO.getAllBySpiciness(spiciness);
    }

    public Hotsauce getHotsauceById(String hotsauce_id){
        return hotDAO.getById(hotsauce_id);
    }

    public List<Hotsauce> getAll() {
        return hotDAO.getAll();
    }
}
