/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.controller;

import attendenceproject.be.User;
import attendenceproject.gui.exceptions.modelException;
import attendenceproject.gui.model.UserModel;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author nedas
 */
public class CurrentUserController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private TextField newName;
    @FXML
    private TextField newCPR;
    @FXML
    private Label urlLabel;

    private User currentUser;
    private UserModel userModel;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
    }

    @FXML
    private void editUser(ActionEvent event) throws modelException, MalformedURLException {
        userModel.editUser(currentUser, newName.getText(), urlLabel.getText(), Integer.parseInt(newCPR.getText()));
        nameLabel.setText(newName.getText());
        if (urlLabel.getText() != "" && urlLabel.getText() != null) {
            imageView.setImage(new Image(new File(urlLabel.getText()).toURI().toURL().toExternalForm()));
        }
    }

    @FXML
    private void deleteUser(ActionEvent event) throws modelException {
        userModel.deleteUser(currentUser);
    }

    @FXML
    private void uploadURL(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop")); //Sets the directory to the desktop
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Picture", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            urlLabel.setText(selectedFile.getAbsolutePath());
        }
    }

    public void setUser(User selectedUser) throws MalformedURLException {
        currentUser = selectedUser;
        nameLabel.setText(currentUser.getName());
        urlLabel.setText(currentUser.getUrl());
        if (currentUser.getUrl() != "" && currentUser.getUrl() != null) {
            imageView.setImage(new Image(new File(currentUser.getUrl()).toURI().toURL().toExternalForm()));
        }
    }

    @FXML
    private void markAttendance(ActionEvent event) {
        try {
            userModel.changeAttendence(currentUser, true);
        } catch (modelException ex) {
            System.out.println(ex);
            try {
                userModel.changeAttendence(currentUser, false);
            } catch (modelException ex1) {
                            System.out.println(ex1);
                Logger.getLogger(CurrentUserController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
