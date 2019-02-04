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

}
