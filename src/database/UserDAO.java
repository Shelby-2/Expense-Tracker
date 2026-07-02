package database;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User login(String username, String password) {

        String sql =
            "SELECT * FROM Users WHERE Username = ? AND Password = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("UserID"),
                    rs.getString("FullName"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Username"),
                    rs.getString("Password")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public boolean register(User user) {

        String sql =
        "INSERT INTO Users " +
        "(FullName, Email, PhoneNumber, Username, Password) " +
        "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());

            return ps.executeUpdate() > 0;

        }
        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}