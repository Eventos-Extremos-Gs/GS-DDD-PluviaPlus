package globalsolution.pluviaplus.models;

public class Dispositivo extends _BaseEntity {

    private int id_dispositivo;
    private String nome;
    private String modelo;
    private int id_localizacao; // relacionamento simples com PP_LOCALIZACAO no banco de dados

    public Dispositivo() {}

    public Dispositivo(int id_dispositivo, String nome, String modelo, int id_localizacao) {
        this.id_dispositivo = id_dispositivo;
        this.nome = nome;
        this.modelo = modelo;
        this.id_localizacao = id_localizacao;
    }

    public int getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(int id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getId_localizacao() {
        return id_localizacao;
    }

    public void setId_localizacao(int id_localizacao) {
        this.id_localizacao = id_localizacao;
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id_dispositivo=" + id_dispositivo +
                ", nome='" + nome + '\'' +
                ", modelo='" + modelo + '\'' +
                ", id_localizacao=" + id_localizacao +
                '}';
    }
}

