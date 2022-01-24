/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.modelo;

import domino.controlador.Constantes;
import domino.events.MovimientoEvent;
import domino.events.MovimientoListener;
import domino.vista.Domino;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ConexionServidor implements Runnable {

    private final int PUERTO = 9192;
    private final String HOST = "localhost";
    private Socket socket;
    private DataOutputStream salida;
    private ObjectInputStream in;
    private DataInputStream entrada;
    private String mensaje;
    private Domino juego;
    private Tablero tablero;
    private int id;
    private Constantes con;
    private boolean turno;

    public ConexionServidor(Domino juego) {
        try {
            this.juego = juego;
            this.tablero = new Tablero(juego.getPanel());
            con = new Constantes();
            socket = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Conectado al servidor: " + socket.getInetAddress() + " En el puerto: " + socket.getPort());
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor... " + e.getMessage());
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                mensaje = entrada.readUTF();
                System.out.println(mensaje);
                String split[] = mensaje.split(";");
                if (split[0].equals("ID")) {
                    this.id = Integer.valueOf(split[1]);
                    System.out.println(id);
                } else if (split[0].equals(con.ESPERA)) {
                    JOptionPane.showMessageDialog(juego, "Faltan jugadores");
                } else if (split[0].equals(con.JUGAR)) {
                    JOptionPane.showMessageDialog(juego, "Comenzando partida!!");
                    this.enviaMensaje("3");
                } else if (split[0].equals("4")) {
                    if (split[15].equals(String.valueOf(this.id))) {
                        setFichasJugador(mensaje);
                    }
                } else if (split[0].equals("5")) {
                    if (tablero.ponerFicha(new Ficha(Integer.valueOf(split[2]), Integer.valueOf(split[3])), Integer.valueOf(split[4]))) {
                        System.out.println("el id es: " + split[1]);
                        System.out.println("Este id es: " + this.id);
                        if (Integer.valueOf(split[1]) == this.id) {
                            turno = true;
                            JOptionPane.showMessageDialog(juego, "Tu turno");
                        }
                    } else {
                        if (!(Integer.valueOf(split[1]) == this.id)) {
                            JOptionPane.showMessageDialog(juego, "No se puede poner la ficha wey");
                            turno = true;
                        }
                    }

                } else if (split[0].equals("6")) {
                    if (Integer.valueOf(split[1]) == this.id) {
                        turno = true;
                        JOptionPane.showMessageDialog(juego, "Tu turno");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    private boolean seguir = false;
    public boolean seguirJugando(){
        return seguir;
    }

    public void setFichasJugador(String mensaje) {
        String split[] = mensaje.split(";");
        ArrayList<JButton> botones = juego.getBotones();
        for (int i = 0; i < 14; i++) {
            String source = "src\\fichas\\" + split[i + 1] + ".png";
            if (split[i + 1].equals("5-5")) {
                turno = true;
            }
            botones.get(i).setIcon(new ImageIcon(source));
        }
        System.out.println(turno);
    }

    public void enviaMensaje(String envia) {
        try {
            salida.writeUTF(envia);
        } catch (IOException e) {
            System.out.println("No se pudo enviar mensaje");
        }
    }

    public void pasarTurno() {
        turno = !turno;
        mensaje = "6" + ";" + this.id;
        enviaMensaje(mensaje);
    }

    public boolean enviaMovimiento(Ficha ficha, int lugar) {
        if (turno) {
            mensaje = "4" + ";" + this.id + ";" + String.valueOf(ficha.lado1) + ";" + String.valueOf(ficha.lado2) + ";" + lugar;
            try {
                salida.writeUTF(mensaje);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(juego, "No es tu turno.");
        }
        return false;
    }

    public String mensaje() {
        return mensaje;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public boolean getTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getScoreTablero() {
        return tablero.scoreTablero();
    }

}
