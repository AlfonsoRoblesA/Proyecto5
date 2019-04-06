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
public class Individuo {
    
    private double [] x = new double[20];
    private double aptitud;

    public Individuo() {
    }

    public double[] getX() {
        return x;
    }

    public void setN(double[] x) {
        this.x = x;
    }

    public double getAptitud() {
        return aptitud;
    }

    public void setAptitud(double aptitud) {
        this.aptitud = aptitud;
    }
    
}
