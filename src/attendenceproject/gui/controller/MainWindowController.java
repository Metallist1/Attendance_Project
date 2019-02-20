/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.controller;

import attendenceproject.be.User;
import attendenceproject.gui.model.UserModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import attendenceproject.gui.exceptions.modelException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class MainWindowController implements Initializable {

    private UserModel userModel;
    private List<User> user;
    @FXML
    private ListView<String> CourseView;
    @FXML
    private ListView<String> StudentView;
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userModel = UserModel.getInstance();
        try {
            //User newUser = userModel.checkLogin("tbeven1", "tYTIoC");       
            //ObservableList<User> newUserList = userModel.getCurrentClassAttendingStudents(1);
            CourseView.getItems().add("Course 1");
                       CourseView.getItems().add("Course 2");
                                  CourseView.getItems().add("Course 3");
                                 
            // StudentView.getItems().addAll(newUserList);
            // ObservableList<User>  newUserList = userModel.getAllStudentFromTeacher (new User("Jeppe", 1 , 1));
            //User editedUser = userModel.editUser(new User("John",  "",  1,  1,  0) ,"Kent" , "Test" , 1516531);
            // userModel.deleteUser(new User("John",  "",  2,  1,  0));
            //User newUser =  userModel.addStudent("Living meme", "" , 15251515 );
            // System.out.println(editedUser);
            // userModel.getAllUsers(); 
            //userModel.changeAttendence(new User("John", "", 2, 1, 0), true);
            System.out.println(userModel.selectIndividualStatistics (new User("John", "", 2, 1, 0)));
            // System.out.println(userModel.getGlobalAttendance(1));
        } catch (modelException ex) {
            setUpAlert(ex.getMessage());
        }
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void changeClass(MouseEvent event) throws modelException {
        StudentView.getItems().clear();
                     ObservableList<User>  newUserList = userModel.getAllStudentFromClass (        CourseView.getSelectionModel().getSelectedIndex()+1);
             for (User us : newUserList) {
                StudentView.getItems().add(us.getName());
            }
    }



}
