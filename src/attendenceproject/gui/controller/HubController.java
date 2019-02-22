/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.controller;

import attendenceproject.be.User;
import attendenceproject.gui.exceptions.modelException;
import attendenceproject.gui.model.UserModel;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class HubController implements Initializable {

    private UserModel userModel;
    AnchorPane otherPane = null;
    AnchorPane otherrPane = null;
    @FXML
    private AnchorPane innerPane;
    @FXML
    private TableView<User> currentStudentList;
    @FXML
    private TableColumn<?, ?> pictureColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private JFXListView<Label> currentClassList;

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
    private void switchToCurrentAttendingStudents(ActionEvent event) {
        try {
            otherrPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/currentStudent.fxml"));
            innerPane.getChildren().add(otherrPane);
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }
    }

    @FXML
    private void switchToStatistics(ActionEvent event) {
        try {
            otherrPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/statistics.fxml"));
            innerPane.getChildren().add(otherrPane);
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }
    }

    @FXML
    private void switchToStudents(ActionEvent event) {
        try {
            otherrPane = FXMLLoader.load(getClass().getResource("/attendenceproject/gui/view/allStudents.fxml"));
            innerPane.getChildren().add(otherrPane);
        } catch (IOException ex) {
            setUpAlert(ex.getMessage());
        }
    }

    @FXML
    private void showAllCurrentStudents(MouseEvent event) throws modelException {
        currentStudentList.getItems().clear();
        currentStudentList.getItems().addAll(userModel.getCurrentClassAttendingStudents(currentClassList.getSelectionModel().getSelectedIndex() + 1));
    }

    @FXML
    private void loadClasses(ActionEvent event) {
        currentClassList.getItems().clear();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pictureColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        currentClassList.getItems().add(new Label("Class 1"));
        currentClassList.getItems().add(new Label("Class 2"));
        currentClassList.getItems().add(new Label("Class 3"));
    }

    @FXML
    private void loadIndividualStudent(MouseEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendenceproject/gui/view/currentUser.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<CurrentUserController>getController().setUser(currentStudentList.getSelectionModel().getSelectedItem()); 

        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 800, 800));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void showAllStudents(MouseEvent event) throws modelException {
        currentStudentList.getItems().clear();
        currentStudentList.getItems().addAll(userModel.getAllStudentFromClass(currentClassList.getSelectionModel().getSelectedIndex() + 1));
    }

}
