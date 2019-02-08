/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal.db;

import attendenceproject.be.User;
import attendenceproject.dal.exceptions.daoException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nedas
 */
public class AttendanceDAO {

    SQLServerDataSource ds;
    UserDAO userDAO;

    public AttendanceDAO() {
        this.ds = DatabaseConnectionDAO.getInstance().getConnection();
        userDAO = new UserDAO();
    }

    public void changeAttendence(User user, boolean isAttending) throws daoException {
        try (Connection con = ds.getConnection()) {
            if (isAttending) {
                String sql = "INSERT INTO Attendance(studentID,classID) VALUES (?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, user.getID());
                ps.setInt(2, user.getCurrentClass());
                ps.executeUpdate();
            } else {
                java.sql.Date date = currentDate();
                String query = "DELETE from Attendance WHERE studentID = ? AND classID = ? AND date = ? ";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, user.getID());
                preparedStmt.setInt(2, user.getCurrentClass());
                preparedStmt.setDate(3, date);
                preparedStmt.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            if (ex.getSQLState().startsWith("23")) {
                throw new daoException("Attendance already marked");
            } else {
                throw new daoException("Cannot execute query");
            }
        }
    }

    public List<Date> selectIndividualStatistics(User user) throws daoException {
        List<Date> allAttendedDates = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String query = "SELECT date FROM Attendance WHERE studentID = ? AND classID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getID());
            ps.setInt(2, user.getCurrentClass());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allAttendedDates.add(rs.getDate("date"));
            }
            return allAttendedDates;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public List<Date> getGlobalAttendance(int classID) throws daoException {
        List<Date> allAttendedDates = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String query = "SELECT date FROM Attendance WHERE classID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allAttendedDates.add(rs.getDate("date"));
            }
            return allAttendedDates;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            throw new daoException("Cannot execute query");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new daoException("Cannot connect to server");
        }
    }

    public void markAttendence(User logedInUser) throws daoException {
        String sql = "INSERT INTO Attendance(studentID,classID) VALUES (?,?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, logedInUser.getID());
            ps.setInt(2, logedInUser.getCurrentClass());
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex);
            if (ex.getSQLState().startsWith("23")) {
                throw new daoException("You already checked your attendence");
            } else {
                throw new daoException("Cannot execute query");
            }
        }
    }

    public List<User> getCurrentClassAttendingStudents(int currentClass) throws daoException {
        List<User> allCurrentUsers = new ArrayList<>();
        java.sql.Date date = currentDate();
        try (Connection con = ds.getConnection()) {
            String query = "SELECT * FROM Attendance WHERE date = ? AND classID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, date);
            ps.setInt(2, currentClass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allCurrentUsers.add(userDAO.getSingleStudentInfo(rs.getInt("studentID")));
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

    private java.sql.Date currentDate() {
        java.util.Date utilStartDate = new Date();
        java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
        return date;
    }
}
