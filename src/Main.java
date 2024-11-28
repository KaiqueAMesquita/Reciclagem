import java.io.File;

import java.io.IOException;
import java.util.*;

import Arquivo.LeituraArquivo;
import Grafo.*;

public class Main {
    public static void main(String[] args) {
    Grafo grafo = new Grafo(5,7);

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

        grafo.addAresta("RUA J", a, b, 4);
        grafo.addAresta("RUA A", a, c, 2);
        grafo.addAresta("RUA V", b, c, 2);
        grafo.addAresta("RUA T", b, d, 5);
        grafo.addAresta("RUA S", c, d, 1);
        grafo.addAresta("RUA Chama", d, e, 3);
        grafo.addAresta("RUA Vartor", c, e, 7);
    grafo.desenharGrafo();
    }
}
