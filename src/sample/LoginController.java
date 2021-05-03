package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LoginController{


    @FXML
    private TextField UserField;
    @FXML
    private TextField PassField;

    @FXML
    private void login(MouseEvent mouseEvent) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("SafeCare.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (br != null) {
            String st;
            while ((st = br.readLine()) != null) {
                String[] splitted = st.split(" ");
                if (UserField.getText().equals(splitted[0]) && PassField.getText().equals(splitted[1])) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You're now logged in");
                    alert.showAndWait();
                    break;
                }
            }
        }
    }

 //   public void login(MouseEvent mouseEvent) {
//        System.out.println(UserField.getText());
   // }


    public void Register(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}