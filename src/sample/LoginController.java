package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LoginController {


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
                    alert.setContentText("Login successful");
                    alert.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();

                    break;
                }
                if (!UserField.getText().equals(splitted[0]) && !PassField.getText().equals(splitted[1])) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("Invalid credentials, please try again");
                    alert2.showAndWait();
                    break;
                }
            }
        }

    }

    public void Register(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root1));
            stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void AggPzn(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPatient.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage3 = new Stage();
            stage3.setScene(new Scene(root1));
            stage3.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}