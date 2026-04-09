package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaOrdenada<T> {

    private List<T> lista = new ArrayList<>();
    private Comparator<T> comparador;
    private long tiempoInsercion = 0;
    private long tiempoExtraccion = 0;

    public ListaOrdenada(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public void insertar(T evento){
        long inicio = System.nanoTime();
        int i = 0;
        while(i<lista.size() && comparador.compare(lista.get(i),evento) < 0){
            i++;

        }
        lista.add(i,evento);
        long fin =  System.nanoTime();
        long tiempo =  fin - inicio;
        tiempoInsercion = tiempoInsercion + tiempo;
    }

    public T extraer(){
        long inicio = System.nanoTime();
        T evento = lista.remove(0);
        long fin =  System.nanoTime();
        long tiempo =  fin - inicio;
        tiempoExtraccion = tiempoExtraccion + tiempo;
        return evento;
    }

    public long getTiempoInsercion() {
        return tiempoInsercion;
    }

    public long getTiempoExtraccion() {
        return tiempoExtraccion;
    }
}
