package Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import org.w3c.dom.Node;

import javax.swing.*;

public class Grafo {
    private Vertice[] vertices;
    private Aresta[] arestas;

    private int numVertices;
    private int numArestas;

    public Grafo(int numV, int numA) {
        this.numVertices = 0;
        this.numArestas = 0;
        this.vertices = new Vertice[numV];
        this.arestas = new Aresta[numA];
    }

    public void addVertice(String valor) {
        Vertice v = new Vertice(valor);
        if (numVertices >= vertices.length) {

        } else {
            vertices[numVertices] = v;
            numVertices++;
        }
    }

    public void addAresta(String valor, Vertice o, Vertice d, int distancia) {
        Aresta a = new Aresta(valor, o, d, distancia);
        if (numArestas >= arestas.length) {
        } else {
            arestas[numArestas] = a;
            numArestas++;

        }
    }
    public ArrayList<Vertice> vizinhos(Vertice v){
        ArrayList<Vertice> vizinhos = new ArrayList<Vertice>();
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numArestas;j++){
                if(!arestas[j].getOrigem().equals(arestas[j].getDestino())){

                    if(v.equals(arestas[j].getOrigem()) &&
                            !vizinhos.contains(arestas[j].getDestino())){
                        vizinhos.add(arestas[j].getDestino());
                    }else if(v.equals(arestas[j].getDestino()) &&
                            !vizinhos.contains(arestas[j].getOrigem())){
                        vizinhos.add(arestas[j].getOrigem());

                    }
                }
            }

        }
        return vizinhos;

    }


    public boolean eVizinho(Vertice v1, Vertice v2) {
        for (int i = 0; i < numArestas; i++) {
            if (v1.equals(arestas[i].getOrigem()) && v2.equals(arestas[i].getDestino())) {
                return true;
            } else if (v1.equals(arestas[i].getDestino()) && v2.equals(arestas[i].getOrigem())) {
                return true;

            }
        }

        return false;

    }

    public void rotaDeReciclagem(Vertice start, Vertice objetivo) {

        Map<Vertice, Integer> heuristica = new HashMap<>();

        List<Vertice> caminho = aStar(start, objetivo, heuristica);

        for(Vertice v: vertices){
          if(eVizinho(start, v)){
              // Vizinhos recebem um valor heurístico menor (menos atrativo)
                heuristica.put(v,3);
            }else{
              // Outros recebem um valor heurístico maior (mais atrativo)
              heuristica.put(v,7);
          }
        }

        List<Vertice> caminho = aStar(start, objetivo, heuristica);

        if (!caminho.isEmpty()) {
            System.out.println("Caminho encontrado: ");
            for (Vertice v : caminho) {
                System.out.print(v.getValor() + " ");

            }
        } else {
            System.out.println("Nenhum caminho encontrado!");
        }
    }

    public List<Vertice> aStar(Vertice start, Vertice objetivo, Map<Vertice, Integer> heuristica) {
        // Map para rastrear o caminho
        Map<Vertice, Vertice> veioDe = new HashMap<>();

        // Map para os custos g(n)
        Map<Vertice, Integer> pontoG = new HashMap<>();
        pontoG.put(start, 0);

        // Map para os custos f(n)
        Map<Vertice, Integer> fScore = new HashMap<>();

        // PriorityQueue para o conjunto aberto
        PriorityQueue<Vertice> conjuntoAberto = new PriorityQueue<>(
                Comparator.comparingInt(v -> fScore.getOrDefault(v, Integer.MAX_VALUE)));
        conjuntoAberto.add(start);

        fScore.put(start, heuristica.getOrDefault(start, Integer.MAX_VALUE));

        while (!conjuntoAberto.isEmpty()) {
            Vertice atual = conjuntoAberto.poll();

            // Verifica se o vertice atual é o objetivo
            if (atual.equals(objetivo)) {
                return reconstruirCaminho(veioDe, atual);
            }

            // Explora os vizinhos
            for (Aresta a : getArestaDeSaida(atual)) {
                Vertice vizinho = a.getDestino();
                int tentativepontoG = pontoG.getOrDefault(atual, Integer.MAX_VALUE) + a.getDistancia();

                if (tentativepontoG < pontoG.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    // Atualiza os mapas de rastreamento
                    veioDe.put(vizinho, atual);
                    pontoG.put(vizinho, tentativepontoG);
                    fScore.put(vizinho, tentativepontoG + heuristica.getOrDefault(vizinho, Integer.MAX_VALUE));

                    if (!conjuntoAberto.contains(vizinho)) {
                        conjuntoAberto.add(vizinho);
                    }
                }
            }
        }

        // Retorna uma lista vazia caso não encontre o caminho
        return new ArrayList<>();
    }

    // Método para obter todas as arestas de saída de um vértice
    private List<Aresta> getArestaDeSaida(Vertice v) {
        // Cria uma lista para armazenar as arestas de saída do vértice v
        List<Aresta> ArestaDeSaida = new ArrayList<>();

        // Itera sobre todas as arestas existentes (presumivelmente armazenadas em um campo chamado 'arestas')
        for (Aresta a : arestas) {
            // Verifica se a aresta não é nula e se o vértice de origem da aresta é o vértice atual v
            if (a != null && a.getOrigem().equals(v)) {
                // Se a condição for verdadeira, a aresta é uma aresta de saída de v, então a adiciona à lista
                ArestaDeSaida.add(a);
            }
        }

        // Retorna a lista com todas as arestas de saída do vértice v
        return ArestaDeSaida;
    }

    // Método para reconstruir o caminho percorrido a partir do mapa de rastreamento 'veioDe'
