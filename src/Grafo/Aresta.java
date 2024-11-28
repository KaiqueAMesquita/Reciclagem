package Grafo;

public class Aresta {
    private String valor;
    private Vertice origem;
    private Vertice destino;
    private int distancia;

    //construtor
    public Aresta(String valor, Vertice origem, Vertice destino, int distancia) {
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    //pegar o valor(nome da Aresta)
    public String getValor() {
        return this.valor;
    }

    //pegar o vertice de origem
    public Vertice getOrigem() {
        return this.origem;
    }

    //pegar o vertice de destino
    public Vertice getDestino() {
        return this.destino;
    }

    //pegar a distancia entre vertices
    public int getDistancia() {
        return this.distancia;
    }

    //pegar o valor do vertice de origem
    public String getOValor() {
        return this.origem.getValor();
    }

    //pegar o valor do vertice de destino
    public String getDValor() {
        return this.destino.getValor();
    }

}
