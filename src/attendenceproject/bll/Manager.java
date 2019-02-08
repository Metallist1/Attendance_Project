/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.bll;

import attendenceproject.be.User;
import java.io.IOException;
import java.util.List;
import attendenceproject.bll.util.searchUser;
import attendenceproject.bll.exceptions.bllException;
import attendenceproject.dal.DAOLogicFacade;
import attendenceproject.dal.DAOManager;
import attendenceproject.dal.exceptions.daoException;
<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> e876aafb8dd6d38dd372ff831d8c67eb4fea1d3a
import javafx.collections.ObservableList;

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

    @Override
    public User checkLogin(String username, String password) throws bllException {
        try {
            return logiclayer.checkLogin(username, password);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getCurrentClassAttendingStudents(int currentClass) throws bllException {
        try {
            return logiclayer.getCurrentClassAttendingStudents(currentClass);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllStudentFromClass(int selectedClass) throws bllException {
        try {
            return logiclayer.getAllStudentFromClass(selectedClass);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public User addStudent(String name, String url, int CPR) throws bllException {

        try {
            return logiclayer.addStudent(name, url, CPR);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllStudentFromTeaccher(User teacher) throws bllException {
        try {
            return logiclayer.getAllStudentFromTeaccher(teacher);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws bllException {
        try {
            return logiclayer.editUser(user, nameToChange, urlToChange, CPRtoChange);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) throws bllException {
        try {
            logiclayer.deleteUser(user);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public ObservableList<User> search(ObservableList<User> currentUsers, String userToFind) {
        return searchforUser.search(currentUsers, userToFind);
    }

    @Override
    public void changeAttendence(User user, boolean isAttending) throws bllException {
        try {
            logiclayer.changeAttendence(user,isAttending);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }
<<<<<<< HEAD
=======

    @Override
    public List<Date> selectIndividualStatistics(User user) throws bllException {
        try {
           return logiclayer.selectIndividualStatistics(user);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Date> getGlobalAttendance(int classID) throws bllException {
        try {
           return logiclayer.getGlobalAttendance(classID);
        } catch (daoException ex) {
            throw new bllException(ex.getMessage());
        }
    }
>>>>>>> e876aafb8dd6d38dd372ff831d8c67eb4fea1d3a
}
