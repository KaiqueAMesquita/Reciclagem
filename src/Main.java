import java.io.File;

import java.io.IOException;
import java.util.*;

import Arquivo.LeituraArquivo;
import Grafo.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Grafo grafo = new Grafo(10, 20);

        File arquivoRotas = new File("C:/Projeto Reciclagem/src/assets/Rotas.txt");

        LeituraArquivo leitor = new LeituraArquivo(arquivoRotas);

        leitor.carregarRotas(grafo);

        System.out.println("Arestas: ");

        for (Aresta a : grafo.getArestas()) {
            if (a != null) {
                System.out.println("- " + a.getValor() + " conecta " +
                        a.getOrigem().getValor() + " a " +
                        a.getDestino().getValor() + " (Distância: " +
                        a.getDistancia() + ")");
            }
        }




    }
}
