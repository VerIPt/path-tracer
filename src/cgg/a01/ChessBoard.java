package cgg.a01;

import cgtools.*;

public class ChessBoard {
    private Color color1;
    private Color color2;

    ChessBoard(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }
    public Color getColor(double x, double y, int width, int height, int divider) {
        int square = height / divider;
        
            // checking square in which x and y is located
            int squareX = (int) (x / square);
            int squareY = (int) (y / square);
        
            // checking if the factor of the square is even or un-even
            boolean isEven = (squareX + squareY) % 2 == 0;
                if (isEven) return color1;
                else return color2;
    }
}
/*
 * Entwerfen Sie ein nach dem Muster von ConstantColor eine Klasse, 
 * deren Methode getColor die Farbwerte so generiert, 
 * dass ein Schachbrettmuster erzeugt wird. 
 * Farbe und Größe der quadratischen Felder sollen 
 * über Parameter steuerbar sein. )
 * Die farbliche Gestaltung der Felder können Sie frei wählen.
 */