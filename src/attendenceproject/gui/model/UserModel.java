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
import java.util.Date;
import java.util.List;

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
            allCurrentUsers.addAll(logiclayer.getAllStudentFromTeacher(teacher));
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return allCurrentUsers;
    }

    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws modelException {
        User editedUser;
        try {
            editedUser = logiclayer.editUser(user, nameToChange, urlToChange, CPRtoChange);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
        return editedUser;
    }

    public void deleteUser(User user) throws modelException {
        try {
            logiclayer.deleteUser(user);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }

    public ObservableList<User> search(ObservableList<User> currentUsers, String userToFind) {
        return logiclayer.search(currentUsers, userToFind);
    }

    public void changeAttendence(User user, boolean isAttending) throws modelException{
        try {
            logiclayer.changeAttendence(user, isAttending);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }

    public List<Date> selectIndividualStatistics(User user) throws modelException{
        try {
           return logiclayer.selectIndividualStatistics(user);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }

    public List<Date> getGlobalAttendance(int classID) throws modelException{
        try {
           return logiclayer.getGlobalAttendance(classID);
        } catch (bllException ex) {
            throw new modelException(ex.getMessage());
        }
    }
}
