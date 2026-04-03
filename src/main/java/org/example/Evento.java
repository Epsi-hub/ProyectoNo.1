package org.example;

public class Evento {
    private String id;
    private String tipo;
    private int prioridad;

    public Evento(String id, String tipo, int prioridad) {
        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public int getPrioridad() { return prioridad; }

    @Override
    public String toString() {
        return id + " - " + tipo + " (prioridad: " + prioridad + ")";
    }
}