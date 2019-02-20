/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.controller;

import attendenceproject.be.User;
import attendenceproject.gui.exceptions.modelException;
import attendenceproject.gui.model.UserModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class HubController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private UserModel userModel;
    AnchorPane otherPane = null;
    @FXML
    private AnchorPane innerPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void switchToCurrentAttending(ActionEvent event) {
        displayLoading(1);
    }

    private void displayLoading(int loadWhat) {
        try {
            otherPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/cardLoading.fxml"));
            innerPane.getChildren().add(otherPane);
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }

        Task<ObservableList<User>> loadData = new Task<ObservableList<User>>() {
            @Override
            public ObservableList<User> call() throws modelException {
                if (loadWhat == 1) {
                    return userModel.getCurrentClassAttendingStudents(1);
                }else if (loadWhat == 2){
                    return null ;//userModel.getAllStudentFromTeacher(teacher);
                }else{
                   return null;
                }
            }
        };
        loadData.setOnSucceeded(e -> {
            innerPane.getChildren().remove(otherPane);
        });
        loadData.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent arg0) {
                setUpAlert(loadData.getException().getMessage());
                innerPane.getChildren().remove(otherPane);
            }
        });
        new Thread(loadData).start();
    }

    @FXML
    private void switchToAllStudents(ActionEvent event) {
    }

    @FXML
    private void switchToStatistics(ActionEvent event) {
    }

}
