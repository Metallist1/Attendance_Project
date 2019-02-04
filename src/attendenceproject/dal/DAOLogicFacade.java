/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal;

import attendenceproject.be.User;
import java.util.List;
import attendenceproject.dal.exceptions.daoException;

/**
 *
 * @author nedas
 */
public interface DAOLogicFacade {

    public List<User> getAllUsers() throws daoException;

}
