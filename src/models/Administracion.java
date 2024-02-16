package models;

import java.util.ArrayList;

public class Administracion {
    private String nombre;

    private ArrayList<Chofer> listaChoferes = new ArrayList<>();
    private ArrayList<Taxi> listaTaxis = new ArrayList<>();
    private ArrayList<Taxi> taxisAsignados = new ArrayList<>();

    public Administracion(String nombre) {
        this.nombre = nombre;
    }

    public Administracion() {}

    public boolean addTaxi(Taxi taxi) {
        return listaTaxis.add(taxi);
    }

    public boolean addChofer(Chofer chofer) {
        return listaChoferes.add(chofer);
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Chofer> getChoferes() {
        return listaChoferes;
    }

    public ArrayList<Taxi> getTaxisAsignados() {
        return taxisAsignados;
    }

    public ArrayList<Taxi> getTaxisDisponibles() {
        ArrayList<Taxi> disponibles = new ArrayList<>();
        for (Taxi taxi : listaTaxis) {
            if (!taxisAsignados.contains(taxi) || taxi.isDisponible()) {
                disponibles.add(taxi);
            }
        }
        return disponibles;
    }

    public boolean asignarChoferUnidad(int choferId, String taxiPlaca) {
        Chofer chofer = buscaChofer(choferId);
        Taxi taxi = buscaTaxi(taxiPlaca);

        if (chofer != null && taxi != null && taxi.isDisponible()) {
            taxi.setDisponible(false);
            chofer.setDisponible(false);
            chofer.addTaxiAsignado(taxi);
            taxisAsignados.add(taxi);
            return true;
        }
        return false;
    }

    public Chofer buscaChofer(int id) {
        for (Chofer chofer : listaChoferes) {
            if (chofer.getId() == id) {
                return chofer;
            }
        }
        return null;
    }

    private Taxi buscaTaxi(String placa) {
        for (Taxi taxi : listaTaxis) {
            if (taxi.getPlaca().equals(placa)) {
                return taxi;
            }
        }
        return null;
    }

    public void reiniciarEstado() {
        for (Chofer chofer : listaChoferes) {
            chofer.setDisponible(true);
        }

        for (Taxi taxi : listaTaxis) {
            taxi.setDisponible(true);
        }
    }
}
