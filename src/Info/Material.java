package Info;

public class Material {
    private String nome;
    private String tipo;
    private String pontoDeColeta;

    public Material(String nome, String tipo, String pontoDeColeta) {
        this.nome = nome;
        this.tipo = tipo;
        this.pontoDeColeta = pontoDeColeta;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String pontoDeColeta() {
        return this.pontoDeColeta;
    }
}
