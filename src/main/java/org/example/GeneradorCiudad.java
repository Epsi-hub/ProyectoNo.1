package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorCiudad {
    public void generarDatos(int interseccionesTotales,String ruta,boolean ordenadas) {
        Random randominterseccion = new Random();
        Random randomSensor = new Random();
        Random randomRiesgo = new Random();
        Random randomCongestion = new Random();
        int distritos = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.1875)); //^2/16
        int zonas = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.375));
        int avenidas = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.375));
        int interseccionesPorAvenida = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.0625));



        int total = distritos*zonas*avenidas*interseccionesPorAvenida;
        List<String> intersecciones = new ArrayList();
        List<Integer> ids = new ArrayList();



        for(int i = 1; i <= interseccionesTotales; i++) {
            ids.add(i);
        }

        if (!ordenadas) {
            Collections.shuffle(ids);
        }

        int cantidad = interseccionesTotales;

        for(int i = 1; i <= distritos; ++i) {
            for(int j = 1; j <= zonas; ++j) {
                for(int k = 1; k <= avenidas; ++k) {
                    for(int l = 1; l <= interseccionesPorAvenida && cantidad != 0; ++l) {
                        int sensor = randomSensor.nextInt(1, 10);
                        int riesgo = randomRiesgo.nextInt(1, 100);
                        int congestion = randomCongestion.nextInt(1, 100);
                        int activa = randomCongestion.nextInt(0, 2);
                        String id = Integer.toString((Integer)ids.get(cantidad - 1));
                        intersecciones.add(id + ",Distrito" + String.valueOf(i) + ",Zona" + String.valueOf(j) + "," + String.valueOf(k) + "avenida," + String.valueOf(riesgo) + "," + String.valueOf(congestion) + "," + String.valueOf(sensor)+ "," +String.valueOf(activa));
                        --cantidad;
                    }
                }
            }
        }



        generarArchivo(ruta, intersecciones);
    }

    public void generarArchivo(String ruta, List<String> ciudad) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                for(String linea : ciudad) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
