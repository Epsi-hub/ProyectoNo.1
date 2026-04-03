package org.example;

import java.util.*;

public class ColaDePrioridad<T> {
    private Queue<T> colaEventos;
    private Comparator<T> comparator;

    public ColaDePrioridad(Comparator<T> comparator) {
        this.comparator = comparator;
        this.colaEventos = new PriorityQueue<>(comparator);
    }

    public void insertar(T elemento) {
        colaEventos.offer(elemento);
    }
    public T maxPrioridad() {
        if (colaEventos.isEmpty()) {
            return null;
        }
        return colaEventos.peek();
    }

    public void modPrioridad(T eventoActual, T eventoNuevo) {
        if (colaEventos.remove(eventoActual)) {
            colaEventos.offer(eventoNuevo);
        }
    }

    public void cambioCriterio(Comparator<T> nuevoComparator) {
        List<T> temp = new ArrayList<>(colaEventos);
        colaEventos = new PriorityQueue<>(nuevoComparator);
        colaEventos.addAll(temp);
    }

    public T extraerMax() {
        if (colaEventos.isEmpty()) {
            return null;
        }
        return colaEventos.poll();  // poll() extrae y elimina el primero
    }

    public int size() {
        return colaEventos.size();
    }


}
