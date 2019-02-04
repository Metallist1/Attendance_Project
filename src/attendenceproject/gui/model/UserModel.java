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
    private ObservableList<User> allCurrentUsers;

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
        allCurrentUsers = FXCollections.observableArrayList();
        try {
            allCurrentUsers.addAll(logiclayer.getAllUsers());
            return allCurrentUsers;
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }

    public User checkLogin(String username, String password) throws modelException {
        try {
            return logiclayer.checkLogin(username, password);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }

    public ObservableList<User> getCurrentClassAttendingStudents(int currentClass) throws modelException {
        allCurrentUsers = FXCollections.observableArrayList();
        try {
            allCurrentUsers.addAll(logiclayer.getCurrentClassAttendingStudents(currentClass));
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return allCurrentUsers;
    }

    public ObservableList<User> getAllStudentFromClass(int selectedClass) throws modelException {
        allCurrentUsers = FXCollections.observableArrayList();
        try {
            allCurrentUsers.addAll(logiclayer.getAllStudentFromClass(selectedClass));
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return allCurrentUsers;
    }

    public User addStudent(String name, String url, int CPR) throws modelException {
        User newUser;
        try {
            newUser = logiclayer.addStudent(name, url, CPR);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return newUser;
    }

    public ObservableList<User> getAllStudentFromTeaccher(User teacher) throws modelException {
        allCurrentUsers = FXCollections.observableArrayList();
        try {
            allCurrentUsers.addAll(logiclayer.getAllStudentFromTeaccher(teacher));
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return allCurrentUsers;
    }
}