// Este mapa mapeia cada vértice para o vértice de onde ele veio, ou seja, de onde ele foi alcançado.
    private List<Vertice> reconstruirCaminho(Map<Vertice, Vertice> veioDe, Vertice atual) {
        // Cria uma lista para armazenar o caminho encontrado
        List<Vertice> caminho = new ArrayList<>();

        // Adiciona o vértice final (atual) ao início da lista de caminho
        caminho.add(atual);

        // Enquanto o vértice atual tiver um vértice anterior registrado no mapa 'veioDe'
        while (veioDe.containsKey(atual)) {
            // Acha o vértice de onde o atual veio
            atual = veioDe.get(atual);

            // Adiciona o vértice anterior ao início da lista de caminho (para reconstruir o caminho na ordem correta)
            caminho.add(0, atual);
        }

        // Retorna a lista do caminho reconstruído, que contém os vértices na ordem do início até o objetivo
        return caminho;
    }

    public void desenharGrafo() {
        GrafoPanel p = new GrafoPanel(this);
        JFrame f = new JFrame();
        f.setTitle( "Desenhando em Java" );
        f.setContentPane( p );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.pack();
        f.setLocationRelativeTo( f );
        f.setVisible( true );
    }

    public Vertice[] getVertices() {
        return this.vertices;
    }

    public Aresta[] getArestas(){
        return this.arestas;
    }
    public Vertice getVertice(int i){
        return this.vertices[i];
    }

    public Aresta[] encontrarAresta(Vertice v1) {
        ArrayList<Aresta> arestas1 = new ArrayList<Aresta>();

        for (int i = 0; i < this.arestas.length; i++)
            if (this.arestas[i].getOrigem().equals(v1)) {
                arestas1.add(this.arestas[i]);

            } else if (this.arestas[i].getDestino().equals(v1)) {
                arestas1.add(this.arestas[i]);
            }

        return arestas1.toArray(new Aresta[0]);
    }

    public Aresta[] encontrarAresta(Vertice v1, Vertice v2) {
        ArrayList<Aresta> arestas1 = new ArrayList<Aresta>();

        for (int i = 0; i < this.arestas.length; i++)
            if (this.arestas[i].getOrigem().equals(v1) && this.arestas[i].getDestino().equals(v2)) {
                arestas1.add(this.arestas[i]);

            } else if (this.arestas[i].getOrigem().equals(v2) && this.arestas[i].getDestino().equals(v1)) {


                arestas1.add(this.arestas[i]);
            }

        return arestas1.toArray(new Aresta[0]);
    }
    public int getIndice(Vertice v){
        for(int i = 0; i < this.vertices.length;i++){
            if(v.getValor() == vertices[i].getValor()){
                return i;
            }
        }
        return -1;
    }

}

    public Aresta[] encontrarAresta(Vertice v1, Vertice v2) {
        ArrayList<Aresta> arestas1 = new ArrayList<Aresta>();

        for (int i = 0; i < this.arestas.length; i++)
            if (this.arestas[i].getOrigem().equals(v1) && this.arestas[i].getDestino().equals(v2)) {
                arestas1.add(this.arestas[i]);

            } else if (this.arestas[i].getOrigem().equals(v2) && this.arestas[i].getDestino().equals(v1)) {


                arestas1.add(this.arestas[i]);
            }

        return arestas1.toArray(new Aresta[0]);
    }
    public int getIndice(Vertice v){
        for(int i = 0; i < this.vertices.length;i++){
            if(v.getValor() == vertices[i].getValor()){
                return i;
            }
        }
        return -1;
    }

}