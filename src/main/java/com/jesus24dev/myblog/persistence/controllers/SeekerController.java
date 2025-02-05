
package com.jesus24dev.myblog.persistence.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SeekerController {

    private String url = "jdbc:mysql://localhost:3306/DATABASENAME?serverTimezone=UTC";
    private Properties props;

    public SeekerController() {
        props = new Properties();
        props.setProperty("user", "YOUR USER");
        props.setProperty("password", "YOUR PASSWORD");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, props);
    }

    public List<Long> getPostId(String text) {
        List<Long> postId = new ArrayList<>();
        String query = "SELECT ID FROM post WHERE description LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, "%" + text + "%"); 

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    postId.add(rs.getLong("ID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  
            return new ArrayList<>();  
        }

        return postId;
    }

    public List<Long> getProfileId(String text) {
        List<Long> idList = new ArrayList<>();
        String query = "SELECT PROFILE_ID FROM user WHERE name LIKE ? OR lastname LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, "%" + text + "%");  
            pstm.setString(2, "%" + text + "%");  

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    idList.add(rs.getLong("PROFILE_ID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  
            return new ArrayList<>();  
        }

        return idList;
    }
}
