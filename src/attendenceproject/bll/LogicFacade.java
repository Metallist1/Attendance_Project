/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.bll;

import attendenceproject.be.User;
import java.util.List;
import attendenceproject.bll.exceptions.bllException;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author nedas
 */
public interface LogicFacade {

    public User checkLogin(String username, String password) throws bllException;

    public List<User> getCurrentClassAttendingStudents(int currentClass) throws bllException;

    public List<User> getAllStudentFromClass(int selectedClass) throws bllException;

    public User addStudent(String name, String url, int CPR) throws bllException;

    public List<User> getAllStudentFromTeacher(User teacher) throws bllException;

    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws bllException;

    public void deleteUser(User user) throws bllException;

    public ObservableList<User> search(ObservableList<User> currentUsers, String userToFind);

    public void changeAttendence(User user, boolean attending) throws bllException;

    public List<Date> selectIndividualStatistics(User user) throws bllException;

    public List<Date> getGlobalAttendance(int classID) throws bllException;
}
