/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tugasmodul3_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author MyBook Z Series
 */
public class DashboardController implements Initializable {
    
    @FXML
    private Button buttonLogot;
    
    @FXML
    private void handleButtonLogoutAction(ActionEvent event) throws Exception{
        
        //keembali ke main Scene / Login
        Main main = new Main();
        main.changeScene("Main.fxml");
    } 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
