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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class MainWindowController implements Initializable {

    private UserModel userModel;
    AnchorPane otherPane = null;

    @FXML
    private AnchorPane innerPane;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField usernameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void signUpWithCard(ActionEvent event) {
        try {
            otherPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/cardLoading.fxml"));
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }

        AnchorPane.setTopAnchor(otherPane, 47d);
        innerPane.getChildren().add(otherPane);

        Task<User> loadData = new Task<User>() {
            @Override
            public User call() throws modelException {
                return userModel.checkLogin("tbeven1", "tYTIoC");
            }
        };
        loadData.setOnSucceeded(e -> {
            innerPane.getChildren().remove(otherPane);
            setUpAlert("You successfully marked your attendance for today");
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
    private void manualLogin(ActionEvent event) {
        try {
            otherPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/manualLogin.fxml"));
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }
        AnchorPane.setTopAnchor(otherPane, 47.2);
        innerPane.getChildren().add(otherPane);
    }

    @FXML
    private void submitLogin(ActionEvent event) {

        try {
            otherPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/cardLoading.fxml"));
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }

        AnchorPane.setTopAnchor(otherPane, 47d);
        innerPane.getChildren().add(otherPane);

        Task<User> loadData = new Task<User>() {
            @Override
            public User call() throws modelException {
                return userModel.checkLogin(usernameField.getText(), passwordField.getText());
            }
        };
        loadData.setOnSucceeded(e -> {

            if (loadData.getValue().isTeacher() == 1) {
                Parent root1 = null;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendenceproject/gui/view/mainHub.fxml"));
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root1, 800, 800));
                stage.centerOnScreen();
                stage.show();
                Stage stageOther = (Stage) innerPane.getScene().getWindow();
                stageOther.close();
            } else {
                innerPane.getChildren().remove(otherPane);
                setUpAlert("You successfully marked your attendance for today");
            }
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
    private void closeLogin(ActionEvent event) throws IOException {
        try {
            otherPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/MainWindowView.fxml"));
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }
        innerPane.getChildren().removeAll();
        AnchorPane.setTopAnchor(otherPane, 47d);
        innerPane.getChildren().add(otherPane);
       
    }

}
