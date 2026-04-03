package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ManejoDeDatos {
    public List<Interseccion> intersecciones = new ArrayList<>();
    public ArbolBST<Interseccion> arbolBSTPorId;
    public ArbolAVL<Interseccion> arbolAVLPorId;
    public ArbolNario<String> arbolNarioCiudad;
    public ColaDePrioridad<Evento> cola = new ColaDePrioridad<>((a, b) -> b.getPrioridad() - a.getPrioridad());
    public Comparator<Interseccion> ordenPorId = (a,b) -> Integer.compare(a.getId(),b.getId());


    public ManejoDeDatos(){}


    public void cargarDatos(String ruta) {
        try {
            String linea;
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                while((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    int id = Integer.parseInt(datos[0]);
                    String distrito = datos[1];
                    String zona = datos[2];
                    String avenida = datos[3];
                    int nivelRiesgo = Integer.parseInt(datos[4]);
                    int nivelCongestion = Integer.parseInt(datos[5]);
                    int sensores = Integer.parseInt(datos[6]);
                    int activa  = Integer.parseInt(datos[7]);
                    Interseccion interseccion = new Interseccion(id, distrito, zona, avenida, nivelRiesgo, nivelCongestion, sensores, activa);
                    intersecciones.add(interseccion);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

    //BST
    public void cargarArbolBSTporId(){
        arbolBSTPorId = new ArbolBST(ordenPorId);
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorId.insertar(interseccion);
        }
    }

    //Mostrar Recorridos
    private void recorridosBST(ArbolBST<Interseccion> arbolBST){
        System.out.println("Recorrido Inorder ");
        for (Interseccion interseccion: arbolBST.inorder()) {
            System.out.print(interseccion.getId() + " ");
        }
        System.out.println("Recorrido Preorder ");
        for (Interseccion interseccion: arbolBST.preorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println("Recorrido Postorder ");
        for (Interseccion interseccion: arbolBST.postorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println();
    }

    public void recorridosBST(){
        System.out.println("Recorridos ArbolBST indexado por Ids ");
        recorridosBST(arbolBSTPorId);

    }

    //Buscar
    public void buscarInterseccionesPorId(int valor){
        if(arbolBSTPorId.encontrado(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
            System.out.println("Interseccion " + valor + " encontrada");
        }else{
            System.out.println("Interseccion " + valor + " no encontrada");
        }

    }



   //Eliminar

    //AVL
    public void cargarArbolAVLporId(){
        arbolAVLPorId = new ArbolAVL(ordenPorId);
        for (Interseccion interseccion: intersecciones) {
            arbolAVLPorId.insert(interseccion);
        }
    }


    //N - Ario
    public void cargarArbolNario(){
        arbolNarioCiudad = new ArbolNario("Ciudad");
        for(Interseccion interseccion: intersecciones) {
            arbolNarioCiudad.insertar(interseccion.getDistrito(),interseccion.getZona(),interseccion.getAvenida(),interseccion.getNombre());
        }

    }

    //Mostrar Recorrido level order
    public void recorridoArbolNario(){
        List<List<String>>recorridoPorNivel= arbolNarioCiudad.levelOrder();
        System.out.println("Recorrido arbol nario");
        for(List<String> nivel : recorridoPorNivel) {
            for(String elemento : nivel) {
                System.out.printf(elemento + " ");
            }
            System.out.println();
        }
    }

    //Estadisticas de todos los arboles
    public List<String> estadisticasArboles(){
        List<String> estadisticas = new ArrayList<>();

        StringBuilder  estadisticasArbolNario = new StringBuilder();
        estadisticasArbolNario.append(" Estadisticas arbol Nario: Profundidad maxima: " + arbolNarioCiudad.profunidadMaxima() + " Intersecciones por distrito: " + arbolNarioCiudad.HojasPorDistrito());
        estadisticas.add(estadisticasArbolNario.toString());



        return estadisticas;

    }

}
