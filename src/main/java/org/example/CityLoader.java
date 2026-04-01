package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CityLoader {

    public void generarDatos(int interseccionesTotales) {
        Random randominterseccion = new Random();
        Random randomSensor = new Random();
        Random randomRiesgo = new Random();
        Random randomCongestion = new Random();
        int distritos = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.125));
        int zonas = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.25));
        int avenidas = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.375));
        int interseccionesPorAvenida = randominterseccion.nextInt(1, (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.25)));
        List<String> intersecciones = new ArrayList();
        List<Integer> ids = new ArrayList();

        for(int i = 1; i <= interseccionesTotales; ++i) {
            ids.add(i);
        }

        int cont = 0;
        Collections.shuffle(ids);
        int cantidad = interseccionesTotales;

        for(int i = 1; i <= distritos; ++i) {
            for(int j = 1; j <= zonas; ++j) {
                for(int k = 1; k <= avenidas; ++k) {
                    for(int l = 1; l <= interseccionesPorAvenida && cantidad != 0; ++l) {
                        int sensor = randomSensor.nextInt(1, 10);
                        int riesgo = randomRiesgo.nextInt(1, 100);
                        int congestion = randomCongestion.nextInt(1, 100);
                        String var10001 = Integer.toString((Integer)ids.get(cantidad - 1));
                        intersecciones.add(var10001 + ",Distrito" + String.valueOf(i) + ",Zona" + String.valueOf(j) + "," + String.valueOf(k) + "avenida," + String.valueOf(riesgo) + "," + String.valueOf(congestion) + "," + String.valueOf(sensor));
                        --cantidad;
                    }
                }
            }
        }

        for(String linea : intersecciones) {
            System.out.println(linea);
        }

        this.generarArchivo("ciudad1.csv", intersecciones);
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

    public void cargarDatos(String ruta) {
        List<Interseccion> intersecciones = new ArrayList();

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
                    Interseccion interseccion = new Interseccion(id, distrito, zona, avenida, nivelRiesgo, nivelCongestion, sensores);
                    intersecciones.add(interseccion);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Comparator<Interseccion> ordenPorId = (a,b) -> Integer.compare(a.getId(),b.getId());
        ArbolBST arbolBST = new ArbolBST(ordenPorId);

        //Insertar segun ID
        for (Interseccion interseccion: intersecciones) {
            arbolBST.insertar(interseccion);

        }





    }

}
