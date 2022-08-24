package com.revature.blazinhot.services;

import com.revature.blazinhot.daos.OrderDao;
import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.Order;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private final OrderDao mockOrderDao = mock(OrderDao.class);
    private OrderService sut;

    @Before
    public void setup() {
        sut = new OrderService(mockOrderDao);
    }

    @Test
    public void test_saveValidOrder() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        Order validOrder = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, 10.00);

        // Act
        spiedSut.saveOrder(validOrder);

        // Assert
        verify(mockOrderDao,times(1)).save(validOrder);
    }

    @Test
    public void test_getOneOrderByUser() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        Order validOrder = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, 10.00);
        when(mockOrderDao.getAllByUserId("user")).thenReturn(Arrays.asList(new Order("id", "user", "hotsauce", "cart", 1, 2.00)));
        // Act


        // Assert
        assert(spiedSut.getAllOrdersByUser("user").size() == 1);
        verify(mockOrderDao,times(1)).getAllByUserId("user");
    }

    @Test
    public void test_getNoOrderByUser() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        Order validOrder = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, 10.00);
        when(mockOrderDao.getAllByUserId("user")).thenReturn(null);
        // Act

        List<Order> orders = spiedSut.getAllOrdersByUser("user");

        // Assert
        Assert.assertNull(orders);
        verify(mockOrderDao,times(1)).getAllByUserId("user");
    }

    @Test
    public void test_getAllOrderByCart() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        Order validOrder = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, 10.00);
        when(mockOrderDao.getAllByCartId("cart")).thenReturn(Arrays.asList(new Order("id", "user", "hotsauce", "cart", 1, 2.00)));
        // Act

        // Assert
        assert(spiedSut.getAllOrdersByCart("cart").size() == 1);
        verify(mockOrderDao,times(1)).getAllByCartId("cart");
    }

    /*@Test
    public void test_AddToExistingOrder() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        String cart_uuid = UUID.randomUUID().toString();
        Order validOrder = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), cart_uuid 1, 10.00);
        Order validOrder2 = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), cart_uuid 3, 10.00);
        when(mockOrderDao.getPossibleOrderFromCart())
        // Act
        spiedSut.saveOrder();
        spiedSut.addToExistingOrder(validOrder);

        // Assert
        verify(mockOrderDao,times(1)).save(validOrder);
    }*/

    /*@Test(expected = SQLException.class)
    public void test_saveInvalidOrder() {
        // Arrange
        OrderService spiedSut = Mockito.spy(sut);
        Order invalidOrder = new Order("", UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, 10.00);
        doThrow(new SQLException()).when(mockOrderDao).save(isA(Order.class));

        spiedSut.saveOrder(invalidOrder);

        //verify(mockOrderDao,times(1)).save(invalidOrder);
    }*/

}