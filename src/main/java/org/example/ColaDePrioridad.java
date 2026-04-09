package org.example;

import java.util.*;


public class ColaDePrioridad<T> {

    private List<T> heap;
    private Comparator<T> comparador;

    //Metricas
    private long intercambiosTotales = 0;
    private long tiempoInsercionTotal = 0;
    private long tiempoExtraccionTotal = 0;



    public ColaDePrioridad(Comparator<T> comparador){
        this.heap = new ArrayList<>();
        this.comparador = comparador ;
    }

    //Insercion
    public void insertar(T elemento){ //Inserta al final, como si fuera una hoja simulando un arbol binario en la lista. Luego lo sube en el arbol binario hasta la posicion que le corresponde, que en el caso de eventos es segun prioridad
        long inicio = System.nanoTime();
        heap.add(elemento);
        reordenarArriba(heap.size()-1);
        tiempoInsercionTotal += System.nanoTime() - inicio;

    }

    //Extraccion
    //Siempre se extrae el elemento con mayor o menor prioridad, dependiendo si se esta manejando un min o max heap. Este valor se devuelve y se elimina del heap
    public T extraer(){
        long inicio = System.nanoTime();
        if(heap.isEmpty()) return null;
        T raiz = heap.get(0);
        T ultimo = heap.remove(heap.size()-1);

        if(!heap.isEmpty()){
            heap.set(0, ultimo);
            reordenarAbajo(0);
        }
        tiempoExtraccionTotal += System.nanoTime() - inicio;
        return raiz;
    }

    //Ver elemento con mayor prioridad
    public T peek(){
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    //Reordenamiento hacia arriba
    private void reordenarArriba(int indice){ //Compara el valor a insertado con el padre y mientras tenga mayor prioridad lo sube, de manera que el valor insertado pasa a ser el padre del valor que era el padre
        while(indice > 0){
            int padre = (indice - 1)/2;
            if(comparador.compare(heap.get(indice), heap.get(padre)) < 0){
                T temp = heap.get(indice);
                heap.set(indice, heap.get(padre));
                heap.set(padre, temp);
                indice = padre;
                intercambiosTotales++;
            }else{
                break;
            }
        }
    }

    //Reordenamiento hacia abajo
    private void reordenarAbajo(int indice){
        int size  = heap.size();
        while(true){
            int izquierdo = 2*indice + 1; //valor dentro de la lista que representa el hijo izquierdo del valor actual dentro de la lista
            int derecho = 2*indice + 2; //valor dentro de la lista que representa el hijo derecho del valor actual dentro de la lista
            int prioridadMayor = indice;

            //Busca el hijo con mayor prioridad e intercambia de posicion con el hijo con mas prioridad
            if(izquierdo < size && comparador.compare(heap.get(izquierdo), heap.get(prioridadMayor)) < 0){
                prioridadMayor = izquierdo;
            }
            if(derecho<size && comparador.compare(heap.get(derecho), heap.get(prioridadMayor)) < 0){
                prioridadMayor = derecho;
            }
            if(prioridadMayor != indice){
                T temp = heap.get(indice);
                heap.set(indice, heap.get(prioridadMayor));
                heap.set(prioridadMayor, temp);
                indice = prioridadMayor;
                intercambiosTotales++;
            }else {
                break;
            }
        }

    }

    //Actualizar prioridad de un valor dentro del heap y reposicionarlo en base a ello
    public void actualizarPrioridad(T viejo, T nuevo){
        int indice = heap.indexOf(viejo);

        if(indice != -1){
            heap.set(indice, nuevo);
        }

        reordenarArriba(indice);
        reordenarAbajo(indice);
    }

    //Cambiar criterio
    //Utilizada para cambiar el criterio de orden del heap, de menera que puede pasar de ser max heap a min heap o viseversa, dependiendo de como se configure el comparador, de manera que pasa de estar ordenado de mayor a menor prioridad a ordenado de menor a mayor prioridad.
    public void cambiarComparador(Comparator<T> comparadorNuevo){
        this.comparador = comparadorNuevo;
        //Reconstruccion del heap sin tomar en cuenta las hojas del heap binario. Por eso se divide en 2, para no hacer recorridos inecesarios
        for(int i = heap.size()/2 -1; i >=0; i--){
            reordenarAbajo(i);
        }

    }

    //Esta vacio
    public boolean isEmpty(){ return heap.isEmpty();}

    //Metricas
    public long getIntercambiosTotales() {return intercambiosTotales;}
    public long getTiempoInsercionTotal() {return tiempoInsercionTotal;}
    public long getTiempoExtraccionTotal() {return tiempoExtraccionTotal;}

    //Limpiar
    public void limpiar(){
        heap.clear();
        intercambiosTotales = 0;
        tiempoInsercionTotal = 0;
        tiempoExtraccionTotal = 0;
    }

}
