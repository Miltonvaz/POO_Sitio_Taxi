package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    private LocalDateTime fechaHora;
    private double pago;
    private double gasto;
    private String puntoPartida;
    private String destino;
    private Chofer chofer;

    public double getPago() {
        return pago;
    }
    public Evento(Chofer chofer) {
        this.chofer = chofer;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getPuntoPartida() {
        return puntoPartida;
    }

    public void setPuntoPartida(String puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public Evento() {
        this.fechaHora = LocalDateTime.now();
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String obtenerFechaHoraFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fechaHora.format(formatter);
    }
}
