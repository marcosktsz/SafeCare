

package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentLogin implements Initializable {

    @FXML
    private TextField UserField;
    @FXML
    private TextField PassField;
    @FXML
    private TextField UserField1;
    @FXML
    private TextField UserField11;
    @FXML
    public void Save(MouseEvent mouseEvent) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(UserField.getText().toString()+ " ");
        sb.append(PassField.getText().toString()+ " ");
        sb.append(UserField1.getText().toString()+" ");
        sb.append(UserField11.getText().toString());
        sb.append("\n");


        File file = new File("assets\\SafeCare.txt");
        FileWriter W = new FileWriter(file, true);
        W.write(sb.toString());
        W.close();

        //Occorre ora un modo per non far sovrascrivere il file e far stampare una riga sotto

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
