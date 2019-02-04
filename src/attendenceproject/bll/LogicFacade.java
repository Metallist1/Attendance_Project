/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.bll;

import attendenceproject.be.User;
import java.util.List;
import attendenceproject.bll.exceptions.bllException;

/**
 *
 * @author nedas
 */
public interface LogicFacade {

    public List<User> getAllUsers() throws bllException;

    public User checkLogin(String username, String password) throws bllException;

}
