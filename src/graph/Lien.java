package graph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IMRAN-DIALLO
 */
public class Lien {
    double capacity; // should be private
    double weight; // should be private for good practice
    int id;
    String edge ;
    static int edgeCount = 0 ;
    public Lien(double weight, double capacity) {
        this.id = edgeCount++; // This is defined in the outer class.
        this.weight = weight;
        this.capacity = capacity;
    }

    public Lien(String e1) {
        this.edge = e1 ;
    }
    @Override

    public String toString() { // Always good for debugging
        return "e"+id;
    }
}
