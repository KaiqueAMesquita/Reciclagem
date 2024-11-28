package UI;

import Arquivo.LeituraArquivo;
import Arvore.Trie;
import Grafo.Grafo;
import Info.Material;
import UI.GrafoMenu;
import UI.TrieMenu;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Menu {
    //carregar dados  dos txt;
    private Grafo grafo = new Grafo();
    private GrafoMenu gm = new GrafoMenu(this);

    public Menu() throws IOException {

    }

    public void desenharMenu() throws IOException {
        int op = Integer.parseInt(JOptionPane.showInputDialog("O que você deseja configurar?\n" +
                "1 - Materiais\n2 - Rotas"));

        switch (op){
            case 1:
                this.carregarDados();
                TrieMenu tm = new TrieMenu(this);
                tm.desenharMenu();
                break;
            case 2:
                this.carregarDados();
                gm.desenharMenu();
                break;
        }
    }
    public void carregarDados() throws IOException {
        LeituraArquivo ler = new LeituraArquivo("src/assets/Rotas.txt");
        //carregar rotas(grafo) e materias(arvore)
        grafo = new Grafo();
        ler.carregarRotas(grafo);
    }
    public Grafo getRotas(){
        return grafo;
    }



}