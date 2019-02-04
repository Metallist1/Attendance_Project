/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendenceproject.be.User;
import attendenceproject.bll.Manager;
import attendenceproject.bll.exceptions.bllException;
import attendenceproject.gui.exceptions.modelException;
import attendenceproject.bll.LogicFacade;

/**
 *
 * @author nedas
 */
public class UserModel {

    private static final UserModel UserSingle = new UserModel();
    private final LogicFacade logiclayer;
    private ObservableList<User> allUsers;

    /*
    Initialises the logic layer manager
     */
    private UserModel() {
        try {
            logiclayer = new Manager();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Missing a required resource", e);
        }
    }

    /* Static 'instance' method */
    public static UserModel getInstance() {
        return UserSingle;
    }

    /*
    Gets all categories from database and then returns a string list of all categories
     */
    public ObservableList<User> getAllUsers() throws modelException {
        allUsers = FXCollections.observableArrayList();
        try {
            allUsers.addAll(logiclayer.getAllUsers());
            return allUsers;
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }
}
