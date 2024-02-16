package models;

import java.util.ArrayList;

public class Registro {
    private ArrayList<Evento> eventos;
    private double totalIngresos;

    public Registro() {
        this.eventos = new ArrayList<>();
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void addEventoAsignado(Chofer chofer, Evento evento) {
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

