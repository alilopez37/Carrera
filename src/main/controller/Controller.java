package main.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Translate;
import main.model.Nodo;
import main.model.Vehiculo;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

public class Controller implements Observer {

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

    private Semaphore mutex = new Semaphore(1);

    @FXML
    void btnIniciarOnMouseClicked(MouseEvent event) {
        Vehiculo vehiculo;

        // Hilo para el vehículo 01
        vehiculo = new Vehiculo(1);
        vehiculo.addObserver(this);
        new Thread(vehiculo).start();

        // Hilo para el vehículo 02
        vehiculo = new Vehiculo(2);
        vehiculo.addObserver(this);
        new Thread(vehiculo).start();

        // Hilo para el vehículo 03
        vehiculo = new Vehiculo(3);
        vehiculo.addObserver(this);
        new Thread(vehiculo).start();
    }

    @FXML
    void btnSalirOnMouseClicked(MouseEvent event) {
        System.exit(1);
    }

    @Override
    public  void update(Observable o, Object arg) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Nodo nodo = (Nodo)arg;
        Translate t = new Translate(nodo.desplazamiento,0.0);
        Bounds  bounds;

        switch(nodo.id) {
            case 1:
                Platform.runLater( () -> auto1.getTransforms().add(t) );
                bounds = auto1.getBoundsInParent();

                System.out.println("1:" + auto1.getLayoutX());
                break;
            case 2:
                Platform.runLater( () -> auto2.getTransforms().add(t) );
                bounds = auto2.getBoundsInParent();
                //System.out.println("2:" + bounds);
                break;
            case 3:
                //auto3.getTransforms().add(t);
                Platform.runLater( () -> auto3.getTransforms().add(t) );
                bounds = auto3.getBoundsInParent();
                //System.out.println("3:" + bounds);
                break;
            default:
                bounds = null;
        }
        
        if (bounds.getMaxX() > 555){
            ((Vehiculo)o).setStatus(false);
        }
        mutex.release();
    }

    @FXML
    public void initialize(){
    }
}
