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

}
