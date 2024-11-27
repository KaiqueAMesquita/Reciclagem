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


        int min = 60;
        int max = 420;
        int verT  = 40;
        int minDistance = 80;

        for (int i = 0; i < vs.length; i++) {
            int randX, randY;
            boolean validPosition;

            do {
                validPosition = true;
                randX = rand.nextInt(max - min + 1) + min;
                randY = rand.nextInt(max - min + 1) + min;

                for (int j = 0; j < i; j++) {
                    // Verifica a distância entre o novo ponto e os pontos existentes
                    int distance = (int) Math.sqrt(Math.pow(posX[j] - randX, 2) + Math.pow(posY[j] - randY, 2));
                    if (distance < minDistance) {
                        validPosition = false;
                        break; // Sai do loop se a posição não for válida
                    }
                }
            } while (!validPosition); // Continua tentando até encontrar uma posição válida

            posX[i] = randX;
            posY[i] = randY;

            g.setColor(Color.black);
            g.drawString(vs[i].getValor(), randX + verT + 5, randY + verT + 5);
            g.fillOval(randX - 4, randY - 4, verT + 8, verT + 8);
            g.setColor(Color.BLUE);
            g.fillOval(randX, randY, verT, verT);
        }
        for (Aresta a : as) {
            int idO = grafo.getIndice(a.getOrigem());
            int idD = grafo.getIndice(a.getDestino());


            g2.setStroke(new BasicStroke(10));
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(posX[idO] + 5, posY[idO] + 5, posX[idD] + 2, posY[idD] + 2);

            int midX = (posX[idO] + 5 + posX[idD] + 2) / 2;
            int midY = (posY[idO] + 5 + posY[idD] + 2) / 2;

            float[] dashPattern = {10f, 5f};
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
            g2.setColor(Color.YELLOW);
            g2.drawLine(posX[idO] + 5, posY[idO] + 5, midX, midY);
        }




    }


}








