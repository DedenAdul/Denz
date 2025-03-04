/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package tugasmodul3_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author MyBook Z Series
 */
public class Main extends Application {
    
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        primaryStage = stage;
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        
        //memuat file FXML   
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        //Membat scene dengan root dari  fxml
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Masoem University");
        Image icon = new Image(getClass()
                        .getResourceAsStream("/assets/img/icon.png"));
        primaryStage.getIcons().add(icon);
        
        primaryStage.show();
    }
    
    public void changeScene(String fxmlFile) throws Exception {
        
        Parent newRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
        
        //mengambil ukuran root container pada file fxml
        double width = newRoot.prefWidth(-1); // -1 nilai preferensi
        double height = newRoot.prefHeight(-1); // -1 nilai preferensi
        
        primaryStage.getScene().setRoot(newRoot);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
