/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tugasmodul4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class MainController implements Initializable {
    
    @FXML
    private TableColumn<User, String> colFullname;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableView<User> tblViewUsers;
    
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnUpdate;
    
    @FXML private TextField txtFullname;
    @FXML private TextField txtPassword;
    @FXML private TextField txtUsername;
    
    private void loadDataUsers() {
        
        // Memuat data dari database dari user DAO
        ObservableList<User> userList = UserDAO.getUsers();
        
        tblViewUsers.setItems(userList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Menghubungkan tabel kolom pada table view ke model user dengan property binding
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));  
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));  
        colFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        
        // Memuat data user ke table view
        loadDataUsers();
        
        // Menambahkan event listener untuk table view
        tblViewUsers.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectUser(newValue)
        );
    }    
    
    private User selectedUser; // Untuk menyimpan data user yang dipilih di table view
    
    // Metode untuk membersihkan field input
    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
        txtFullname.clear();
        selectedUser = null; // Reset selected user
    }

    // Metode untuk menampilkan pesan alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Metode untuk memilih user dari TableView
    private void selectUser(User user) {
        if (user != null) {
            selectedUser = user;
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
            txtFullname.setText(user.getFullname());
        }
    }

    // Metode untuk menyimpan user baru (Create)
    @FXML
    private void addUser() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fullname = txtFullname.getText();

        if (username.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            showAlert("Input Error", "Field tidak boleh kosong!");
            return;
        }

        User newUser = new User(username, password, fullname);
        UserDAO.addUser(newUser);
        loadDataUsers(); // Memuat ulang data setelah menambahkan user baru
        clearFields();
    }
    
    // Metode untuk memperbarui user (Update)
    @FXML
    private void updateUser() {
        if (selectedUser == null) {
            showAlert("Selection Error", "Tidak ada user yang dipilih!");
            return;
        }

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fullname = txtFullname.getText();

        if (username.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            showAlert("Input Error", "Field tidak boleh kosong!");
            return;
        }

        selectedUser.setUsername(username);
        selectedUser.setPassword(password);
        selectedUser.setFullname(fullname);

        UserDAO.updateUser(selectedUser);
        loadDataUsers(); // Memuat ulang data setelah memperbarui user
        clearFields();
    }


    // Metode untuk menghapus user (Delete)
    @FXML
    private void deleteUser() {
        if (selectedUser == null) {
            showAlert("Selection Error", "Tidak ada user yang dipilih!");
            return;
        }

        UserDAO.deleteUser(selectedUser.getUsername());
        loadDataUsers(); // Memuat ulang data setelah menghapus user
        clearFields();
    }

}
