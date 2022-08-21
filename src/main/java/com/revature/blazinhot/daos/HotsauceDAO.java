package com.revature.blazinhot.daos;

import com.revature.blazinhot.models.Hotsauce;
import com.revature.blazinhot.models.Restaurant;
import com.revature.blazinhot.models.User;
import com.revature.blazinhot.utils.custom_exceptions.InvalidSQLException;
import com.revature.blazinhot.utils.database.ConnectionFactory;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotsauceDAO implements CrudDAO<Hotsauce> {
    @Override
    public void save(Hotsauce obj) {

    }

    @Override
    public void update(Hotsauce obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Hotsauce getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hotsauces WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Hotsauce(rs.getString("id"), rs.getString("name"), rs.getString("spiciness"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return null;
    }

    @Override
    public List<Hotsauce> getAll() {
        List<Hotsauce> hotsauces = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hotsauces");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Hotsauce hot = new Hotsauce(rs.getString("id"), rs.getString("name"), rs.getString("spiciness"), rs.getDouble("price"));
                hotsauces.add(hot);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return hotsauces;
    }

    public List<Hotsauce> getAllBySpiciness(String spiciness) {
        List<Hotsauce> hotsauces = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hotsauces WHERE spiciness = ?");
            ps.setString(1, spiciness);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Hotsauce hot = new Hotsauce(rs.getString("id"), rs.getString("name"), rs.getString("spiciness"), rs.getDouble("price"));
                hotsauces.add(hot);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return hotsauces;
    }
}
