package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.WarehouseDAO;
import com.revature.blazinhot.models.Warehouse;

public class WarehouseService {
    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Warehouse getWarehouseByHotsauceId(String hotsauce_id) {
        return warehouseDAO.getWarehouseByHotsauceId(hotsauce_id);
    }

    public void updateStock(String id, int amount) {
        warehouseDAO.updateStock(id, amount);
    }
}
