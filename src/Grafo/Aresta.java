package Grafo;

public class Aresta {
    private String valor;
    private Vertice origem;
    private Vertice destino;
    private int distancia;

    public Aresta(String valor, Vertice origem, Vertice destino, int distancia) {
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String OrigemOuDestino(Vertice v){
        if(v.equals(this.origem)){
            return  "O";
        }else if(v.equals(this.destino)){
            return "D";
        }
        return null;
    }

    public String getValor() {
        return this.valor;
    }

    public Vertice getOrigem() {
        return this.origem;
    }

    public Vertice getDestino() {
        return this.destino;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public String getOValor() {
        return this.origem.getValor();
    }

    public String getDValor() {
        return this.destino.getValor();
    }

}
