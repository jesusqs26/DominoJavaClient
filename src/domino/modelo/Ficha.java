/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.modelo;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jesus
 */
public class Ficha {
    
    int lado1;
    int lado2;
    Ficha fichaIzquierda;
    Ficha fichaDerecha;
    Ficha fichaArriba;
    Ficha fichaAbajo;
    int x;
    int y;
    BufferedImage img;
    String dir;
    int rotacion;
    int visible;
    
    public Ficha(int lado1, int lado2){
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.fichaIzquierda = null;
        this.fichaDerecha = null;
        this.fichaArriba = null;
        this.fichaAbajo = null;
        this.x = 0;
        this.y = 0;
        this.img = null;
        this.dir = null;
        this.rotacion = 0;
    }
    
    
}
