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

        Aresta[] as = grafo.getArestas();
        Vertice[] vs = grafo.getVertices();

        int[] posX = new int[vs.length];
        int[] posY = new int[vs.length];

        Random rand = new Random();
        Graphics2D g2 = (Graphics2D) g;

        int min = 100;
        int max = 200;
        int verT = 40;
        int raio = 150;
        int xCentral = getWidth()/2;
        int yCentral = getHeight()/2;
        Font myFont = new Font ("Courier New", 1, 14);
        Font myFont2 = new Font ("Consolas", 2, 17);

        for(int i = 0; i < vs.length;i++){
            double angulo = 2 * Math.PI * i / vs.length;

            int x = xCentral + (int) (raio * Math.cos(angulo)) - verT / 2;
            int y = yCentral + (int) (raio * Math.sin(angulo)) - verT / 2;

            posX[i] = x;
            posY[i] = y;
        }

        for(Aresta a: as){
            int idx1 = grafo.getIndice(a.getOrigem());
            int idx2 = grafo.getIndice(a.getDestino());

            double angulo1 = 2 * Math.PI * idx1 / vs.length;
            double angulo2 = 2 * Math.PI * idx2 / vs.length;


            int x1 = xCentral + (int) (raio * Math.cos(angulo1));
            int y1 = yCentral + (int) (raio * Math.sin(angulo1));

            int x2 = xCentral + (int) (raio * Math.cos(angulo2));
            int y2 = yCentral + (int) (raio * Math.sin(angulo2));

            g2.setStroke(new BasicStroke(10));
            g2.setColor(Color.DARK_GRAY);

            int midX = (x1+x2)/2;
            int midY = (y1+y2)/2;


            g2.drawLine(x1,  y1, x2, y2);

            float[] pontilhado = {10f, 5f};

            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, pontilhado, 0));
            g2.setColor(Color.YELLOW);
            g2.drawLine(x1, y1, x2, y2);


        }

        for(int i = 0; i < vs.length;i++){

           g.fillOval(posX[i], posY[i],verT,verT);g.setColor(Color.BLACK);
           g.fillOval(posX[i] - 4, posY[i] - 4, verT + 8, verT + 8);
           g.setColor(Color.BLUE);
           g.fillOval(posX[i], posY[i], verT, verT);
            g2.setFont (myFont);
            g.drawString(vs[i].getValor(), posX[i] + verT + 5, posY[i] + verT + 5);
        }

        for(Aresta a: as){

            int idx1 = grafo.getIndice(a.getOrigem());
            int idx2 = grafo.getIndice(a.getDestino());

            double angulo1 = 2 * Math.PI * idx1 / vs.length;
            double angulo2 = 2 * Math.PI * idx2 / vs.length;


            int x1 = xCentral + (int) (raio * Math.cos(angulo1));
            int y1 = yCentral + (int) (raio * Math.sin(angulo1));

            int x2 = xCentral + (int) (raio * Math.cos(angulo2));
            int y2 = yCentral + (int) (raio * Math.sin(angulo2));

            int midX = (x1+x2)/2;
            int midY = (y1+y2)/2;

            int r = rand.nextInt((max - min) + 1) + min;
            int gr = rand.nextInt((max - min) + 1) + min;
            int b = rand.nextInt((max - min) + 1) + min;

            Color color  = new Color(r,gr,b);
            g2.setColor(color);
            g2.drawString(a.getValor(), midX - 1, midY);
            g2.drawString(a.getValor(), midX + 1, midY);
            g2.drawString(a.getValor(), midX, midY - 1);
            g2.drawString(a.getValor(), midX, midY + 1);

            g2.setFont (myFont2);
            g.setColor(new Color(31, 6, 69));
            g.drawString(a.getValor(), midX, midY);
            g2.setColor(color);
            g.drawString(String.valueOf(a.getDistancia()), midX+20, midY-15);
        }




    }


}








