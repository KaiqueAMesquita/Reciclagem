import java.io.File;

import java.util.*;

import Grafo.Grafo;
import Grafo.Vertice;

public class Main {
    public static void main(String[] args) {
    Grafo grafo = new Grafo(6,7);

        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");

        grafo.addVertice(a.getValor());
        grafo.addVertice(b.getValor());
        grafo.addVertice(c.getValor());
        grafo.addVertice(d.getValor());
        grafo.addVertice(e.getValor());
        grafo.addVertice(f.getValor());
        grafo.addAresta("AB", a, b, 4);
        grafo.addAresta("AC", a, c, 2);
        grafo.addAresta("BC", b, c, 2);
        grafo.addAresta("BD", b, d, 5);
        grafo.addAresta("CD", c, d, 1);
        grafo.addAresta("DE", d, e, 3);
        grafo.addAresta("EF", e, f, 4);
    grafo.desenharGrafo();
    }
}
