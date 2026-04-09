package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulacion {
    public int cantidadIntersecciones;
    public int cantidadEventos;
    private String ruta;
    public ManejoDeDatos administrador;
    public GeneradorCiudad generador = new GeneradorCiudad();



    public Simulacion(int cantidadIntersecciones, int cantidadEventos, String ruta, boolean ordenadas, boolean usoBST, boolean usoAVL) {
        this.cantidadIntersecciones = cantidadIntersecciones;
        this.cantidadEventos = cantidadEventos;
        this.ruta =  ruta;
        this.generador.generarDatos(cantidadIntersecciones,ruta,ordenadas);
        this.administrador = new ManejoDeDatos(usoBST,usoAVL);
        this.administrador.cargarDatos(ruta);

    }


    public void ArbolesBinarios(){

        //Carga y recorridos
        administrador.cargarArbolBSTporId();
        administrador.cargarArbolBSTporCongestion();
        administrador.cargarArbolBSTporRiesgo();
        administrador.cargarArbolBSTporTiempoReporte();
        administrador.recorridosBST();

        //Buscar
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int id = random.nextInt(1, cantidadIntersecciones);
            administrador.buscarInterseccionesPorIdBST(id);
        }
        administrador.estadisticasBST();

    }

    public void ArbolesAVL(){
        //Carga y recorridos
        administrador.cargarArbolAVLporId();
        administrador.cargarArbolAVLporCongestion();
        administrador.cargarArbolAVLporRiesgo();
        administrador.cargarArbolAVLporTiempoReporte();
        administrador.recorridosAVL();

        //Buscar
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int id = random.nextInt(1, cantidadIntersecciones);
            administrador.buscarInterseccionesPorIdAVL(id);
        }
        administrador.estadisticasAVL();
    }

    public void ArbolNario(){
        administrador.cargarArbolNario();
        administrador.recorridoArbolNario();
        administrador.estadisticasNario();
    }

    public void SimularEventos(){
        administrador.cargarCola(cantidadEventos);

        for(int i = 0; i < cantidadEventos; i++){
            if(i == 1001){ //Cuando se han procesado 1000 eventos la prioridad cambia
                administrador.invertirPrioridad();
            }
            if(i%10 == 0){ //Cada cierto tiempo se disminuye la prioridad del evento mas o menos prioritario en un momento dado, por lo que la cola se reordena
                administrador.actualizarPrioridad();
            }
            administrador.procesarEvento();
        }

        administrador.estadisticasColaPrioridad();
        administrador.estadisticasListaordenada();



    }


    public void generarArchivo(String ruta) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                for(String linea : administrador.reporteFinal) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
