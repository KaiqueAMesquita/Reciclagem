package Arvore;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Trie
{
    // A classe NoTrie se encontra dentro da classe Trie para que ela possa ser estática
    // Ao ser estática, é possível realizar inserir filhos dentro de filhos sem uma atribuição formal no nó raiz
    public static class NoTrie 
    {
        public boolean FimDePalavra; // Determina se o nó atual é o fim da palavra
        public NoTrie filhos[]; // Armazena os filhos (outros nós) que o nó possui
    
        // Construtor do nó
        public NoTrie() 
        {
            FimDePalavra = false;
            filhos = new NoTrie[26]; // São 26 letras no alfabeto
    
            for (int i = 0; i < 26; ++i) 
            {
                filhos[i] = null;
            }
    
        }
    }

    // Nó que contém a primeira letra de cada palavra inserida
    private NoTrie raiz;

    // Construtor da árvore Trie
    public Trie()
    {
        raiz = new NoTrie();
    }


    // --------------------------------------------------------------
    // ---------------------- ÁREA DOS MÉTODOS ----------------------
    // --------------------------------------------------------------

    // Esse método não mostra a palavra buscada
    // Implementar ArrayList para esse objetivo?
    public boolean busca(String palavra)
    {
        palavra = palavra.toLowerCase(); // Facilita a comparação
        NoTrie noAuxiliar = this.raiz; // Cria um segundo acesso ao nó raiz

        for(int i = 0; i < palavra.length(); i++)
        {
            // O charAt retorna o caractere na posição especificada. Ex: casa - palavra.charAt(0) -> 'c'
            // Essa subtração é referente aos bytes na lista ASCII, pois os caracteres estão no formato literal ('a')
            // A letra 'a' possui valor 97, a 'b' 98 e assim por diante, ou seja, 'c' - 'a' = 99 - 97 =  2
            // No exemplo da casa, isso resultaria no índice 2 para o vetor filhos (filhos[2])
            if(noAuxiliar.filhos[palavra.charAt(i) - 'a'] == null)
            {
                // Esse if é verdadeiro no caso de ser uma palavra não existente no programa
                return false;
            }

            // Indo de filho em filho, enquanto i for menor que o tamanho da palavra
            noAuxiliar = noAuxiliar.filhos[palavra.charAt(i) - 'a'];
        }

        // Se o último filho visitado no laço de repetição for um nó e for a última letra da palavra
        if((noAuxiliar != null) && (noAuxiliar.FimDePalavra == true))
        {
            return true;
        }

        return false;
    }

    public boolean insere(String palavra)
    {
        palavra = palavra.toLowerCase(); // Facilita a comparação

        if(busca(palavra)) // Caso a palavra já exista
        {
            return false;
        }

        NoTrie noAuxiliar = this.raiz; // Cria um segundo acesso ao nó raiz

        for(int i = 0; i < palavra.length(); i++)
        {
            // *Ver explicação no método busca
            if(noAuxiliar.filhos[palavra.charAt(i) - 'a'] == null)
            {
                // Caso não exista um nó na posição da letra informada, cria-se um
                noAuxiliar.filhos[palavra.charAt(i) - 'a'] = new NoTrie();
            }

            // Visita o filho criado (ou existente)
            noAuxiliar = noAuxiliar.filhos[palavra.charAt(i) - 'a'];
        }

        // Determinando que o último nó da palavra é a última letra dela
        noAuxiliar.FimDePalavra = true;

        return true;
    }

    public boolean remove(String palavra)
    {
        palavra = palavra.toLowerCase(); // Facilita a comparação

        if(!busca(palavra)) // Caso a palavra não exista
        {
            JOptionPane.showMessageDialog(null, 
            "A palavra informada não existe no registro ou não está completa!");
            return false;
        }

        NoTrie noAuxiliar = this.raiz; // Cria um segundo acesso ao nó raiz
        NoTrie noAuxiliarAnterior = noAuxiliar; // Cria um segundo acesso ao noAuxiliar
        int indiceControle = palavra.charAt(0) - 'a'; // Serve para rastrear o nó a ser excluído

        for(int i = 0; i < palavra.length(); i++)
        {
            // i = 0; i = 1; i = 2; i = 3
            // nó raiz; 'c'; 'a'; 's'
            // = 2; = 0; = 13; = 0
            // 'c'; 'a'; 's'; 'a'
            noAuxiliarAnterior = noAuxiliar; // Define como o nó anterior ao que está sendo vistoriado
            indiceControle = palavra.charAt(i) - 'a'; // No último laço, seu valor refere-se ao último nó da palavra

            noAuxiliar = noAuxiliar.filhos[palavra.charAt(i) - 'a']; // Visita o próximo nó
        }

        // Define a última letra como null, apagando seu atributo "FimDePalavra"
        // Isso faz com que ela fique inacessível pelos outros métodos, que buscam por esse atributo
        noAuxiliarAnterior.filhos[indiceControle] = null;

        return true;
    }

    public List<String> autocompletar(String prefixo)
    {
        List<String> sugestoes = new ArrayList<>(); // Uma lista para guardar as palavras possíveis
        NoTrie noAuxiliar = this.raiz; // Cria um segundo acesso ao no raiz

        for(int i = 0; i < prefixo.length(); i++)
        {
            // casa
            // i = 0; =1; =2; =3
            // raiz; 'c'; 'a'; 's' (momento da comparação)
            // 'c'; 'a'; 's'; 'a'
            if(noAuxiliar.filhos[prefixo.charAt(i) - 'a'] == null)
            {
                // Caso não ache o prefixo
                return sugestoes;
            }

            noAuxiliar = noAuxiliar.filhos[prefixo.charAt(i) - 'a']; // Visita o próximo nó
        }

        // Chama o coletar palavras, que adiciona as palavras na lista sugestoes
        coletarPalavras(noAuxiliar, prefixo, sugestoes);

        return sugestoes;
    }

    private void coletarPalavras(NoTrie no, String palavra, List<String> sugestoes)
    {
        if(no.FimDePalavra)
        {
            // Quando encontra uma palavra completa, a adiciona na lista
            sugestoes.add(palavra);
        }

        for(int i = 0; i < 26; i++) // Percorre todos os possíveis filhos do nó
        {
            if(no.filhos[i] != null) // Caso o nó possua filhos
            {
                char c = (char) (i + 'a'); // 0 + 'a' = 97 -> 'a'; 1 + 'a' = 98 -> 'b'
                // Passando:
                // - Filho do nó
                // - Prefixo + caracteres percorridos até agora
                // - Lista 
                coletarPalavras(no.filhos[i], palavra + c, sugestoes);
            }
        }
    }
}