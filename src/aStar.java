
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Grafo.Grafo;
import Grafo.Vertice;

public class aStar {
    public static void main(String[] args) {
        // Criação do grafo
        Grafo grafo = new Grafo(5, 6);

        // Criação dos vértices
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");

        grafo.addVertice(a.getValor());
        grafo.addVertice(b.getValor());
        grafo.addVertice(c.getValor());
        grafo.addVertice(d.getValor());
        grafo.addVertice(e.getValor());

        grafo.addAresta("AB", a, b, 1);
        grafo.addAresta("AC", a, c, 4);
        grafo.addAresta("BC", b, c, 2);
        grafo.addAresta("BD", b, d, 5);
        grafo.addAresta("CD", c, d, 1);
        grafo.addAresta("DE", d, e, 3);

        grafo.rotaDeReciclagem(a, e);
    }
}