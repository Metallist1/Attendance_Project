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

}
