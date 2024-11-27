package Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Info.Material;

public class LeituraArquivo {
    private FileReader arq;
    private BufferedReader lerArq;

    public LeituraArquivo(File nomeArq) {
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
        String linha;

        while ((linha = getLinha()) != null) {
            String[] partes = linha.split(";");


            if(partes.length == 3){
                String nome = partes[0].trim().toLowerCase();
                String tipo = partes[1];
                String pontoDeColeta = partes[2].trim().toLowerCase();

//            if(nome.length() == 0 || tipo.length() == 0 || pontoDeColeta.length() == 0){
//                    System.out.println("Linha ignorada, campos invalidos: " + linha + "\n");
//                    continue;
//            }

                boolean duplicado = false;

                for(Material material : materiais){
                    if(material.getNome().toLowerCase().equals(nome) &&
                            material.getPontoDeColeta().toLowerCase().equals(pontoDeColeta)){
                        duplicado = true;
                        break;
                    }
                }

                if(!duplicado){
                    materiais.add(new Material(nome, tipo, pontoDeColeta));
                } else{
                    System.out.println("Linha ignorada, linha duplicada encontrada: " + nome + "ponto de coleta " + pontoDeColeta + "\n");
                }

            }else{
                System.out.println("Linha ignorada, formato incorreto: " + linha + "\n");
            }


        }

        return materiais;

    }

}