/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal;

import attendenceproject.be.User;
import java.io.IOException;
import java.util.List;
import attendenceproject.dal.db.UserDAO;
import attendenceproject.dal.exceptions.daoException;
import java.util.Date;

/**
 *
 * @author nedas
 */
public class DAOManager implements DAOLogicFacade {

    private final UserDAO userDAO;

    /*
    Initialises all classes in DAL
     */
    public DAOManager() throws IOException {
        userDAO = new UserDAO();
    }

    @Override
    public List<User> getAllUsers() throws daoException {
        try {
            return userDAO.getAllUsers();
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public User checkLogin(String username, String password) throws daoException {
        try {
            User logedInUser = userDAO.checkLogin(username, password);
            if (logedInUser != null) {
                if (logedInUser.isTeacher() == 0) {
                    userDAO.markAttendence(logedInUser);
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
            return userDAO.getCurrentClassAttendingStudents(currentClass);
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
    public List<User> getAllStudentFromTeaccher(User teacher) throws daoException {
        try {
            return userDAO.getAllStudentFromTeaccher(teacher);
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
            userDAO.changeAttendence(user,isAttending);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

<<<<<<< HEAD
=======
    @Override
    public List<Date> selectIndividualStatistics(User user) throws daoException {
         try {
           return userDAO.selectIndividualStatistics(user);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

    @Override
    public List<Date> getGlobalAttendance(int classID) throws daoException {
         try {
           return userDAO.getGlobalAttendance(classID);
        } catch (daoException ex) {
            throw new daoException(ex.getMessage());
        }
    }

>>>>>>> e876aafb8dd6d38dd372ff831d8c67eb4fea1d3a
}
