package service;

import config.dbconfig;
import modal.Employee;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService  {

    private final dbconfig dbConfig;


    public AuthService() {
        dbConfig = new dbconfig();
    }

    public String login(String username, String password) {
        String id = "";
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = String.format("SELECT * FROM employee \n" +
                    "WHERE username=\"%s\" AND password=\"%s\";", username, password);

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                id = rs.getString("emp_id");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credential");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

