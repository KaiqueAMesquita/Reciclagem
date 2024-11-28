package UI;

import Arquivo.LeituraArquivo;
import Arvore.Trie;
import Info.Material;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TrieMenu {
    int op;
    String palavra;
    Trie trie = new Trie();

    String caminhoLeitura = "src/assets/Materiais.txt";
    LeituraArquivo arqLeitura;

    public void desenharMenu() {
        try {
            arqLeitura = new LeituraArquivo(caminhoLeitura);
            List<Material> materiais = arqLeitura.carregarMaterial();

            for (Material material : materiais) {
                trie.insere(material.getNome(), material);
            }

            do {
                op = Integer.parseInt(JOptionPane.showInputDialog(
                        "1 - Remover palavra na árvore Trie\n"
                                + "2 - Buscar Palavra na árvore Trie\n"
                                + "3 - Utilização de Autocompletar\n"
                                + "4 - Gerar Relatório\n"
                                + "5 - Sair\n"
                                + "Escolha opção desejada:"));
                switch (op) {
                    case 1:
                        palavra = JOptionPane.showInputDialog("Informe a palavra a ser removida:").toLowerCase();
                        trie.remove(palavra);
                        break;
                    case 2:
                        palavra = JOptionPane.showInputDialog("Informe a palavra a ser buscada:").toLowerCase();
                        if (trie.existe(palavra)) {
                            Material material = (Material) trie.busca(palavra);

                            JOptionPane.showMessageDialog(null,
                                    material.getNome()
                                            + "\nTipo: " + material.getTipo()
                                            + "\nPonto de coleta: " + material.getPontoDeColeta());
                        } else {
                            JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
                        }
                        break;
                    case 3:
                        palavra = JOptionPane.showInputDialog("Informe uma parte de uma palavra cadastrada:")
                                .toLowerCase();

                        JOptionPane.showMessageDialog(null,
                                "Sugestões para " + palavra + ": " + trie.autocompletar(palavra));
                        break;
                    case 4:
                        String caminhoEscrita = "src/assets/RelatorioMateriais";
                        FileWriter arqEscrita = new FileWriter(caminhoEscrita);
                        PrintWriter gravarArq = new PrintWriter(arqEscrita);

                        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + caminhoEscrita);

                        String Relatorio = "";
                        for (Material material : materiais) {
                            if (trie.busca(material.getNome()) == null) {
                                continue;
                            }

                            Material material2 = (Material) trie.busca(material.getNome());

                            Relatorio += material2.getNome()
                                    + "\nTipo: " + material2.getTipo()
                                    + "\nPonto de coleta: " + material2.getPontoDeColeta()
                                    + "\n\n";
                        }
                        // gravarArq.printf("%s", trie.busca(material.getNome()));
                        gravarArq.printf("%s", Relatorio);

                        arqEscrita.close();
                        break;
                    case 5:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");

                }
            } while (op != 5);
        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            ;
        }
    }
}


