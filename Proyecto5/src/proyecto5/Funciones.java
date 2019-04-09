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
     * @return regresa si x en i pasa la prueba y es apto para ser componente
     * del individuo, true si pasa, false si no
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
     * @return regresa si x en i pasa la prueba y es apto para ser componente
     * del individuo, true si pasa, false si no
     */
    public boolean g2(double[] x, int i) {
        double sumatoria = 0;
        for (i = i; i < n; i++) {
            sumatoria = sumatoria + x[i];
        }
        return sumatoria - 7.5 * n <= 0;
    }

    /**
     * La funcion principal para sacar la aptitud de los individuos. funcion
     * f(x)
     *
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
        for (int i = 0; i < n; i++) {
            double valor = (i + 1) * Math.pow(x[i], 2);
            sumatoria2 = sumatoria2 + valor;
        }

        //aqui se define la formula ya despejada con todos los valores
        double arriba = sumatoria1 - multiplicatoria;
        double abajo = Math.sqrt(sumatoria2);
        double dividido = arriba / abajo;
        aptitud = (-1) * dividido;

        return aptitud;
    }

    /**
     * Sirve para crear las 20 X de cada individuo, verifica que cada X cumpla
     * con las dos reglas si cumple una regla,
     *
     * @return
     */
    public double[] generarXdeIndividuo() {
        double[] x = new double[20];
        for (int i = n - 1; i > -1; i--) {
            x[i] = (double) Math.round(((Math.random() * 10.001) * 1000d)) / 1000d;
            while (!(g1(x, i) && g2(x, i))) {
                x[i] = (double) Math.round(((Math.random() * 10.001) * 1000d)) / 1000d;
            }
        }
        return x;
    }

    public Individuo[] generarPoblacion(int totalPoblacion) {
        Individuo[] poblacion = new Individuo[totalPoblacion];
        for (int i = 0; i < totalPoblacion; i++) {
            poblacion[i] = new Individuo();
        }
        for (int i = 0; i < totalPoblacion; i++) {
            poblacion[i].setX(generarXdeIndividuo());
            poblacion[i].setAptitud(f(poblacion[i].getX()));
        }
        return poblacion;
    }

    public Individuo[] ordenarPoblacion(Individuo[] poblacion) {
        for (int i = 1; i < poblacion.length; i++) {
            for (int j = 0; j < poblacion.length - 1; j++) {
                if (poblacion[j].getAptitud() > poblacion[j + 1].getAptitud()) {
                    Individuo temp = poblacion[j];
                    poblacion[j] = poblacion[j + 1];
                    poblacion[j + 1] = temp;
                }
            }
        }
        return poblacion;
    }

    public Individuo[] seleccionarPadresPorTorneo(Individuo[] poblacion) {
        Individuo[] padres = new Individuo[100];
        for (int i = 0; i < padres.length; i++) {
            padres[i] = new Individuo();
        }
        int max = poblacion.length - 1;
        int torneo = 0;
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 200 + 1);
            if (random > torneo) {
                padres[i] = poblacion[i];
            } else {
                padres[i] = poblacion[max];
            }
            max--;
            torneo++;
        }
        return padres;
    }

    public Individuo[] cruzaUniforme(Individuo[] padre) {
        Individuo[] hijos = new Individuo[50];
        int resultantes = 0;
        for (int i = 0; i < 50; i++) {
            hijos[i] = new Individuo();
        }
        int hijoActual = 0;
        for (int i = 0; i < 99; i += 2) {
            double[] xHijo = new double[20];
            for (int j = 0; j < 20; j++) {
                double ganador = Math.random();
                if (ganador < 0.5) {
                    xHijo[j] = padre[i].getX()[j];
                } else {
                    xHijo[j] = padre[i + 1].getX()[j];
                }
            }
            hijos[hijoActual].setX(xHijo);
            hijoActual++;
        }
        //Aqui se muta cada uno de los hijos, puede mutar o no
        for (int i = 0; i < 50; i++) {
            int random = (int) (Math.random() * 20);
            double probableM = hijos[i].getX()[random];
            double ganador = Math.random();
            //Si es mejor a .5, suma random en caso de que sea menor a 10, en otro caso lo resta en caso de que sea mayor a 0
            if (ganador < 0.5) {
                probableM = probableM + Math.random();
                if (probableM <= 10) {
                    hijos[i].setXIndividual(probableM, random);
                }
            } else {
                probableM = probableM - Math.random();
                if (probableM >= 0) {
                    hijos[i].setXIndividual(probableM, random);
                }
            }
        }
        int hijosCumplen = 0;
        Individuo[] hijosAux = new Individuo[50];
        for (int i = 0; i < 50; i++) {
            if (g1(hijos[i].getX(), 0)) {
                if (g2(hijos[i].getX(), 0)) {
                    // es un hijo valido y puede pasar a la siguiente generacion
                    hijosAux[hijosCumplen] = hijos[i];
                    hijosAux[hijosCumplen].setAptitud(f(hijosAux[hijosCumplen].getX()));
                    hijosCumplen++;
                }
            }
        }
        //retornar solo los hijos que cumplen
        Individuo[] hijosCumplidos = new Individuo[hijosCumplen];
        for (int i = 0; i < hijosCumplen; i++) {
            hijosCumplidos[i] = hijosAux[i];
        }
        return hijosCumplidos;
    }

    public void imprimir(Individuo[] poblacion) {
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println(i + " " + poblacion[i].getAptitud());
        }
    }
    
    public double media(Individuo[] tableros) {
        double suma = 0;
        for (int i = 0; i < 100; i++) {
            suma = suma + tableros[i].getAptitud();
        }
        return suma / 200;
    }
    
    public double desviacion(Individuo[] tableros) {
        double prom, sum = 0;
        int i, n = tableros.length;
        prom = media(tableros);
        for (i = 0; i < n; i++) {
            sum += Math.pow(tableros[i].getAptitud() - prom, 2);
        }
        return (double) Math.sqrt(sum / (double) n);
    }
    
    public Individuo[] juntarGeneraciones(Individuo[] antigua, Individuo[] nueva) {
        Individuo[] todas = new Individuo[antigua.length + nueva.length];
        //incializar todas
        for (int i = 0; i < todas.length; i++) {
            todas[i] = new Individuo();
        }
        for (int i = 0; i < antigua.length; i++) {
            todas[i] = antigua[i];
        }
        int j = 0;
        for (int i = antigua.length; i < todas.length; i++) {
            todas[i] = nueva[j];
            j++;
        }
        return todas;
    }
    
    public Individuo[] sacarMejores(Individuo[] tableros, int cantidad) {
        Individuo[] mejores = new Individuo[cantidad];
        System.arraycopy(tableros, 0, mejores, 0, cantidad);
        return mejores;
    }
}
