package Arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Info.Material;

import java.io.File;

public class LeituraArquivo {
    private FileReader arq;
    private BufferedReader lerArq;

    LeituraArquivo(File nomeArq) throws IOException {
        try {
            arq = new FileReader(nomeArq);
            lerArq = new BufferedReader(arq);

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo.\n",
                    e.getMessage());
        }
    }

    String getLinha() throws IOException {
        return lerArq.readLine();
    }

    public List<Material> carregarMaterial() throws IOException {

        List<Material> materiais = new ArrayList<>();
        String linha = getLinha();

        while (linha != null) {
            String[] parts = linha.split(",");
            String nome = parts[0];
            String tipo = parts[1];
            String pontoDeColeta = parts[2];

            materiais.add(new Material(nome, tipo, pontoDeColeta));
        }

        return materiais;

    }

}