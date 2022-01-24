/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.modelo;

import domino.controlador.ControlDomino;
import domino.vista.Domino;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Jesus
 */
public class MyCanvas extends JPanel {

    private BufferedImage img;
    // private JPanel panel;
    private ControlDomino control;
    private ArrayList<Ficha> lista;

    //legaba un panel
    public MyCanvas() {

        this.setPreferredSize(new Dimension(2000, 2000));
        this.setMaximumSize(new Dimension(3000, 3000));
        this.setBackground(Color.WHITE);
        //this.panel = panel;
        lista = new ArrayList();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).lado1 == 5 && lista.get(i).lado2 == 5) {
                    g2.drawImage(lista.get(i).img, lista.get(i).x, lista.get(i).y, this);
                } else {
                    String rot = lista.get(i).dir;
                    switch (rot) {
                        case "H":
                            if (lista.get(i).rotacion == 1) {
                                BufferedImage rota = this.rotarDerecha(lista.get(i).img);
                                g2.drawImage(rota, lista.get(i).x, lista.get(i).y, this);
                            } else if (lista.get(i).rotacion == 2) {
                                BufferedImage rota = this.rotarIzquierda(lista.get(i).img);
                                g2.drawImage(rota, lista.get(i).x, lista.get(i).y, this);
                            } else {
                                g2.drawImage(lista.get(i).img, lista.get(i).x, lista.get(i).y, this);
                            }

                            break;
                        case "V":
                            if (lista.get(i).rotacion == 1) {
                                BufferedImage rota = this.rotar180(lista.get(i).img);
                                g2.drawImage(rota, lista.get(i).x, lista.get(i).y, this);
                            } else {
                                g2.drawImage(lista.get(i).img, lista.get(i).x, lista.get(i).y, this);
                            }

                            break;
                    }

                }
            }
        }
    }

    public BufferedImage rotarIzquierda(BufferedImage inputImage) {

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
            }
        }
        return returnImage;

    }

    public BufferedImage rotarDerecha(BufferedImage inputImage) {

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));
            }
        }
        return returnImage;

    }

    public BufferedImage rotar180(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(width, height, inputImage.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(width - x - 1, height - y - 1, inputImage.getRGB(x, y));
            }
        }
        return returnImage;
    }

    public void pintar(Ficha ficha) {
        try {
            img = ImageIO.read(new File("src\\fichas\\" + ficha.lado1 + "-" + ficha.lado2 + ".png"));
            ficha.img = img;
            lista.add(ficha);
            this.repaint();
        } catch (IOException ex) {
            Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
