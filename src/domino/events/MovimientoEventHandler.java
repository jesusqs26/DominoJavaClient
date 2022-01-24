/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.events;

import domino.modelo.ConexionServidor;
import domino.modelo.Ficha;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class MovimientoEventHandler {

    private List listeners = new ArrayList();
    private Ficha ficha;
    private int lugar;
    private ConexionServidor conexion;

    public synchronized void realizarMovimiento(Ficha ficha, int lugar, ConexionServidor conexion) {
        this.conexion = conexion;
        this.ficha = ficha;
        this.lugar = lugar;
        conexion.enviaMovimiento(ficha, lugar);
        this.disparaMovimientoEvent();
    }

    public synchronized void pasarTurno(ConexionServidor conexion) {
        this.conexion = conexion;
        conexion.pasarTurno();
    }

    public synchronized void addMovimientoListener(MovimientoListener l) {
        listeners.add(l);
    }

    public synchronized void removeMovimientoListener(MovimientoListener l) {
        listeners.remove(l);
    }

    private synchronized void disparaMovimientoEvent() {
        MovimientoEvent movimiento = new MovimientoEvent(this, ficha);
        Iterator listeners = this.listeners.iterator();
        while (listeners.hasNext()) {
            ((MovimientoListener) listeners.next()).onMovimientoFicha(movimiento);
        }
    }
}
