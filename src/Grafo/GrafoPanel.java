package Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GrafoPanel extends JPanel {
        Grafo grafo;

        public GrafoPanel(Grafo grafo) {
            this.grafo = grafo;
            Dimension d = new Dimension(480, 480);
            super.setPreferredSize(d);

        }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Vertice[] vs = grafo.getVertices();
        ArrayList<Boolean> desenhado = new ArrayList<Boolean>();
        int verT  = 30;
        int xInicial = 240-verT;
        int yInicial = 20;

        g.setColor(Color.black);
        g.fillOval(xInicial,yInicial,verT,verT);
        desenhado.add(true);

        for(int i = 1;i < vs.length;i++){
            ArrayList<Vertice> a =  grafo.vizinhos(vs[i]);
            for(int j = 0; j <= desenhado.size();j++){
                if(grafo.existeAresta(vs[i], vs[j])){
                    int dis = (int)((grafo.getDistancia(vs[i],vs[j])*2) * Math.sqrt(2));
                    xInicial = xInicial+dis;
                    yInicial = yInicial+dis;

                    g.setColor(Color.black);
                    g.fillOval(xInicial,yInicial,verT,verT);
                }


            }

        }
    }


}








