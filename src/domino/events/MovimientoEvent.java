/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.events;

import domino.modelo.Ficha;
import java.util.EventObject;

/**
 *
 * @author Jesus
 */
public class MovimientoEvent extends EventObject{
    
    private Ficha ficha;
    
    public MovimientoEvent(Object source, Ficha ficha) {
        super(source);
        this.ficha = ficha;
    }
    
    public Ficha getFicha(){
        return ficha;
    }
    
}
