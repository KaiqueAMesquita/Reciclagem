package Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GrafoPanel extends JPanel {
        Grafo grafo;

        public GrafoPanel(Grafo grafo) {
            this.grafo = grafo;
            Dimension d = new Dimension(480, 480);
            super.setPreferredSize(d);

        }
        public void paintComponent( Graphics g) {
            super.paintComponent( g );
            Vertice[] vs = grafo.getVertices();
            Vertice[] verificados = new Vertice[vs.length];

            Random random = new Random();
            int min = 220;
            int max = 260;
            int tamanho = 15;
            int dir = -25;

            int[] x = new int[vs.length];
            int[] y = new int[vs.length];

            int XInicial = 240-tamanho;
            int YInicial = 20;

            x[0] = XInicial;
            y[0] = YInicial;

            g.drawString(vs[0].getValor(),XInicial+tamanho,YInicial+tamanho);
            g.fillOval(XInicial,YInicial, tamanho,tamanho);



            for(int i =0; i < grafo.getVertices().length;i++){

                Aresta arestas[] = grafo.encontrarAresta(vs[i]);
                for(int j = 0; j < arestas.length;j++){

                    int dis = arestas[j].getDistancia();
                    Vertice o = arestas[j].getOrigem();
                    Vertice d = arestas[j].getDestino();
                    if(!o.equals(vs[i])){
                        if(!foiVerificado(verificados,o)){
                            int index = grafo.getIndice(o);
                            int posX = (int) ((x[i] - dir) * (dis * Math.sqrt(2)));
                            int posY = (int) ((x[i] - dir) * (dis * Math.sqrt(2)));
                            g.drawString(vs[i].getValor(),XInicial+tamanho,YInicial+tamanho);
                            g.fillOval(posX, posY,tamanho,tamanho);
                            x[index] = posX;
                            x[index] = posY;


                        }
                    } else if (!d.equals(vs[i])) {
                        if(!foiVerificado(verificados,d)){

                        }
                    }
                    if(dir == 25){
                        dir = -25;
                    }else{
                        dir = 25;
                    }
                }
            }

        }

        public boolean foiVerificado(Vertice[] verificados, Vertice v){
            for(int i = 0; i < verificados.length;i++){
                if(verificados[i].equals(v)){
                    return  true;
                }
            }
            return false;
        }




    }

