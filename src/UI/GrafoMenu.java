package UI;

import Grafo.Grafo;
import Info.Material;
import UI.Menu;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Grafo.Vertice;
import Grafo.Aresta;
public class GrafoMenu {
    private Menu menu;

    public GrafoMenu(Menu menu){
        this.menu = menu;

    }
    public void desenharMenu() throws IOException {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Controle de rotas:\n" +
                "1 - Adicionar rota\n2 - Remover Rota\n3 - Buscar Rota\n4 - Mapa de Rotas\n5 - Gerar Relatórios\n6 - Sair"));

        switch (op){
            case 1:
                String v  = JOptionPane.showInputDialog("Qual o nome da rota?");
                String o  = JOptionPane.showInputDialog("Qual o ponto de inicio da rota?");
                String d  = JOptionPane.showInputDialog("Qual o ponto de fim da rota?");
                String dis  = JOptionPane.showInputDialog("Qual a distancia entre os pontos?");

                adicionarRota(v,o,d,dis);
                this.desenharMenu();
                break;
            case 2:
                menu.carregarDados();
                int ops = Integer.parseInt(JOptionPane.showInputDialog("Deseja deletar como?\n1 - Pelos pontos\n2 - Pelo nome"));
                switch (ops){
                    case 1:
                        String or = JOptionPane.showInputDialog("Qual o valor do ponto de origem?");
                        String de = JOptionPane.showInputDialog("Qual o valor do ponto de destino?");
                        this.deletarRota(or,de);
                        this.desenharMenu();
                        break;
                    case 2:

                        String valor = JOptionPane.showInputDialog("Qual o nome da rota?");
                        this.deletarRota(valor);
                        this.desenharMenu();
                        break;
                }
                break;
            case 3:
                menu.carregarDados();
                String v1 = JOptionPane.showInputDialog("Deseja buscar uma rota:\n De onde quer iniciar?");
                String v2 = JOptionPane.showInputDialog("\n Até onde quer sair?");

                if(v1 == null || v2 == null){
                     v1 = JOptionPane.showInputDialog("Deseja buscar uma rota(use uma rota válida):\n De onde quer iniciar?");
                     v2 = JOptionPane.showInputDialog("\n Até onde quer ir?");
                }

                Vertice start = menu.getRotas().getVerticePorValor(v1);
                Vertice objetivo = menu.getRotas().getVerticePorValor(v2);

                JOptionPane.showMessageDialog(null,menu.getRotas().rotaDeReciclagemString(start,objetivo));
                this.desenharMenu();
                break;
            case 4:
                menu.carregarDados();
                menu.getRotas().desenharGrafo();
                this.desenharMenu();
                break;
            case 5:
                menu.carregarDados();
                String caminhoEscrita = "src/assets/RelatorioRota";
                FileWriter arqEscrita = new FileWriter(caminhoEscrita);
                PrintWriter gravarArq = new PrintWriter(arqEscrita);

                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + caminhoEscrita);

                String Relatorio = menu.getRotas().gerarRelatorioDeRotas();

                gravarArq.printf("%s", Relatorio);

                arqEscrita.close();
                this.desenharMenu();

                break;
            default:
                menu.carregarDados();
                menu.desenharMenu();
                break;
        }
    }
    public void adicionarRota(String v, String o, String d, String dis) throws IOException {
        String caminhoEscrita = "src/assets/Rotas.txt";
        FileWriter arqEscrita = new FileWriter(caminhoEscrita);
        PrintWriter gravarArq = new PrintWriter(arqEscrita);

        JOptionPane.showMessageDialog(null, "Rota adicionada em " + caminhoEscrita);

        ArrayList<Aresta> rotas = menu.getRotas().getArestas();

        for(Aresta a: rotas){
            String rota = a.getValor()+";"+a.getOValor()+";"+a.getDValor()+";"+a.getDistancia()+"\n";
            gravarArq.printf("%s", rota);
        }
        String novaRota = v+";"+o+";"+d+";"+dis;

        gravarArq.printf("%s", novaRota);


        arqEscrita.close();
    }

    //por origem e destino
    public void deletarRota(String o, String d) throws IOException {
        String caminhoEscrita = "src/assets/Rotas.txt";
        try (FileWriter arqEscrita = new FileWriter(caminhoEscrita);
             PrintWriter gravarArq = new PrintWriter(arqEscrita)) {

            ArrayList<Aresta> rotas = menu.getRotas().getArestas();

            for (Aresta a : rotas) {

                if (!o.equals(a.getOValor()) || !d.equals(a.getDValor())) {
                    String rota = a.getValor() + ";" + a.getOValor() + ";" + a.getDValor() + ";" + a.getDistancia() + "\n";
                    gravarArq.printf("%s", rota);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Rota removida do arquivo: " + caminhoEscrita);
    }


    //por valor
    public void deletarRota(String valor) throws IOException {
        String caminhoEscrita = "src/assets/Rotas.txt";
        try (FileWriter arqEscrita = new FileWriter(caminhoEscrita);
             PrintWriter gravarArq = new PrintWriter(arqEscrita)) {

            ArrayList<Aresta> rotas = menu.getRotas().getArestas();

            for (Aresta a : rotas) {

                if (!valor.equals(a.getValor())) {
                    String rota = a.getValor() + ";" + a.getOValor() + ";" + a.getDValor() + ";" + a.getDistancia() + "\n";
                    gravarArq.printf("%s", rota);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Rota removida do arquivo: " + caminhoEscrita);
    }



}