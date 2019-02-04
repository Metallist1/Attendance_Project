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
            while (rs.next()) {
            }
            return allUsers; //Returns the full list
        } catch (SQLServerException ex) {
            throw new daoException("Cannot connect to server");
        } catch (SQLException ex) {
            throw new daoException("Cannot execute query");
        }
    }

    public User checkLogin(String username, String password) throws daoException {
        User logedInUser = null ;
        try (Connection con = ds.getConnection()) {
            String query = ""
                    + "SELECT Teacher.name as tName , Teacher.id as tID , Student.name as sName , Student.photo as sPhoto , Student.id as sID , Student.CPR as sClass , allUser.isTeacher as isTeacher FROM allUser "
                    + "INNER JOIN Teacher "
                    + "ON allUser.personID = Teacher.teacherID "
                    + "INNER JOIN Student "
                    + "ON allUser.personID = Student.studentID "
                    + "WHERE allUser.username = ? AND allUser.password = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new daoException("Cannot incorrect username or password");
            } else {
                System.out.println(rs.getString("tName"));
                while (rs.next()) {
                    if (rs.getInt("isTeacher") == 0) {
                        logedInUser=  new User(rs.getString("sName"), rs.getString("sPhoto"), rs.getInt("sID"), rs.getInt("sClass"), 0);
                    } else {
                        logedInUser= new User(rs.getString("tName"), rs.getInt("tID"), 1);
                    }
                }
                return logedInUser;
            }
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public void markAttendence(User logedInUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
