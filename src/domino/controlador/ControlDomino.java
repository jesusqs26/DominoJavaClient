/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.controlador;

import domino.events.MovimientoEvent;
import domino.events.MovimientoListener;
import domino.modelo.ConexionServidor;
import domino.modelo.Ficha;
import domino.modelo.MyCanvas;
import domino.modelo.Tablero;
import domino.vista.Domino;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ControlDomino implements MovimientoListener{

    private ConexionServidor conexion;
    private Domino juego;
    private String mensaje;
    private Constantes constantes;


    public ControlDomino(Domino juego) {
        this.juego = juego;
        constantes = new Constantes();
       
        conexion = new ConexionServidor(this.juego);
        iniciaHilo();
    }


    public void jugadorListo() {
        conexion.enviaMensaje(constantes.LISTO);
    }

    public void iniciaHilo() {
        Thread hilo = new Thread(conexion);
        hilo.start();
    }

    public String mensaje() {
        return conexion.mensaje();
    }

    @Override
    public void onMovimientoFicha(MovimientoEvent e) {
        if (conexion.getTurno()) {
            JOptionPane.showMessageDialog(juego, "Turno del siguiente jugador");
        }
    }
    
    public ConexionServidor getCon(){
        return conexion;
    }

    @Override
    public void onPasarTurno() {
        conexion.pasarTurno();
    }

}
