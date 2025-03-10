/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugasmodul4;

/**
 *
 * @author MyBook Z Series
 */
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
    
    public static ObservableList<User> getUsers() {
        //observablelist untuk menyimpan data user
        ObservableList<User> userlist = FXCollections.observableArrayList();
        String query = "SELECT * FROM users";
        
        try (
                //membuat koneksi ke database
                Connection koneksi = DBConnection.getConnection();
                //membuat statment
                Statement stmt = koneksi.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
            //menambahkan data ke observablelist
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                
                userlist.add(new User(
                    username,
                    password,
                    fullname
                    ));
            }
            //menutup koneksi
            rs.close();
            stmt.close();
            koneksi.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userlist;
    }
    
     //Metode untuk menambahkan user
    public static void addUser(User user) {
        String query = "INSERT INTO users (username, password, fullname) VALUES (?, MD5(?), ?)";
    
    try {
        Connection koneksi = DBConnection.getConnection();
        PreparedStatement smt = koneksi.prepareStatement(query);
        
        smt.setString(1, user.getUsername());
        smt.setString(2, user.getPassword());
        smt.setString(3, user.getFullname());
        
        smt.executeUpdate();
        
        smt.close();
        koneksi.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Metode untuk memperbarui user (Update)
    public static void updateUser(User user) {
        String query = "UPDATE users SET password = MD5(?), fullname = ? WHERE username = ?";

        try {
            Connection koneksi = DBConnection.getConnection();
            PreparedStatement smt = koneksi.prepareStatement(query);

            smt.setString(1, user.getPassword());
            smt.setString(2, user.getFullname());
            smt.setString(3, user.getUsername());

            smt.executeUpdate();

            smt.close();
            koneksi.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        // Metode untuk menghapus user (Delete)
    public static void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";

        try {
            Connection koneksi = DBConnection.getConnection();
            PreparedStatement smt = koneksi.prepareStatement(query);

            smt.setString(1, username);

            smt.executeUpdate();

            smt.close();
            koneksi.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}