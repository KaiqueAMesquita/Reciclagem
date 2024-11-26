package Arvore;

public class NoTrie {
    public boolean ehFimDePalavra;
    public NoTrie filhos[];

    public NoTrie() {

        ehFimDePalavra = false;
        filhos = new NoTrie[26];

        for (int i = 0; i < 26; ++i) {
            filhos[i] = null;
        }

    }

}