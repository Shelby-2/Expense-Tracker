package database;

import model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class IncomeDAO {

    public boolean addIncome(Income income) {

        String sql =
                "INSERT INTO Income " +
                "(UserID, Amount, Source, IncomeDate) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, income.getUserID());
            ps.setDouble(2, income.getAmount());
            ps.setString(3, income.getSource());
            ps.setDate(4, java.sql.Date.valueOf(income.getIncomeDate()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}