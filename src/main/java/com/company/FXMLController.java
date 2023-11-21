package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController {
    private boolean startFlag = false;
    private boolean realFirst = true;
    private Thread thread1;
    private Thread thread3;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Polygon shape1;
    @FXML
    private Polygon shape2;
    @FXML
    private ImageView image1;
    @FXML
    private Button startBtn;
    @FXML
    private ImageView image2;
    @FXML
    private Circle circle;

    @FXML
    void startBtnOnAction(ActionEvent event) {
        if (!startFlag) {
            startFlag = true;
            if (realFirst) {
                thread1 = new Thread(() -> {
                    while (image1.getY() != 120) {
                        image1.setY(image1.getY() + 1);
                        image2.setY(image2.getY() - 1);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    startFlag = false;
                });

                thread3 = new Thread(() -> {
                    while (startFlag) {
                        int counter = 5;
                        double firstValue = circle.getCenterY();
                        for (int i = 0; i < counter; i++) {
                            circle.setCenterY(circle.getCenterY() + 20);
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        circle.setCenterY(firstValue);
                    }
                });

                thread1.start();
                thread3.start();
                realFirst = false;
            } else {
                thread1.resume();
                thread3.resume();
            }

        }
    }

    @FXML
    void stopBtnOnAction(ActionEvent event) {
        if (startFlag) {
            startFlag = false;
            thread1.suspend();
            thread3.suspend();
        } else {
            showAlert("Поток уже был остановлен!");
        }
    }

    @FXML
    void initialize() {
        image2.setY(120);
    }

    private void showAlert(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");

        alert.setHeaderText(null);
        alert.setContentText(string);

        alert.showAndWait();
    }

}
