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

    public List<User> getCurrentClassAttendingStudents(int currentClass) throws bllException;

    public List<User> getAllStudentFromClass(int selectedClass) throws bllException;

    public User addStudent(String name, String url, int CPR) throws bllException;

    public List<User> getAllStudentFromTeaccher(User teacher) throws bllException; 

    public User editUser(User user, String nameToChange, String urlToChange, int CPRtoChange) throws bllException;

    public void deleteUser(User user)throws bllException;

}
