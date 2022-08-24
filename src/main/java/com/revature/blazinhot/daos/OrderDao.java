package com.revature.blazinhot.daos;

import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.Order;
import com.revature.blazinhot.utils.custom_exceptions.InvalidSQLException;
import com.revature.blazinhot.utils.database.ConnectionFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements CrudDAO<Order> {
    @Override
    public void save(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, user_id, hotsauce_id, amount, total, cart_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getHotsauce_id());
            ps.setInt(4, obj.getAmount());
            ps.setDouble(5, obj.getTotal());
            //System.out.println(obj.getCart_id());
            ps.setString(6, obj.getCart_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }

    public Order getPossibleOrderFromCart(String hotsauce_id, String cart_id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE cart_id = ? AND hotsauce_id = ?");
            ps.setString(1, cart_id);
            ps.setString(2, hotsauce_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("hotsauce_id"), rs.getString("cart_id"), rs.getInt("amount"), rs.getDouble("total"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    public List<Order> getAllByCartId(String cart_id){
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE cart_id = ?");
            ps.setString(1, cart_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("hotsauce_id"), rs.getString("cart_id"), rs.getInt("amount"), rs.getDouble("total"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return orders;
    }

    public List<Order> getAllByUserId(String user_id){
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("hotsauce_id"), rs.getString("cart_id"), rs.getInt("amount"), rs.getDouble("total"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return orders;
    }

    @Override
    public void update(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET id = ?, user_id = ?, hotsauce_id = ?, amount = ?, total = ?, cart_id = ?, order_date = ?, transaction_id = ? WHERE id = ?");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getHotsauce_id());
            ps.setInt(4, obj.getAmount());
            ps.setDouble(5, obj.getTotal());
            ps.setString(6, obj.getCart_id());
            ps.setTimestamp(7, Timestamp.valueOf(obj.getTimestamp()));
            ps.setString(8, obj.getTransaction_id());
            ps.setString(9, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }

    @Override
    public void delete(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE user_id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to delete from the database.");
        }
    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    public void addToExistingOrder(String id, int amount, double total) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET amount = ?, total = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setDouble(2, total);
            ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void clearOrdersFromCart(String order_id, String cart_id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET cart_id = '' WHERE id = ? AND cart_id = ?");
            ps.setString(1, order_id);
            ps.setString(2, cart_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void setTimestamp(String id, LocalDateTime now) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET order_date = ? WHERE id = ?");
            ps.setTimestamp(1, Timestamp.valueOf(now));
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Timestamp> getAllOrderDatesByUser(String id) {
        List<Timestamp> dates = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT order_date FROM orders WHERE user_id = ? AND order_date IS NOT NULL ORDER BY order_date DESC");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp date = rs.getTimestamp(1);
                dates.add(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return dates;
    }

    public List<Order> getAllOrderByUserAndDate(String id, Timestamp stamp) {
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ? AND order_date = ?");
            ps.setString(1, id);
            ps.setTimestamp(2, stamp);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("hotsauce_id"), rs.getString("cart_id"), rs.getInt("amount"), rs.getDouble("total"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return orders;
    }
}
