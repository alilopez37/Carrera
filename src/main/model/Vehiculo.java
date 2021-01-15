package main.model;

import java.util.Observable;
import java.util.Random;

public class Vehiculo extends Observable implements Runnable {
    private Nodo nodo;
    private final Random random;
    private boolean status;

    public Vehiculo(int id){
        nodo = new Nodo();
        nodo.id = id;
        random = new Random(System.currentTimeMillis());
        status = true;
    }
    @Override
    public void run() {
        while (status){
            nodo.desplazamiento =  random.nextInt(6) + 1;
            this.setChanged();
            this.notifyObservers(nodo);
            try {
                Thread.sleep(random.nextInt(100) + 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.nodo.id + ": Cerrado");
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}
