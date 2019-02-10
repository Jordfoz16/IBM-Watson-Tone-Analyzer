package com.jordan;

import com.jordan.watson.Watson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class Controller {

    @FXML private TextArea txtInput;
    @FXML private TextArea txtOutput;

    public void runWatson(ActionEvent event){
        API_File file = new API_File();

        if(file.readAPI().length() != 44){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("API Key Needed");
            alert.setHeaderText(null);
            alert.setContentText("API Key Needed:\nEdit 'api_key.json' and enter your api" +
                    " key for IBM Tone Analyzer");

            alert.showAndWait();
        }else{
            Watson watson = new Watson(file.readAPI());

            txtOutput.setText(watson.getResult(txtInput.getText()));
        }
    }

    public void close(ActionEvent event){
        System.exit(0);
    }
}
