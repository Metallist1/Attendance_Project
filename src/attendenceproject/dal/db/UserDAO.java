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
import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;

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
        User logedInUser = null;
        try (Connection con = ds.getConnection()) {
            String query = ""
                    + "SELECT Teacher.name as tName , Teacher.teacherID as tID , Student.name as sName , Student.photo as sPhoto , Student.studentID as sID , allUser.isTeacher as isTeacher , Is_Learning.studentID as sClass FROM allUser "
                    + "INNER JOIN Teacher "
                    + "ON allUser.personID = Teacher.teacherID "
                    + "INNER JOIN Student "
                    + "ON allUser.personID = Student.studentID "
                    + "INNER JOIN Is_Learning "
                    + "ON Student.studentID = Is_Learning.studentID "
                    + "WHERE allUser.username = ? AND allUser.password = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isTeacher") == 0) {
                    logedInUser = new User(rs.getString("sName"), rs.getString("sPhoto"), rs.getInt("sID"), rs.getInt("sClass"), 0);
                } else {
                    logedInUser = new User(rs.getString("tName"), rs.getInt("tID"), 1);
                }
            }
            return logedInUser;

        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public void markAttendence(User logedInUser) throws daoException {
        java.util.Date utilStartDate = new Date();
        java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
        String sql = "INSERT INTO Attendance(studentID,classID,date) VALUES (?,?,?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, logedInUser.getID());
            ps.setInt(2, logedInUser.getCurrentClass());
            ps.setDate(3, date);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                throw new daoException("You already checked your attendence");
            } else {
                throw new daoException("Cannot execute query");
            }
        }
    }

    public List<User> getCurrentClassAttendingStudents(int currentClass) throws daoException {
        List<User> allCurrentUsers = new ArrayList<>();
        java.util.Date utilStartDate = new Date();
        java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
        try (Connection con = ds.getConnection()) {
            String query = "SELECT * FROM Attendance WHERE date = ? AND classID = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setDate(1, date);
            ps.setInt(2, currentClass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentID");
                allCurrentUsers.add(getSingleStudentInfo(id));
            }
            return allCurrentUsers;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    private User getSingleStudentInfo(int id) throws daoException {
        User logedInUser = null;
        try (Connection con = ds.getConnection()) {
            String query = ""
                    + "SELECT Student.name as sName , Student.photo as sPhoto , Student.studentID as sID , Is_Learning.studentID as sClass FROM Student "
                    + "INNER JOIN Is_Learning "
                    + "ON Student.studentID = Is_Learning.studentID "
                    + "WHERE Student.studentID = ? ";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logedInUser = new User(rs.getString("sName"), rs.getString("sPhoto"), rs.getInt("sID"), rs.getInt("sClass"), 0);
            }
            return logedInUser;

        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public List<User> getAllStudentFromClass(int selectedClass) throws daoException {
        List<User> allCurrentUsers = new ArrayList<>();
        java.util.Date utilStartDate = new Date();
        java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
        try (Connection con = ds.getConnection()) {
            String query = "SELECT * FROM Is_Learning WHERE classID = ? ";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, selectedClass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentID");
                allCurrentUsers.add(getSingleStudentInfo(id));
            }
            return allCurrentUsers;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public User addStudent(String name, String url, int CPR) throws daoException {
        String sql = "INSERT INTO Student(name,photo,CPR,studentID) VALUES (?,?,?,?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, url);
            ps.setFloat(3, CPR);
            ps.setInt(4, getNewestStudentID());
            ps.addBatch();
            ps.executeBatch();
            User usr = new User(name, url, getNewestStudentID(), 0, 0);
            return usr; //Returns the movie object
        } catch (SQLServerException ex) {
            throw new daoException("Cannot connect to server");
        } catch (SQLException ex) {
            throw new daoException("Cannot execute query");
        }
    }

    /*
    Gets the top Movie ID from the database so it is possible to create the movie object
     */
    private int getNewestStudentID() throws daoException {
        int newestID = -1; // Default ID not found
        try (Connection con = ds.getConnection()) {
            String query = "SELECT TOP(1) * FROM Student ORDER by studentID desc"; //Selects the biggest song ID in the database
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                newestID = rs.getInt("id");
            }
            return newestID;
        } catch (SQLServerException ex) {
            throw new daoException("Cannot connect to server");
        } catch (SQLException ex) {
            throw new daoException("Cannot execute query");
        }
    }

    public List<User> getAllStudentFromTeaccher(User teacher) throws daoException {
        List<User> allCurrentUsers = new ArrayList<User>();
        try (Connection con = ds.getConnection()) {
            String query = "SELECT * FROM Is_Teaching WHERE teacherID = ? ";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, teacher.getID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("classID");
                allCurrentUsers = new ArrayList<User>(allCurrentUsers);
                allCurrentUsers.addAll(getAllStudentFromClass(id));
            }
            return allCurrentUsers;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws daoException {
        try (Connection con = ds.getConnection()) {
            String query = "UPDATE Student set name = ?, CPR = ?, photo = ? WHERE studentID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nameToChange);
            preparedStmt.setFloat(2, CPRtoChange);
            preparedStmt.setString(3, urlToChange);
            preparedStmt.setInt(4, user.getID());
            preparedStmt.executeUpdate();
            User us = new User(nameToChange, urlToChange, user.getID(), user.getCurrentClass(), 0); //creates a new song object.
            return us;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public void deleteUser(User user) throws daoException {
        try (Connection con = ds.getConnection()) {
            String query = "DELETE from Attendance WHERE studentID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, user.getID());
            preparedStmt.execute();
            query = "DELETE from Is_Learning WHERE studentID = ?";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, user.getID());
            preparedStmt.execute();
            query = "DELETE from Student WHERE studentID = ?";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, user.getID());
            preparedStmt.execute();
            query = "DELETE from allUser WHERE personID = ? AND isTeacher = 0";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, user.getID());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }
}
