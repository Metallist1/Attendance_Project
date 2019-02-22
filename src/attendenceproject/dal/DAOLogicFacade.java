/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal;

import attendenceproject.be.User;
import java.util.List;
import attendenceproject.dal.exceptions.daoException;
import java.util.Date;

/**
 *
 * @author nedas
 */
public interface DAOLogicFacade {

    public List<User> getAllUsers() throws daoException;

    public User checkLogin(String username, String password) throws daoException;

    public List<User> getCurrentClassAttendingStudents(int currentClass) throws daoException;

    public List<User> getAllStudentFromClass(int selectedClass) throws daoException;

    public User addStudent(String name, String url, int CPR) throws daoException;

    public List<User> getAllStudentFromTeaccher(User teacher) throws daoException;

    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws daoException;

    public void deleteUser(User user) throws daoException;

    public void changeAttendence(User user, boolean isAttending) throws daoException;

    public List<Date> selectIndividualStatistics(User user) throws daoException;

    public List<Date> getGlobalAttendance(int classID) throws daoException;

}
