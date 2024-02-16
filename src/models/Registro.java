package models;

import java.util.ArrayList;

public class Registro {
    private ArrayList<Evento> eventos;
    private double totalIngresos;
    private Chofer chofer;

    public Registro() {
        this.chofer = chofer;
        this.eventos = new ArrayList<>();
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void addEventoAsignado(Evento evento) {
        eventos.add(evento);
        evento.setChofer(chofer);
        if (evento.getPago() > 0) {
            totalIngresos += evento.getPago();
        }
    }

    public double calcularGanancia(double ingresos, double gastos) {
        return ingresos - gastos;
    }
}