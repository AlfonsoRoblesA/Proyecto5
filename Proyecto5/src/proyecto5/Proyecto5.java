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
public class Proyecto5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funciones fun = new Funciones();

        Individuo[] poblacion = fun.generarPoblacion(200);
        Individuo[] poblacionOrdenada = fun.ordenarPoblacion(poblacion);
        System.out.println("MEJOR primera");
        System.out.println(poblacionOrdenada[0].getAptitud());
        Individuo[] padres = fun.seleccionarPadresPorTorneo(poblacionOrdenada);
        Individuo[] hijos = fun.cruzaUniforme(padres);
        Individuo[] juntos = fun.juntarGeneraciones(poblacionOrdenada, hijos);
        Individuo[] juntosOrdenados = fun.ordenarPoblacion(juntos);
        Individuo[] mejores = fun.sacarMejores(juntosOrdenados, 200);
        int i = 2;
        System.out.println("MEJOR " + i);
        System.out.println(mejores[0].getAptitud());

        int totalEvaluaciones = 200 + hijos.length;

        int seRepite = 1000;
        double mejorAnterior = mejores[0].getAptitud();

        while (totalEvaluaciones < 220000) {
            padres = fun.seleccionarPadresPorTorneo(mejores);
            hijos = fun.cruzaUniforme(padres);
            totalEvaluaciones = totalEvaluaciones + hijos.length;
            juntos = fun.juntarGeneraciones(mejores, hijos);
            juntosOrdenados = fun.ordenarPoblacion(juntos);
            mejores = fun.sacarMejores(juntosOrdenados, 200);
            i++;
            System.out.println("MEJOR " + i);
            System.out.println(mejores[0].getAptitud());
            
            if (seRepite == 0) {
                break;
            }
            if (mejorAnterior == mejores[0].getAptitud()) {
                seRepite--;
            } else {
                seRepite = 1000;
                mejorAnterior = mejores[0].getAptitud();
            }
        }

        System.out.println("MEJOR ultima");
        System.out.println(mejores[0].getAptitud());
        System.out.println("PEOR");
        System.out.println(mejores[199].getAptitud());
        System.out.println("Mediana");
        System.out.println(mejores[100].getAptitud());
        System.out.println("Desviacion");
        System.out.println(fun.desviacion(mejores));
    }

}
