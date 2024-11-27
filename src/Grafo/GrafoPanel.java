package Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GrafoPanel extends JPanel {
        Grafo grafo;

        public GrafoPanel(Grafo grafo) {
            this.grafo = grafo;
            Dimension d = new Dimension(480, 480);
            super.setPreferredSize(d);

        }
        public void paintComponent( Graphics g) {
            super.paintComponent(g);
            Aresta[] arestas =  grafo.getArestas();
            int qntVertices = grafo.getVertices().length;




        }



}




