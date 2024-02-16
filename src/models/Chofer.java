package models;

import java.util.ArrayList;

public class Chofer {
    private String nombre;
    private String apellido;
    private int id;
    private boolean disponible = true;
    private ArrayList<Taxi> taxisAsignados;

    public Chofer() {
        this.taxisAsignados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<Taxi> getTaxisAsignados() {
        return taxisAsignados;
    }

    public void addTaxiAsignado(Taxi taxi) {
        taxisAsignados.add(taxi);
    }
}
