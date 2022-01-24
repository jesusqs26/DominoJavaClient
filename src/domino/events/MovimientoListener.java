/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.events;

import java.util.EventListener;

/**
 *
 * @author Jesus
 */
public interface MovimientoListener extends EventListener{
    
    public void onMovimientoFicha(MovimientoEvent e);
    
    public void onPasarTurno();
    
}
