package com.jordan;

import com.jordan.watson.Watson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class Controller {

    @FXML private TextArea txtInput;
    @FXML private TextArea txtOutput;

    public void runWatson(ActionEvent event){
        Watson watson = new Watson();

        txtOutput.setText(watson.start(txtInput.getText()));
    }

    public void close(ActionEvent event){
        System.exit(0);
    }
}
