/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal.db;

import attendenceproject.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import attendenceproject.dal.exceptions.daoException;

/**
 *
 * @author nedas
 */
public class UserDAO {

    SQLServerDataSource ds;

    public UserDAO() {
        this.ds = DatabaseConnectionDAO.getInstance().getConnection();
    }

    /*
    Initialises the constructor. Gets the array from the DatabaseConnectionDAO and sets up the database so the class can use it.
     */
    public List<User> getAllUsers() throws daoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM Movie";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { // Creates and adds movie objects into an array list
             //   User user = new User(rs.getString("name"), rs.getInt("userRating"), rs.getInt("imdbRating"), rs.getDate("lastview"), rs.getString("filelink"), rs.getInt("id"));
               // allUsers.add(user);
            }
            return allUsers; //Returns the full list
        } catch (SQLServerException ex) {
            throw new daoException("Cannot connect to server");
        } catch (SQLException ex) {
            throw new daoException("Cannot execute query");
        }
    }
}
