/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.bll;

import attendenceproject.be.User;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import attendenceproject.bll.util.searchUser;
import attendenceproject.bll.exceptions.bllException;
import attendenceproject.dal.DAOLogicFacade;
import attendenceproject.dal.DAOManager;
import attendenceproject.dal.exceptions.daoException;

/**
 *
 * @author nedas
 */
public class Manager implements LogicFacade {
    private final DAOLogicFacade logiclayer;
    private final searchUser searchforUser;

    /*
    Initialises all classes in DAL
     */
    public Manager() throws IOException {
        logiclayer = new DAOManager();
        searchforUser = new searchUser();
    }

    @Override
    public List<User> getAllUsers() throws bllException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
