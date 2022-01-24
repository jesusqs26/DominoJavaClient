package domino.modelo;

import java.awt.Point;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Jesus
 */
public class Tablero {

    private Ficha primera;
    private Ficha ultima;
    private Ficha mula5;
    private Ficha primeraArriba;
    private Ficha ultimaAbajo;
    private MyCanvas canvas;

    /**
     * Constructor que inicializa los valores del tablero;
     */
    public Tablero(JScrollPane panel) {
        primera = null;
        ultima = null;
        primeraArriba = null;
        ultimaAbajo = null;
        canvas = new MyCanvas();
        panel.setViewportView(canvas);

    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    /**
     * Metodo para verificar si el tablero esta vacio.
     *
     * @return true si esta vacio
     */
    public boolean tableroVacio() {
        if (primera == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se agrega la primera ficha al tablero, tiene que ser la mula de 5: [5|5]
     *
     * @param ficha Ficha a colocar en el tablero
     */
    public boolean addPrimerFicha(Ficha ficha) {

        //Verifica que sea la mula de 5
        if (ficha.lado1 == 5 && ficha.lado2 == 5) {
            ficha.x = canvas.getWidth() / 2;
            ficha.y = canvas.getHeight() / 2;
            primera = ficha;
            ultima = ficha;
            mula5 = ficha;
            primeraArriba = mula5;
            ultimaAbajo = mula5;
            canvas.pintar(ficha);
            return true;
        } else {
            System.out.println("La primera ficha debe ser la mula de 5");
            return false;
        }

    }

    /**
     * Agrega una ficha a la derecha
     *
     * @param ficha
     */
    public boolean addFichaDerecha(Ficha ficha) {
        if (tableroVacio()) {
            System.out.println("No hay primera ficha");
            return false;
        } else {
            if (ultima.lado1 == ficha.lado1 || ultima.lado1 == ficha.lado2 || ultima.lado2 == ficha.lado1 || ultima.lado2 == ficha.lado2) {
                if (ultima.lado1 == 5 && ultima.lado2 == 5) {
                    ficha.x = ultima.x + 50;
                } else {
                    if (ultima.lado1 == ultima.lado2) {
                        ficha.x = ultima.x + 50;
                    } else {
                        ficha.x = ultima.x + 100;
                    }
                }
                if (ultima.y == canvas.getHeight() / 2) {
                    ficha.y = ultima.y + 25;
                } else {
                    ficha.y = ultima.y;
                }

                if (ficha.lado1 == ultima.lado1) {
                    ficha.rotacion = 2;
                } else {
                    ficha.rotacion = 1;
                }
                if (ficha.lado1 == ficha.lado2) {
                    ficha.rotacion = 0;
                    ficha.x = ultima.x + 100;
                    ficha.y = ultima.y - 25;
                }

                ficha.dir = "H";
                Ficha nueva = ficha;
                ultima.fichaDerecha = nueva;
                nueva.fichaIzquierda = ultima;
                ultima = nueva;
                canvas.pintar(ficha);
                return true;
            } else {
                return false;
            }

        }
    }

    /**
     * Agrega una ficha a la izquierda
     *
     * @param ficha
     */
    public boolean addFichaIzquierda(Ficha ficha) {
        if (tableroVacio()) {
            System.out.println("No hay primera ficha");
            return false;
        } else {
            if (ficha.lado1 == primera.lado1) {
                ficha.rotacion = 1;
            } else {
                ficha.rotacion = 2;
            }
            ficha.x = primera.x - 100;

            if (primera.y == canvas.getHeight() / 2) {
                ficha.y = primera.y + 25;
            } else {
                ficha.y = primera.y;
            }
            if (ficha.lado1 == ficha.lado2) {
                ficha.rotacion = 0;
                ficha.x = primera.x - 50;
                ficha.y = primera.y - 25;
            }
            ficha.dir = "H";
            Ficha nueva = ficha;
            primera.fichaIzquierda = nueva;
            nueva.fichaDerecha = primera;
            primera = nueva;
            canvas.pintar(ficha);
            return true;
        }
    }

    /**
     * Agrega una ficha arriba
     *
     * @param ficha
     */
    public boolean addFichaArriba(Ficha ficha) {
        if (tableroVacio()) {
            System.out.println("El tablero esta vacio");
            return false;
        } else {
            ficha.x = primeraArriba.x;
            ficha.y = primeraArriba.y - 100;

            if (ficha.lado1 == primeraArriba.lado1) {
                ficha.rotacion = 1;
                ficha.visible = 2;
                if (primeraArriba.visible == 2) {
                    ficha.rotacion = 0;
                    ficha.visible = 1;
                }
            } else if (ficha.lado1 == primeraArriba.lado2) {
                ficha.rotacion = 1;
            }
            
            ficha.dir = "V";
            Ficha nueva = ficha;
            primeraArriba.fichaArriba = nueva;
            nueva.fichaAbajo = primeraArriba;
            primeraArriba = nueva;
            canvas.pintar(ficha);
            return true;
        }
    }

    /**
     * Agrega una ficha abajo
     *
     * @param ficha
     */
    public boolean addFichaAbajo(Ficha ficha) {
        if (tableroVacio()) {
            System.out.println("El tablero esta vacio");
            return false;
        } else {
            if (ficha.lado1 == ultimaAbajo.lado2) {
                ficha.rotacion = 0;
                ficha.visible = 2;
            } else if (ficha.lado1 == ultimaAbajo.lado1) {
                if (ultimaAbajo.visible == 1) {
                    ficha.rotacion = 0;
                } else {
                    ficha.rotacion = 1;
                }
            }
            
            ficha.x = ultimaAbajo.x;
            ficha.y = ultimaAbajo.y + 100;
            ficha.dir = "V";
            Ficha nueva = ficha;
            ultimaAbajo.fichaAbajo = nueva;
            nueva.fichaArriba = ultimaAbajo;
            ultimaAbajo = nueva;
            canvas.pintar(ficha);
            return true;
        }
    }

    public boolean ponerFicha(Ficha ficha, int lugar) {

        switch (lugar) {
            case 0:
                return addPrimerFicha(ficha);
            case 1:
                return addFichaIzquierda(ficha);
            case 2:
                return addFichaDerecha(ficha);
            case 3:
                return addFichaArriba(ficha);
            case 4:
                return addFichaAbajo(ficha);
        }
        return false;
    }

    public int scoreTablero() {
        if (tableroVacio()) {
            System.out.println("El tablero esta vacio");
        } else {

            int score = 0;

            if (primera == mula5 && ultima == mula5) {
                score = 10;
                return score;
            }

            if (primera.lado1 == primera.lado2) {
                score = score + primera.lado1 + primera.lado2;
            } else {
                score = score + primera.lado1;
            }

            if (ultima.lado1 == ultima.lado2) {
                score = score + ultima.lado1 + ultima.lado2;
            } else {
                score = score + ultima.lado2;
            }

            if (primeraArriba.lado1 == primeraArriba.lado2) {
                score = score + primeraArriba.lado1 + primeraArriba.lado2;
            } else {
                score = score + primeraArriba.lado1;
            }

            if (ultimaAbajo.lado1 == ultimaAbajo.lado2) {
                score = score + ultimaAbajo.lado1 + ultimaAbajo.lado2;
            } else {
                score = score + ultimaAbajo.lado2;
            }

            System.out.println("Puntaje: " + score);
            if (score % 5 == 0) {
                System.out.println("Es multiplo");
            } else {
                System.out.println("No es multiplo");
            }
            return score;
        }

        return 0;

    }

    public void imprimir() {

        if (tableroVacio()) {
            System.out.println("El tablero esta vacio");
        } else {

            Ficha actual;
            actual = primera;
            while (actual != ultima) {
                System.out.print("[" + actual.lado1 + "-" + actual.lado2 + "]" + actual.x + " " + actual.y);
                actual = actual.fichaDerecha;
            }
            System.out.print("[" + actual.lado1 + "-" + actual.lado2 + "]" + actual.x + " " + actual.y);
            System.out.println();

            actual = primeraArriba;
            while (actual != ultimaAbajo) {
                System.out.print("[" + actual.lado1 + "-" + actual.lado2 + "]" + actual.x + " " + actual.y);
                actual = actual.fichaAbajo;
            }
            System.out.print("[" + actual.lado1 + "-" + actual.lado2 + "]" + actual.x + " " + actual.y);
            System.out.println();
        }
    }

}
