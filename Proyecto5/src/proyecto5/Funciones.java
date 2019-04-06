/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto5;

/**
 *
 * @author Foncho
 */
public class Funciones {

    int n = 20;

    public Funciones() {
    }

    /**
     * 
     * @param x el vector de los valores de un individuo
     * @param i el individuo que se encuentra analizando actualmente
     * @return regresa si x en i pasa la prueba y es apto para ser componente del individuo
     */
    public boolean g1(double[] x, int i) {
        double multiplicatoria = 1;
        for (i = i; i < n; i++) {
            multiplicatoria = multiplicatoria * x[i];
        }
        return .75 - multiplicatoria <= 0;
    }
    
    /**
     * 
     * @param x el vector de los valores de un individuo
     * @param i el individuo que se encuentra analizando actualmente
     * @return regresa si x en i pasa la prueba y es apto para ser componente del individuo
     */
    public boolean g2(double[] x, int i) {
        double sumatoria = 0;
        for (i = i; i < n; i++) {
            sumatoria = sumatoria + x[i];
        }
        return sumatoria - 7.5 * n <= 0;
    }
    
    /**
     * La funcion principal para sacar la aptitud de los individuos. funcion f(x)
     * @param x el vector de valores en un individuo
     * @return la aptitud para un individuo
     */
    public double f(double[] x) {
        double aptitud = 0;
        double sumatoria1 = 0;
        for (int i = 0; i < n; i++) {
            //Math.pow sirve para sacar el valor a la cuarta, y Math.cos para sacar el coseno de x[i]
            sumatoria1 = sumatoria1 + Math.pow(Math.cos(x[i]), 4);
        }
        double multiplicatoria = 1;
        for (int i = 0; i < n; i++) {
            //Math.pow sirve para sacar el valor al cuadrado, y Math.cos para sacar el coseno de x[i]
            multiplicatoria = multiplicatoria * Math.pow(Math.cos(x[i]), 2);
        }
        double sumatoria2 = 0;
        for(int i=0; i<n;i++){
            double valor = (i+1) * Math.pow(x[i], 2);
            sumatoria2 = sumatoria2 + valor;
        }
        
        //aqui se define la formula ya despejada con todos los valores
        double arriba = sumatoria1 - multiplicatoria;
        double abajo = Math.sqrt(sumatoria2);
        double dividido = arriba/abajo;
        aptitud = (-1) * dividido;

        return aptitud;
    }
}
