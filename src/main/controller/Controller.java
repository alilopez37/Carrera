package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private AnchorPane canvas;

    @FXML
    private ImageView auto1;

    @FXML
    private ImageView auto2;

    @FXML
    private ImageView auto3;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnIniciar;

    @FXML
    void btnIniciarOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnSalirOnMouseClicked(MouseEvent event) {
        System.exit(1);
    }

}
