package org.example;

import java.util.ArrayList;
import java.util.List;

public class ArbolNario<T> {
    private NaryNode<T> root;
    public ArbolNario(T rootValue) {
        this.root = new NaryNode<>(rootValue);

    }

    public void insertar(T nombreDistrito,T nombreZona, T nombreAvenida, T interseccion ) {
        NaryNode<T> distrito = crear(root,nombreDistrito);
        NaryNode<T> zona = crear(root,nombreZona);
        NaryNode<T> avenida = crear(root,nombreAvenida);
        avenida.children.add(new NaryNode<>(interseccion));
    }

    public NaryNode<T> crear(NaryNode<T> padre, T valor) {
        for(NaryNode<T>hijo: padre.children){
            if(hijo.equals(valor)){
                return hijo;
            }
        }
        NaryNode<T> nuevo = new NaryNode<>(valor);
        padre.children.add(nuevo);
        return nuevo;

    }


    private static class NaryNode<T> {
        public T value;
        public List<NaryNode<T>> children;

        public NaryNode(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }






}
