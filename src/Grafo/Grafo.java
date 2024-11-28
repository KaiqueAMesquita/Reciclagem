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
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();

    private int numVertices;
    private int numArestas;

    public Grafo() {
        this.numVertices = 0;
        this.numArestas = 0;

    }

    //adiciona um vertice
    public void addVertice(String valor) {
        Vertice v = new Vertice(valor);
       this.vertices.add(v);
        numVertices++;
    }

    //adiciona uma aresta
    public void addAresta(String valor, Vertice o, Vertice d, int distancia) {
        Aresta a = new Aresta(valor, o, d, distancia);
        arestas.add(a);
        numVertices++;

    }

    //encontra vizinhos
    public ArrayList<Vertice> vizinhos(Vertice v){
        ArrayList<Vertice> vizinhos = new ArrayList<Vertice>();
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numArestas;j++){
                if(arestas.get(j).getOValor() != arestas.get(j).getDValor()){

                    if(v.getValor() == arestas.get(j).getOValor()){
                        vizinhos.add(arestas.get(j).getDestino());
                    }else if(v.getValor() == arestas.get(j).getDValor()){
                        vizinhos.add(arestas.get(j).getOrigem());

                    }
                }
            }

        }
        return vizinhos;

    }

    public String gerarRelatorioDeRotas(){
        String rota = "";
        for(int i = 0; i < vertices.size(); i++){
            for (int j = 0; j< vertices.size()-1;j++){
                if(i != j){
                    rota += "Caminho de: "+ vertices.get(i).getValor()+ " para "+ vertices.get(j).getValor()+" é:"+
                            "\n"+ rotaDeReciclagemString(vertices.get(i),vertices.get(j))+"\n";
                }

            }
        }


        return rota;
    }

    //ve se um vertice é vizinho de outro
    public boolean eVizinho(Vertice v1, Vertice v2) {
        for (int i = 0; i < numArestas; i++) {
            if (v1.getValor() == arestas.get(i).getOValor() && v2.getValor() == arestas.get(i).getDValor()) {
                return true;
            } else if (v1.getValor() == arestas.get(i).getDValor() && v2.getValor() == arestas.get(i).getOValor()) {
                return true;

            }
        }

        return false;

    }

    public void rotaDeReciclagem(Vertice start, Vertice objetivo) {
        // Inicializa a heurística
        Map<Vertice, Integer> heuristica = new HashMap<>();

        for (Vertice v : vertices) {
            if (eVizinho(start, v)) {
                // Vizinhos recebem um valor heurístico menor (menos atrativo)
                heuristica.put(v, 3);
            } else {
                // Outros recebem um valor heurístico maior (mais atrativo)
                heuristica.put(v, 7);
            }
        }
        // Chama o algoritmo A* para encontrar o caminho
        List<Vertice> caminho = aStar(start, objetivo, heuristica);
        // Construção da reposta com o caminho ou mensagem de erro
        if (!caminho.isEmpty()) {
            System.out.println("Caminho encontrado: ");
            for (Vertice v : caminho) {
                System.out.print(v.getValor() + " ");

            }
        } else {
            System.out.println("Nenhum caminho encontrado entre "+start.getValor()+ " e "+ objetivo.getValor());
        }
    }
    public String rotaDeReciclagemString(Vertice start, Vertice objetivo) {
        String rotas = "";
        // Inicializa a heurística
        Map<Vertice, Integer> heuristica = new HashMap<>();

        for (Vertice v : vertices) {
            if (eVizinho(start, v)) {
                // Vizinhos recebem um valor heurístico menor (menos atrativo)
                heuristica.put(v, 3);
            } else {
                // Outros recebem um valor heurístico maior (mais atrativo)
                heuristica.put(v, 7);
            }
        }
        // Chama o algoritmo A* para encontrar o caminho
        List<Vertice> caminho = aStar(start, objetivo, heuristica);
        // Construção da reposta com o caminho ou mensagem de erro
        if (!caminho.isEmpty()) {
            for (Vertice v : caminho) {
                rotas += v.getValor() + " ";

            }

        } else {
            rotas += "Nenhum caminho encontrado entre "+ start.getValor() +" e "+ objetivo.getValor();
        }
        return  rotas;
    }

    public List<Vertice> aStar(Vertice start, Vertice objetivo, Map<Vertice, Integer> heuristica) {
        // Map para rastrear o caminho de onde ele veio, v1 - veio de - v2
        Map<Vertice, Vertice> veioDe = new HashMap<>();

        // Map para os custos g(n),custo do caminho do nó inicial até n
        Map<Vertice, Integer> pontoG = new HashMap<>();
        pontoG.put(start, 0);

        // Map para os custos f(n)
        Map<Vertice, Integer> fScore = new HashMap<>();

        // PriorityQueue para o conjunto aberto(fila de prioridade)
        PriorityQueue<Vertice> conjuntoAberto = new PriorityQueue<>(
                //se não existe na lista define valor como maximo de custo
                Comparator.comparingInt(v -> fScore.getOrDefault(v, Integer.MAX_VALUE)));
        conjuntoAberto.add(start);


        fScore.put(start, heuristica.getOrDefault(start, Integer.MAX_VALUE));

        while (!conjuntoAberto.isEmpty()) {
            Vertice atual = conjuntoAberto.poll();

            // Verifica se o vertice atual é o objetivo
            if (atual.getValor() == objetivo.getValor()) {
                return reconstruirCaminho(veioDe, atual);
            }

            // Explora os vizinhos
            for (Aresta a : getArestaDeSaida(atual)) {

                Vertice vizinho;

                // Verifica se o vértice de origem da aresta é igual ao atual
                if (a.getOValor() == atual.getValor()) {
                    vizinho = a.getDestino(); // O vizinho é o destino
                } else {
                    vizinho = a.getOrigem(); // Caso contrário, o vizinho é a origem
                }

                //o custo do caminho do nó atual até vizinho
                int tentativaAtePontoG= pontoG.getOrDefault(atual, Integer.MAX_VALUE) + a.getDistancia();

                if (tentativaAtePontoG< pontoG.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    // Atualiza os mapas de rastreamento
                    veioDe.put(vizinho, atual);
                    pontoG.put(vizinho, tentativaAtePontoG);
                    fScore.put(vizinho, tentativaAtePontoG+ heuristica.getOrDefault(vizinho, Integer.MAX_VALUE));

                    //se conjunto aberto não contém vizinho ele adiciona
                    if (!conjuntoAberto.contains(vizinho)) {
                        conjuntoAberto.add(vizinho);
                    }
                }
            }
        }

        // Retorna uma lista vazia caso não encontre o caminho
        return new ArrayList<>();
    }

    // Métôdo para obter todas as arestas de saída de um vértice
    private List<Aresta> getArestaDeSaida(Vertice v) {
        // Cria uma lista para armazenar as arestas de saída do vértice v
        List<Aresta> ArestaDeSaida = new ArrayList<>();

        for (Aresta a : arestas) {
            // Verifica se a aresta não é nula e se o vértice de origem da aresta é o vértice atual v
            if (a != null && a.getOValor() ==  v.getValor()) {
                // Se a condição for verdadeira, a aresta é uma aresta de saída de v, então a adiciona à lista
                ArestaDeSaida.add(a);
            }
            if(a != null && a.getDValor() == v.getValor()){
                ArestaDeSaida.add(a);

            }
        }
        // Retorna a lista com todas as arestas de saída do vértice v
        return ArestaDeSaida;
    }
    // Métôdo para reconstruir o caminho percorrido a partir do mapa de rastreamento 'veioDe'
    private List<Vertice> reconstruirCaminho(Map<Vertice, Vertice> veioDe, Vertice atual) {
        // Cria uma lista para armazenar o caminho encontrado
        List<Vertice> caminho = new ArrayList<>();

        // Adiciona o vértice final (atual) ao início da lista de caminho
        caminho.add(atual);

        // Enquanto o vértice atual tiver um vértice anterior registrado no mapa 'veioDe'
        while (veioDe.containsKey(atual)) {
            // Acha o vértice de onde o atual veio
            atual = veioDe.get(atual);

            // Adiciona o vértice anterior ao início da lista de caminho
            caminho.add(0, atual);
        }

        // Retorna a lista do caminho reconstruído, que contém os vértices na ordem do início até o objetivo
        return caminho;
    }

    public void desenharGrafo() {
        //instancia um objeto grafoPanel passando ele próprio
        GrafoPanel p = new GrafoPanel(this);
        //istancia jFrame(Janela)
        JFrame f = new JFrame();
        f.setTitle( "Grafo" );//define nome na janela
        f.setContentPane( p );//define o panel como o conteudo da tela
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );//metodo de fechar a operação
        f.pack();
        f.setLocationRelativeTo( f );
        f.setVisible( true );
    }

    //pegar a lista de vertices
    public ArrayList<Vertice> getVertices() {
        return this.vertices;
    }

    //pegar a lista de arestas
    public ArrayList<Aresta> getArestas(){
        return this.arestas;
    }

    //pegar algum vertice da lista
    public Vertice getVertice(int i){
        return this.vertices.get(i);

    }

    //encontrar as arestas que esse vertice se encontra
    public Aresta[] encontrarAresta(Vertice v1) {
        ArrayList<Aresta> arestas1 = new ArrayList<Aresta>();

        //percorre as arestas
        for (int i = 0; i < this.arestas.size(); i++)
            //se a origem da aresta for igual ao vertice
            if (this.arestas.get(i).getOValor() == v1.getValor()) {
                //adiciona aresta a linha
                arestas1.add(this.arestas.get(i));

                //se destino da aresta for igual ao vertice
            } else if (this.arestas.get(i).getDValor()  == v1.getValor()) {
                //adiciona aresta a linha
                arestas1.add(this.arestas.get(i));
            }

        //retorna lista como um array
        return arestas1.toArray(new Aresta[0]);
    }

    //encontrar arestas que contenha dois vertices especificos
    public Aresta[] encontrarAresta(Vertice v1, Vertice v2) {
        ArrayList<Aresta> arestas1 = new ArrayList<Aresta>();

        //percorre arestas
        for (int i = 0; i < this.arestas.size(); i++) {
            //verifica se existe uma arestas com esses dois vertices
            if (this.arestas.get(i).getOValor() == v1.getValor() && this.arestas.get(i).getDValor() == v2.getValor()) {
                //adiciona se tiver
                arestas1.add(this.arestas.get(i));
            } else if (this.arestas.get(i).getOValor() ==  v2.getValor() && this.arestas.get(i).getDValor() == v1.getValor()) {
                //adiciona se tiver
                arestas1.add(this.arestas.get(i));
            }
        }
        //retorna lista como array
        return arestas1.toArray(new Aresta[0]);
    }

    //pega indice de um vertice na lista
    public int getIndice(Vertice v){
        for(int i = 0; i < this.vertices.size();i++){
            //se valor for igual retorna o indice encontrado
            if(v.getValor() == vertices.get(i).getValor()){
                return i;
            }
        }
        return -1;
    }

    public Vertice getVerticePorValor(String nome){
        for(Vertice v : vertices){
            if(v.getValor().equals(nome)){
                return v;
            }
        }
        return null;
    }
}