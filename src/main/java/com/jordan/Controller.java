package com.jordan;

import com.jordan.watson.Emotion;
import com.jordan.watson.Watson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.util.ArrayList;


public class Controller {

    @FXML private TextArea txtInput;
    @FXML private TextArea txtOutput;
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    @FXML private BarChart<String, Number> barchart = new BarChart<String, Number>(xAxis, yAxis);

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

            StringBuilder ss = new StringBuilder();
            ArrayList<Emotion> emotionsList = watson.getResult(txtInput.getText());
            XYChart.Series series1 = new XYChart.Series();

            if(barchart.getData().size() != 0){
                barchart.getData().remove(0);
            }

            xAxis.setLabel("Emotion");
            yAxis.setLabel("Score");

            for(int i = 0; i < emotionsList.size(); i++){
                ss.append("Tone: " + emotionsList.get(i).emotion + "\n");
                ss.append("Score: " + emotionsList.get(i).score + "\n");
                ss.append("---------------------\n");

                Double score = Double.parseDouble(emotionsList.get(i).score);

                series1.getData().add(new XYChart.Data(emotionsList.get(i).emotion, score));
            }

            txtOutput.setText(ss.toString());
            barchart.getData().add(series1);

        }
    }

    public void close(ActionEvent event){
        System.exit(0);
    }
}
