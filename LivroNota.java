public class LivroNota {

    private String nome;
    private double media;

    public LivroNota(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }

    public String toString() {
        return "Media [nome: " + nome + ", media: " + media + "]";
    }

    public String[] toArray() {
        String[] resp = new String[2];
        resp[0] = nome;
        resp[1] = "" + media;
        return resp;
    }
}