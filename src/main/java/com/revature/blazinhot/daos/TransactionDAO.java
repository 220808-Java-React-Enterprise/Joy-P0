package com.revature.blazinhot.daos;

import com.revature.blazinhot.models.Transaction;
import com.revature.blazinhot.utils.custom_exceptions.InvalidSQLException;
import com.revature.blazinhot.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAO implements CrudDAO<Transaction> {
    @Override
    public void save(Transaction obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO transactions (id, warehouse_id) VALUES (?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getWarehouse_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Transaction obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Transaction getById(String id) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }
}
