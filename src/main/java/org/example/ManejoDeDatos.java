package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ManejoDeDatos {
    public List<Interseccion> intersecciones = new ArrayList<>();
    public List<String> reporteFinal =  new ArrayList<>();

    //BST
    public ArbolBST<Interseccion> arbolBSTPorId;
    public ArbolBST<Interseccion> arbolBSTPorCongestion;
    public ArbolBST<Interseccion> arbolBSTPorRiesgo;
    public ArbolBST<Interseccion> arbolBSTPorTiempoReporte;


    //AVL
    public ArbolAVL<Interseccion> arbolAVLPorId;
    public ArbolAVL<Interseccion> arbolAVLPorCongestion;
    public ArbolAVL<Interseccion> arbolAVLPorRiesgo;
    public ArbolAVL<Interseccion> arbolAVLPorTiempoReporte;


    //Nario
    public ArbolNario<String> arbolNarioCiudad;

    //Cola de prioridad
    private Comparator<Evento> comparador = (a,b) ->Integer.compare(b.getPrioridad(), a.getPrioridad());
    public ColaDePrioridad<Evento> cola = new ColaDePrioridad<>(comparador);
    public boolean usoBST = true;
    public boolean usoAVL = true;

    //Lista ordenada
    public ListaOrdenada<Evento> listaOrdenada = new ListaOrdenada<>(comparador);

    //Criterios de insercion
    public Comparator<Interseccion> ordenPorId = (a,b) -> Integer.compare(a.getId(),b.getId());

    public Comparator<Interseccion> ordenPorCongestion = (a,b) -> {
        int orden = Integer.compare(a.getNivelCongestion(),b.getNivelCongestion());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };

    public Comparator<Interseccion> ordenPorRiesgo = (a,b) -> {
        int orden = Integer.compare(a.getNivelRiesgo(),b.getNivelRiesgo());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };

    public Comparator<Interseccion> ordenPorTiempoReporte = (a,b) -> {
        int orden = Long.compare(a.getActualizacionReporte(),b.getActualizacionReporte());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };



    public ManejoDeDatos(boolean usoBST, boolean usoAVL){
        this.usoBST = usoBST;
        this.usoAVL = usoAVL;
    }


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
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorId.insertar(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/intersecciones.size();
        reporteFinal.add("Comparaciones de incersion en BST por Id: " +  arbolBSTPorId.contadorComparacionesInsert());
        reporteFinal.add("Tiempo promedio de incersion: " + tiempoProm);
        reporteFinal.add("");
    }

    public void cargarArbolBSTporCongestion(){
        arbolBSTPorCongestion= new ArbolBST(ordenPorCongestion);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorCongestion.insertar(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/intersecciones.size();
        reporteFinal.add("Comparaciones de incersion en BST por nivel de congestion: " +  arbolBSTPorCongestion.contadorComparacionesInsert());
        reporteFinal.add("Tiempo promedio de incersion: " + tiempoProm);
        reporteFinal.add("");
    }

    public void cargarArbolBSTporRiesgo(){
        arbolBSTPorRiesgo= new ArbolBST(ordenPorRiesgo);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorRiesgo.insertar(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/intersecciones.size();
        reporteFinal.add("Comparaciones de incersion en BST por Nivel de riesgo: " +  arbolBSTPorRiesgo.contadorComparacionesInsert());
        reporteFinal.add("Tiempo promedio de incersion: " + tiempoProm);
        reporteFinal.add("");
    }

    public void cargarArbolBSTporTiempoReporte(){
        arbolBSTPorTiempoReporte= new ArbolBST(ordenPorTiempoReporte);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorTiempoReporte.insertar(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/intersecciones.size();
        reporteFinal.add("Comparaciones de incersion en BST por Tiempo de Reporte: " +  arbolBSTPorTiempoReporte.contadorComparacionesInsert());
        reporteFinal.add("Tiempo promedio de insercion: " + tiempoProm);
        reporteFinal.add("");
    }



    //Mostrar Recorridos
    private void recorridosBST(ArbolBST<Interseccion> arbolBST){

        reporteFinal.add("Recorrido Inorder");
        StringBuilder inorder = new StringBuilder();
        for (Interseccion interseccion: arbolBST.inorder()) {
            inorder.append(interseccion.getId() + ",");
        }
        reporteFinal.add(inorder.toString());

        reporteFinal.add("Recorrido Preorder ");
        StringBuilder preorder = new StringBuilder();
        for (Interseccion interseccion: arbolBST.preorder()) {
            preorder.append(interseccion.getId() +  ",");
        }
        reporteFinal.add(preorder.toString());

        reporteFinal.add("Recorrido Postorder ");
        StringBuilder postorder = new StringBuilder();
        for (Interseccion interseccion: arbolBST.postorder()) {
            postorder.append(interseccion.getId() +  ",");
        }
        reporteFinal.add(postorder.toString());

        reporteFinal.add("Recorrido LevelOrder ");
        StringBuilder levelorder = new StringBuilder();
        for (List<Interseccion>nivel: arbolBST.levelOrder()) {
            for (Interseccion interseccion: nivel) {
                levelorder.append(interseccion.getId() +  ",");
            }
        }
        reporteFinal.add(levelorder.toString());
        reporteFinal.add("");


    }

    public void recorridosBST(){
        reporteFinal.add("Recorridos ArbolBST indexado por Ids ");
        recorridosBST(arbolBSTPorId);

        reporteFinal.add("Recorridos ArbolBST indexado por Nivel de congestion ");
        recorridosBST(arbolBSTPorCongestion);

        reporteFinal.add("Recorridos ArbolBST indexado por Nivel de Riesgo");
        recorridosBST(arbolBSTPorRiesgo);

        reporteFinal.add("Recorridos ArbolBST indexado por Tiempo de reporte");
        recorridosBST(arbolBSTPorTiempoReporte);
        reporteFinal.add("");

    }

    //Buscar
    public void buscarInterseccionesPorIdBST(int valor){
        long inicio = System.nanoTime();
        Interseccion interseccion=  arbolBSTPorId.encontrado(new Interseccion(valor, "", "", "", 0, 0, 0,0));
        long fin = System.nanoTime();
        long tiempo =  fin - inicio;
        reporteFinal.add("Busqueda de interseccion " + valor);
        reporteFinal.add("Tiempo de busqueda en arbol BST por ID en nanosegundos: " + tiempo);
        reporteFinal.add("Comparaciones: " + arbolBSTPorId.contadorComparacionesSearch());
        reporteFinal.add("");

        arbolBSTPorId.reset();

    }


   //Eliminar
   public void eliminarInterseccionesPorIdBST(int valor){
       if(arbolBSTPorId.eliminado(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
           System.out.println("Intersecion " + valor + " eliminada");
       }else{
           System.out.println("Intersecion " + valor + " no eliminada");
       }

   }


    //AVL
    public void cargarArbolAVLporId(){
        arbolAVLPorId = new ArbolAVL(ordenPorId);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolAVLPorId.insert(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/arbolAVLPorId.size();
        reporteFinal.add("Comparaciones de insercion en AVL por Id: " +  arbolAVLPorId.getComparacionesInsercion());
        reporteFinal.add("Tiempo promedio de insercion: " + tiempoProm);

    }

    public void cargarArbolAVLporCongestion(){
        arbolAVLPorCongestion = new ArbolAVL(ordenPorCongestion);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolAVLPorCongestion.insert(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/arbolAVLPorCongestion.size();
        reporteFinal.add("Comparaciones de insercion en AVL por nivel de congestion: " +  arbolAVLPorCongestion.getComparacionesInsercion());
        reporteFinal.add("Tiempo promedio de de insercion: " + tiempoProm);
    }

    public void cargarArbolAVLporRiesgo(){
        arbolAVLPorRiesgo = new ArbolAVL(ordenPorRiesgo);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolAVLPorRiesgo.insert(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/arbolAVLPorRiesgo.size();
        reporteFinal.add("Comparaciones de incercion en AVL por nivel de riesgo: " +  arbolAVLPorRiesgo.getComparacionesInsercion());
        reporteFinal.add("Tiempo promedio de insercion: " + tiempoProm);
    }

    public void cargarArbolAVLporTiempoReporte(){
        arbolAVLPorTiempoReporte = new ArbolAVL(ordenPorTiempoReporte);
        long inicio = System.nanoTime();
        for (Interseccion interseccion: intersecciones) {
            arbolAVLPorTiempoReporte.insert(interseccion);
        }
        long fin = System.nanoTime();
        long tiempo  = fin - inicio;
        long tiempoProm = tiempo/arbolAVLPorTiempoReporte.size();
        reporteFinal.add("Comparaciones de incercion en AVL por tiempo de reporte: " +  arbolAVLPorTiempoReporte.getComparacionesInsercion());
        reporteFinal.add("Tiempo promedio de insercion: " + tiempoProm);
    }



    //Mostrar Recorridos
    private void recorridosAVL(ArbolAVL<Interseccion> arbolAVL){
        reporteFinal.add("");
        reporteFinal.add("Recorrido Inorder ");
        StringBuilder inorder = new StringBuilder();
        for (Interseccion interseccion: arbolAVL.inorder()) {
            inorder.append(interseccion.getId() + ",");
        }
        reporteFinal.add(inorder.toString());

        reporteFinal.add("Recorrido Preorder ");
        StringBuilder preorder = new StringBuilder();
        for (Interseccion interseccion: arbolAVL.preorder()) {
            preorder.append(interseccion.getId() +  ",");
        }
        reporteFinal.add(preorder.toString());

        reporteFinal.add("Recorrido Postorder ");
        StringBuilder postorder = new StringBuilder();
        for (Interseccion interseccion: arbolAVL.postorder()) {
            postorder.append(interseccion.getId() +  ",");
        }
        reporteFinal.add(postorder.toString());

        reporteFinal.add("Recorrido LevelOrder ");
        StringBuilder levelorder = new StringBuilder();
        for (List<Interseccion>nivel: arbolAVL.levelOrder()) {
            for (Interseccion interseccion: nivel) {
                levelorder.append(interseccion.getId() +  ",");
            }
        }
        reporteFinal.add(levelorder.toString());
        reporteFinal.add("");
    }

    public void recorridosAVL(){
        reporteFinal.add("Recorridos ArbolAVL indexado por Ids ");
        recorridosAVL(arbolAVLPorId);

        reporteFinal.add("Recorridos ArbolAVL indexado por nivel de congestion ");
        recorridosAVL(arbolAVLPorCongestion);

        reporteFinal.add("Recorridos ArbolAVL indexado por nivel de riesgo ");
        recorridosAVL(arbolAVLPorRiesgo);

        reporteFinal.add("Recorridos ArbolAVL indexado por tiempo de reporte ");
        recorridosAVL(arbolAVLPorTiempoReporte);
    }

    //Buscar
    public void buscarInterseccionesPorIdAVL(int valor){
        long inicio = System.nanoTime();
        Interseccion interseccion=  arbolAVLPorId.search(new Interseccion(valor, "", "", "", 0, 0, 0,0));
        long fin = System.nanoTime();
        long tiempo =  fin - inicio;
        reporteFinal.add("Busqueda de interseccion " + valor);
        reporteFinal.add("Tiempo de busqueda en arbol AVL por ID en nanosegundos: " + tiempo);
        reporteFinal.add("Numero de comparaciones realizadas " + arbolAVLPorId.getComparacionesBusqueda());
        arbolAVLPorId.reset();
        reporteFinal.add("");

    }

    //Eliminar
    public void eliminarInterseccionesPorIdAVL(int valor){
        if(arbolAVLPorId.delete(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
            System.out.println("Interseccion " + valor + " encontrada");
        }else{
            System.out.println("Interseccion " + valor + " no encontrada");
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
        reporteFinal.add("");
        reporteFinal.add("Recorrido arbol nario");
        for(List<String> nivel : recorridoPorNivel) {
            StringBuilder level = new StringBuilder();
            for(String elemento : nivel) {
                level.append(elemento + ",");
            }
            reporteFinal.add(level.toString());
        }
    }

    //Estadisticas de todos los arboles
    public void estadisticasBST(){
        reporteFinal.add("");
        int alturaId = arbolBSTPorId.height();
        reporteFinal.add("Altura del arbol BST ordenado por Id:," + alturaId);

        int alturaCongestion = arbolBSTPorCongestion.height();
        reporteFinal.add("Altura del arbol BST ordenado por congestion:," + alturaCongestion);

        int alturaRiesgo = arbolBSTPorRiesgo.height();
        reporteFinal.add("Altura del arbol BST ordenado por riesgo:," + alturaRiesgo);

        int alturaTiempo = arbolBSTPorTiempoReporte.height();
        reporteFinal.add("Altura del arbol BST ordenado por actualizacion de reporte:," + alturaTiempo);

        reporteFinal.add("");

    }

    public void estadisticasAVL(){
        reporteFinal.add("");
        reporteFinal.add("Altura del arbol AVL ordenado por Id: " + arbolAVLPorId.height());
        reporteFinal.add("Total Rotaciones " + arbolAVLPorId.rotaciones());
        reporteFinal.add("Factor de balanceo " + arbolAVLPorId.factorBalance());

        reporteFinal.add("Altura del arbol AVL ordenado por Congestion" + arbolAVLPorCongestion.height());
        reporteFinal.add("Total Rotaciones " + arbolAVLPorCongestion.rotaciones());
        reporteFinal.add("Factor de balanceo " + arbolAVLPorCongestion.factorBalance());

        reporteFinal.add("Altura del arbol AVL ordenado por nivel de riesgo:," + arbolAVLPorRiesgo.height());
        reporteFinal.add("Total Rotaciones " + arbolAVLPorRiesgo.rotaciones());
        reporteFinal.add("Factor de balanceo " + arbolAVLPorRiesgo.factorBalance());

        reporteFinal.add("Altura del arbol AVL ordenado por tiempo de reporte:," + arbolAVLPorTiempoReporte.height());
        reporteFinal.add("Total Rotaciones," + arbolAVLPorTiempoReporte.rotaciones());
        reporteFinal.add("Factor de balanceo," + arbolAVLPorTiempoReporte.factorBalance());
        reporteFinal.add("");

    }

    public void estadisticasNario(){
        reporteFinal.add("");
        reporteFinal.add("Altura del arbol nario: " + arbolNarioCiudad.profunidadMaxima());
        reporteFinal.add("Cantidad total de hojas del arbol nario: " + arbolNarioCiudad.hojas());
        reporteFinal.add(arbolNarioCiudad.HojasPorDistrito());
        reporteFinal.add("Cantidad de nodos internos: " + arbolNarioCiudad.CantidadNodosInternos());
        reporteFinal.add("Factor promedio de ramificacion: " + arbolNarioCiudad.factorRamificacionPromedio());
        reporteFinal.add("");

    }

    //Cola
    public Evento generarEvento(int id){
        String[] tipos = {"accidente", "trafico", "lluvia", "semaforo descompuesto", "reparaciones", "manifestacion"};
        Random random = new Random();
        int prioridad = random.nextInt(1,101);
        String tipo = tipos[random.nextInt(tipos.length)];
        int interseccion = random.nextInt(1,intersecciones.size());


        return new Evento(id, prioridad, tipo, interseccion);
    }

    public void cargarCola(int cantidadEventos){
        for(int i = 0; i < cantidadEventos; i++){
            cola.insertar(generarEvento(i));
            listaOrdenada.insertar(generarEvento(i));
        }

    }



    public void procesarEvento(){
        Evento evento = cola.extraer();
        listaOrdenada.extraer();
        Interseccion interseccion = null;

        if (usoAVL) {
            interseccion = arbolAVLPorId.search(new Interseccion(evento.getIdInterseccion(), "", "", "", 0, 0, 0,0));
        } else if (usoBST) {
            interseccion = arbolBSTPorId.encontrado(new Interseccion(evento.getIdInterseccion(), "", "", "", 0, 0, 0,0));
        }

        if(interseccion == null){return;}


        if(usoBST == true) {
            arbolBSTPorCongestion.eliminado(interseccion);
            arbolBSTPorRiesgo.eliminado(interseccion);
            arbolBSTPorTiempoReporte.eliminado(interseccion);
        }

        if(this.usoAVL == true) {
            arbolAVLPorCongestion.delete(interseccion);
            arbolAVLPorRiesgo.delete(interseccion);
            arbolAVLPorTiempoReporte.delete(interseccion);
        }

        switch (evento.getTipo()){
            case "accidente":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 30);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "trafico":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 15);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 1);
                break;
            case "lluvia":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 15);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "semaforo descompuesto":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 10);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "reparaciones":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 10);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 20);
                break;

            case "manifestacion":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 25);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 5);
                break;

        }
        interseccion.actualizarReporte();

        if(usoBST == true) {
            arbolBSTPorCongestion.insertar(interseccion);
            arbolBSTPorRiesgo.insertar(interseccion);
            arbolBSTPorTiempoReporte.insertar(interseccion);
        }



        if(usoAVL == true) {
            arbolAVLPorCongestion.insert(interseccion);
            arbolAVLPorRiesgo.insert(interseccion);
            arbolAVLPorTiempoReporte.insert(interseccion);
        }


    }

    public void actualizarPrioridad(){
        Evento evento = cola.peek();
        Evento eventoNuevo = new Evento(evento.getId(),evento.getPrioridad()-10, evento.getTipo(), evento.getId());
        cola.actualizarPrioridad(evento, eventoNuevo);
    }

    //Pasa de ser un Max-Heap a un Min-Heap, de manera que ahora los eventos de menor prioridad se procesan primero
    public void invertirPrioridad(){
        Comparator<Evento> comparadorNuevo = (a,b) ->Integer.compare(a.getPrioridad(),b.getPrioridad());
        cola.cambiarComparador(comparadorNuevo);
    }

    public void estadisticasColaPrioridad(){
        reporteFinal.add("");
        reporteFinal.add("Cola de prioridades");
        reporteFinal.add("Intercambios totales: " + cola.getIntercambiosTotales());
        reporteFinal.add("Tiempo total de insercion: " + cola.getTiempoInsercionTotal());
        reporteFinal.add("Tiempo total de extraccion: " + cola.getTiempoExtraccionTotal());
        reporteFinal.add("");
    }

    public void estadisticasListaordenada(){
        reporteFinal.add("");
        reporteFinal.add("Lista ordenada");
        reporteFinal.add("Tiempo total de insercion: " + listaOrdenada.getTiempoInsercion());
        reporteFinal.add("Tiempo total de extraccion: " + listaOrdenada.getTiempoExtraccion());
        reporteFinal.add("");
    }





}
