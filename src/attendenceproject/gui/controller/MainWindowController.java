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
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class MainWindowController implements Initializable {

    private UserModel userModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        try {
         //User newUser = userModel.checkLogin("lscoffham0", "QfEtRxxb");
         ObservableList<User> newUserList = userModel.getCurrentClassAttendingStudents(1);
            System.out.println(newUserList);
           // userModel.getAllUsers(); //Loads all movies
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
}
