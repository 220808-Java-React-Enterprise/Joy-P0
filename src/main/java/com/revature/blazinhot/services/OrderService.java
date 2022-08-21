package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.OrderDao;
import com.revature.blazinhot.models.Order;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao){this.orderDao = orderDao;}

    public void saveOrder(Order order){
        orderDao.save(order);
    }

    public List<Order> getAllOrdersByCart(String cart_id){
        return orderDao.getAllByCartId(cart_id);
    }

    public List<Order> getAllOrdersByUser(String user_id){ return orderDao.getAllByUserId(user_id);}
}
