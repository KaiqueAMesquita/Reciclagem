package UI;

import Arquivo.LeituraArquivo;
import Arvore.Trie;
import Grafo.Grafo;

import javax.swing.*;
import java.io.IOException;

public class Menu {
    //carregar dados  dos txt;
    private Grafo grafo;
    private Trie trie;

    public Menu() throws IOException {
        boolean r = true;
        do{
           this.desenharMenu();
        }while(r = true);
    }

    public void desenharMenu() throws IOException {
        int op = Integer.parseInt(JOptionPane.showInputDialog("O que você deseja configurar?\n" +
                "1 - Materiais\n2 - Rotas"));

        switch (op){
            case 1:
                this.carregarDados();
                GrafoMenu gm = new GrafoMenu();
                break;
            case 2:
                this.carregarDados();
                TrieMenu tm = new TrieMenu();
                break;
        }
    }
    public void carregarDados() throws IOException {
        LeituraArquivo ler = new LeituraArquivo("src/assets/Rotas.txt");
       //carregar rotas(grafo) e materias(arvore)
    }
    public Grafo getRotas(){
        return grafo;
    }
    public void setRotas(Grafo g){
        this.grafo = g;
    }
    public Trie getMateriais(){
        return trie;

    }
    public void setMateriais(Trie t){
        this.trie = t;
    }



}
