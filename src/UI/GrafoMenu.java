package UI;

import Grafo.Grafo;
import UI.Menu;

import javax.swing.*;
import java.io.IOException;
import Grafo.Vertice;

public class GrafoMenu {
    private Menu menu;

    public GrafoMenu(Menu menu){
        this.menu = menu;

    }
    public void desenharMenu() throws IOException {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Controle de rotas:\n" +
                "1 - Adicionar rota\n2 - Remover Rota\n3 - Buscar Rota\n4 - Mapa de Rotas\n5 - Gerar Relatórios"));

        switch (op){
            case 1:
                break;
            case 2:

                break;
            case 3:
                String v1 = JOptionPane.showInputDialog("Deseja buscar uma rota:\n De onde quer iniciar?");
                String v2 = JOptionPane.showInputDialog("\n Até onde quer sair?");

                Vertice start = menu.getRotas().getVerticePorValor(v1);
                Vertice objetivo = menu.getRotas().getVerticePorValor(v2);

                JOptionPane.showMessageDialog(null,menu.getRotas().rotaDeReciclagemString(start,objetivo));
                break;
            case 4:
                menu.getRotas().desenharGrafo();
                this.desenharMenu();
                break;
            case 5:
                JOptionPane.showMessageDialog(null,menu.getRotas().gerarRelatorioDeRotas());
                this.desenharMenu();
                break;
            default:
                menu.carregarDados();
                menu.desenharMenu();
                break;
        }
    }


}