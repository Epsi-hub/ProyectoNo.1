package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Simulaciones automaticas

        //1000 intersecciones desordenadas
        Simulacion simulacion1 = new Simulacion(1000,10000,"C:\\Users\\manue\\Desktop\\ciudad1.csv",false);
        simulacion1.ArbolesBinarios();
        simulacion1.ArbolesAVL();
        simulacion1.ArbolNario();
        simulacion1.SimularEventos();
        simulacion1.generarArchivo("C:\\Users\\manue\\Desktop\\reporte1.csv");
        System.out.println("Simulacion No. 1 terminada, resultados en: reporte1.csv ");

        //10000 intersecciones desordenadas
        Simulacion simulacion2 = new Simulacion(10000,10000,"C:\\Users\\manue\\Desktop\\ciudad2.csv",false);
        simulacion2.ArbolesBinarios();
        simulacion2.ArbolesAVL();
        simulacion2.ArbolNario();
        simulacion2.SimularEventos();
        simulacion2.generarArchivo("C:\\Users\\manue\\Desktop\\reporte2.csv");
        System.out.println("Simulacion No. 2 terminada, resultados en: reporte2.csv ");

        //500000 intersecciones desordenada
        Simulacion simulacion3 = new Simulacion(50000,10000,"C:\\Users\\manue\\Desktop\\ciudad3.csv",false);
        simulacion3.ArbolesBinarios();
        simulacion3.ArbolesAVL();
        simulacion3.ArbolNario();
        simulacion3.SimularEventos();
        simulacion3.generarArchivo("C:\\Users\\manue\\Desktop\\reporte3.csv");
        System.out.println("Simulacion No. 3 terminada, resultados en: reporte3.csv ");

        //100000 intersecciones desordenadas
        Simulacion simulacion4 = new Simulacion(100000,10000,"C:\\Users\\manue\\Desktop\\ciudad4.csv",false);
        simulacion4.ArbolesBinarios();
        simulacion4.ArbolesAVL();
        simulacion4.ArbolNario();
        simulacion4.SimularEventos();
        simulacion4.generarArchivo("C:\\Users\\manue\\Desktop\\reporte4.csv");
        System.out.println("Simulacion No. 4 terminada, resultados en: reporte4.csv ");

        //1000 intersecciones ordenadas (Peor caso para BST y mayor numero de rotaciones para AVL)
        Simulacion simulacion5 = new Simulacion(1000,10000,"C:\\Users\\manue\\Desktop\\ciudad5.csv",true);
        simulacion5.ArbolesBinarios();
        simulacion5.ArbolesAVL();
        simulacion5.ArbolNario();
        simulacion5.SimularEventos();
        simulacion5.generarArchivo("C:\\Users\\manue\\Desktop\\reporte5.csv");
        System.out.println("Simulacion No. 5 terminada, resultados en: reporte5.csv ");


        //Simulacion personalizada
        Scanner sc =  new Scanner(System.in);
        System.out.println("BenchMark con 5 simulaciones automaticas ejecutado");
        System.out.println("Inicio de ejecucion de simulacion personalizada");
        System.out.println("Ingrese cantidad de intersecciones que quiere que tenga su ciudad ");
        int cantidadInter =  sc.nextInt();
        System.out.println("Ingrese cantidad de eventos que ocurren");
        int cantidadEv =  sc.nextInt();
        Simulacion simulacion6 = new Simulacion(cantidadInter,cantidadEv,"C:\\Users\\manue\\Desktop\\ciudad6.csv",true);
        simulacion5.ArbolesBinarios();
        simulacion5.ArbolesAVL();
        simulacion5.ArbolNario();
        simulacion5.SimularEventos();
        simulacion5.generarArchivo("C:\\Users\\manue\\Desktop\\reporte6.csv");
        System.out.println("Simulacion No. 6 terminada, resultados en: reporte6.csv ");



    }
}