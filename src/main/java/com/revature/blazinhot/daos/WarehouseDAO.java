package com.revature.blazinhot.daos;

import com.revature.blazinhot.models.User;
import com.revature.blazinhot.models.Warehouse;
import com.revature.blazinhot.utils.custom_exceptions.InvalidSQLException;
import com.revature.blazinhot.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WarehouseDAO implements CrudDAO<Warehouse> {

    public Warehouse getWarehouseByHotsauceId(String hotsauce_id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouses WHERE hotsauce_id = ?");
            ps.setString(1, hotsauce_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Warehouse(rs.getString("id"), rs.getString("city"), rs.getString("state"), rs.getString("name"), rs.getString("hotsauce_id"), rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    @Override
    public void save(Warehouse obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO warehouses (id, city, state, name, hotsauce_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getCity());
            ps.setString(3, obj.getState());
            ps.setString(4, obj.getName());
            ps.setString(5, obj.getHotsauce_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } catch (NullPointerException e) {}
    }

    @Override
    public void update(Warehouse obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Warehouse getById(String id) {
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        return null;
    }

    public void updateStock(String id, int amount) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE warehouses SET quantity = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }
}
