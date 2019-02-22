/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.gui.controller;

import attendenceproject.be.User;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editUser(ActionEvent event) {
    }

    @FXML
    private void deleteUser(ActionEvent event) {
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

    public void setUser(User selectedUser) {
        currentUser = selectedUser;
        nameLabel.setText(currentUser.getName());
        urlLabel.setText(currentUser.getUrl());
        
        
    }
    
}
