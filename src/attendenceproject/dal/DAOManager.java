/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal;

import attendenceproject.be.User;
import attendenceproject.dal.db.AttendanceDAO;
import java.io.IOException;
import java.util.List;
import attendenceproject.dal.db.UserDAO;
import attendenceproject.dal.exceptions.daoException;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author nedas
 */
public class DAOManager implements DAOLogicFacade {

    private final UserDAO userDAO;
    private final AttendanceDAO attendanceDAO;

    /*
    Initialises all classes in DAL
     */
    public DAOManager() throws IOException {
        userDAO = new UserDAO();
        attendanceDAO = new AttendanceDAO();
    }

    @Override
    public User checkLogin(String username, String password) throws daoException {
        try {
            User logedInUser = userDAO.checkLogin(username, password);
            if (logedInUser != null) {
                if (logedInUser.isTeacher() == 0) {
                    attendanceDAO.markAttendence(logedInUser);
                }
                return logedInUser;
            } else {
                throw new daoException("Inncorect login. Check your username and password");
            }
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<User> getCurrentClassAttendingStudents(int currentClass) throws daoException {
        try {
            return attendanceDAO.getCurrentClassAttendingStudents(currentClass);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllStudentFromClass(int selectedClass) throws daoException {
        try {
            return userDAO.getAllStudentFromClass(selectedClass);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public User addStudent(String name, String url, int CPR) throws daoException {
        try {
            return userDAO.addStudent(name, url, CPR);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllStudentFromTeacher(User teacher) throws daoException {
        try {
            return userDAO.getAllStudentFromTeacher(teacher);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws daoException {
        try {
            return userDAO.editUser(user, nameToChange, urlToChange, CPRtoChange);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) throws daoException {
        try {
            userDAO.deleteUser(user);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public void changeAttendence(User user, boolean isAttending) throws daoException {
        try {
            attendanceDAO.changeAttendence(user, isAttending);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<Date> selectIndividualStatistics(User user) throws daoException {
        try {
            return attendanceDAO.selectIndividualStatistics(user);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<Date> getGlobalAttendance(int classID) throws daoException {
        try {
            return attendanceDAO.getGlobalAttendance(classID);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public ObservableList<String> getTeachersClasses(User teacher) throws daoException {
        try {
            return userDAO.getTeachersClasses(teacher);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

}
